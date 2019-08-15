package com.fjut.oj.judge.util.Vjudge;

import com.fjut.oj.judge.entity.OJ.OTHOJ;
import com.fjut.oj.judge.entity.RES;
import com.fjut.oj.judge.entity.Result;
import com.fjut.oj.judge.util.ApplicationContextHelper;
import com.fjut.oj.judge.util.MyClient;
import com.fjut.oj.judge.util.Tool;
import com.fjut.oj.pojo.CeInfo;
import com.fjut.oj.pojo.Status;
import com.fjut.oj.pojo.UserSolve;
import com.fjut.oj.service.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 提交器 info 里面保存了各种提交信息
 * Created by Administrator on 2015/5/21.
 */
public class VjSubmitter implements Runnable{

    public static final int BUSY=1;
    public static final int IDLE=0;
    public LinkedList<String> showstatus;
    public String rid = "";

    //用这个网络客户端进行登录、提交等操作
    public MyClient client = new MyClient();

    //正在处理的info
    public SubmitInfo info;

    //忙碌状态与否
    int status;

    int ojid;
    int submitterID;
    VJudge vj;

    public StatusService statusService = ApplicationContextHelper.getBean(StatusService.class);
    public CeinfoService ceinfoService = ApplicationContextHelper.getBean(CeinfoService.class);
    public UserSolveService userSolveService = ApplicationContextHelper.getBean(UserSolveService.class);
    public ProblemService problemService = ApplicationContextHelper.getBean(ProblemService.class);
    public UserService userService = ApplicationContextHelper.getBean(UserService.class);

    private Thread selfThread = null;
    private String username;
    private String password;
    private String ojsrid;
    public VjSubmitter(int ojid, String us, String pas, int id, VJudge vj){
        this.ojid = ojid;
        username = us;
        password = pas;
        status = IDLE;
        submitterID = id;
        this.vj = vj;
        showstatus = new LinkedList<>();
        showstatus.addLast("Begin");
    }

    public SubmitInfo getSubmitInfo(){return info;}

    public int getOjid() {
        return ojid;
    }

    public int getID(){return submitterID;}

    public boolean isBusy(){
        return status==BUSY;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getOjsrid(){return ojsrid;}

    public void setShowstatus(String status){
        this.showstatus.addLast("["+ Tool.now()+"]"+status);
        if(this.showstatus.size()>30){
            this.showstatus.removeFirst();
        }
    }

    /**
     * 依次拿出每个评测记录来判题
     */
    @Override
    public void run() {
        while(true){
            try {
                //读取队列执行
                //System.out.println(submitterID+"IDLE");
                System.out.println(vj.queue.get(ojid).size());
                this.info=vj.queue.get(ojid).take();

                //System.out.println("this.info:" + this.info);
                this.status = BUSY;
                go();
                ojsrid="->"+ojsrid;
                this.status=IDLE;
            } catch (Exception e) {
                this.status=IDLE;
                setShowstatus("出错，10秒后重新运行");
                Tool.sleep(10000);
            }
        }
    }

    public void go() {
        try{
            System.out.println("开始执行，正在第1次获取原rid");
            OTHOJ oj;
            oj = Submitter.ojs[ojid];
            String prid;
            int z = 0;
            do {
                z++;
                if(z>=10){
                    this.status = IDLE;
                    System.out.println("获取原rid出错");
                    return;
                }
                prid = oj.getRid(username,this);//获得原来的rid

                System.out.println("获得原来的rid:" + prid);

                Tool.sleep(1000);
            } while(prid.equals("error"));
            String nrid;
            RES r ;
            int num = 0;
            int k=2;
            do{
                while (oj.submit(this).equals("error")) {
                    num++;
                    if (num >= 10) {
                        System.out.println(submitterID+":doSubmit out time");
                        this.status=IDLE;
                        System.out.println("第"+num+"次的提交出错，评测出错");
                        return;
                    }else{
                        System.out.println("第"+num+"次的提交出错，开始重试");
                    }
                    Tool.sleep(1000);
                }
                System.out.println("提交结束，开始获取评测结果");
                num = 0;
                do {
                    Tool.sleep(1000);
                    nrid = oj.getRid(username,this);
                    System.out.println(submitterID+":get rid "+num+"=" + nrid);
                    System.out.println("第"+num+"次获取rid="+nrid);
                    num++;
                    if (num == 5) {
                        break;//提交失败重新提交
                    }
                } while (nrid.equals("error")||nrid.equals(prid));
                k--;
            } while(num == 5 && k != 0);
            if (k == 0){
                System.out.println("提交失败");
            } else {
                ojsrid = nrid;
                r = oj.getResultReturn(this);
                if (r != null){
                    //Status s = Main.status.setStatusResult(info.rid, r.getR(),r.getTime(),r.getMemory(),r.getCEInfo(),r.getScore());
                    Status status = statusService.queryStatusById(info.rid);
                    System.out.println(status);
                    status.setResult(r.getR().getValue());
                    status.setTimeUsed(r.getTime());
                    status.setMemoryUsed(r.getMemory());
                    status.setScore(r.getScore());
                    statusService.updateStatus(status);
//                    System.out.println(status.toString() +  "    170");

                    if (r.getR() == Result.CE){
                        // 编译错误会往表中添加编译错误信息
                        CeInfo ceinfo = new CeInfo();
                        ceinfo.setRid(info.rid);
                        ceinfo.setInfo(r.getCEInfo());
                        ceinfoService.insertCeinfo(ceinfo);
                    }
                    UserSolve userSolve = userSolveService.queryACProblem(status.getRuser(),status.getPid());
                    if (r.getR() == Result.AC){
                        // 题目 AC 数量加一
                        problemService.updateProblemTotalAc(status.getPid());
                        if (userSolve == null){
                            // 用户写题数量 + 1
                            userService.addAcnum(status.getRuser());
                            // 用户之前未 AC 过,AC用户数目加一
                            problemService.updateProblemtotalAcUser(status.getPid());
                            // 用户之前未尝试过现在解决了
                            userSolveService.replaceUserSolve(status.getRuser(),status.getPid(),1);
                        }
                    } else{
                        if (userSolve==null)
                        // 用户尝试过该题目，但没有解决
                        userSolveService.replaceUserSolve(status.getRuser(),status.getPid(),0);
                    }
                }
            }
            this.status=IDLE;
        } catch(Exception e){
            System.out.println("未知错误");
            this.status=IDLE;
        }
    }

    public List<String> row(){
        //submitterID,ojid,status,username,info.rid,info.pid,ojsrid,show
        List<String> row=new ArrayList<String>();
        // row.add(HTML.a("admin.jsp?page=SubmitterInfo&id="+submitterID,submitterID+""));
        if(submitterID == -1) row.add("local");
        else row.add(Submitter.ojs[ojid].getName());
        row.add(selfThread.getId()+"");
        row.add(status==1?"正在评测":"空闲");
        row.add(username);
        row.add(info==null?"":info.rid+"");
        row.add(info==null?"":info.pid+"");
        row.add(ojsrid+"");
        row.add(showstatus.getLast());
        return row;
    }

    void setSelfThread(Thread selfThread) {
        this.selfThread = selfThread;
    }
}
