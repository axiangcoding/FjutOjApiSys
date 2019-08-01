package com.fjut.oj.controller;

import com.fjut.oj.interceptor.CheckUserIsLogin;
import com.fjut.oj.judge.util.Vjudge.Submitter;
import com.fjut.oj.judge.util.Vjudge.SubmitterImp;
import com.fjut.oj.localjudge.LocalJudgeHttp;
import com.fjut.oj.pojo.*;
import com.fjut.oj.pojo.enums.Result;
import com.fjut.oj.service.*;
import com.fjut.oj.util.JsonInfo;
import com.fjut.oj.util.ResultString;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO: 把 JsonMsg 替换为 JsonInfo
 *
 * @author cjt
 */
@RequestMapping("/submit")
@Controller
@CrossOrigin
@ResponseBody
public class SubmitController {

    @Autowired
    private StatusService statusService;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private UserSolveService userSolveService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private UserService userService;
    @Autowired
    private CeinfoService ceinfoService;

    @Autowired
    private ThreadPoolTaskExecutor executor;
//    private static ExecutorService service = Executors.newFixedThreadPool(100);

    Submitter sm = new SubmitterImp();

    @RequestMapping("/submitProblem")
    @CheckUserIsLogin
    public JsonInfo submitProblem(HttpServletRequest req) {
        String strpid = req.getParameter("pid");
        if (strpid == null || strpid == "") {
            return new JsonInfo("FAIL", "pid未传入");
        }

        String user = req.getParameter("user");
        if (user == null || user == "") {
            return new JsonInfo("FAIL", "user未传入");
        }

        String code = req.getParameter("code");
        if (code == null || code == "") {
            return new JsonInfo("FAIL", "code未传入");
        }

        // code = "#include<stdio.h>\nint main(){int a,b;while(~scanf(\"%d%d\",&a,&b))printf(\"%d\\n\",a+b);}";
        Integer pid = Integer.parseInt(strpid);
        Integer cid = Integer.parseInt(req.getParameter("cid") == null ? "-1" : req.getParameter("cid"));

        if (cid != -1) {
            Contest contest = contestService.getContestById(cid);
            if (contest == null) {
                return new JsonInfo("FAIL", "没有查找到该比赛");
            }
            String endTime = contest.getEndTime();

            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);

            if (endTime.compareTo(dateString) < 0) {
                return new JsonInfo("FAIL", "比赛已经结束，提交失败");
            }

            Integer userNum = contestService.getContestUser(cid, user);
            if (userNum == 0) {
                // 用户之前没有提交过题目，添加该用户
                Contestuser contestuser = new Contestuser();
                contestuser.setTime(dateString);
                contestuser.setCid(cid);
                contestuser.setUsername(user);
                contestuser.setInfo("");
                contestuser.setStatu(1);
                contestService.insertContestuser(contestuser);
            }
        }

        String language = req.getParameter("language") == null ? "G++" : req.getParameter("language");
        Integer langid = ResultString.getSubmitLanguage(language);
        System.out.println(pid + " " + cid + " " + language + " " + langid + user + code);

        Timestamp submittime = new Timestamp(System.currentTimeMillis());
        Integer maxpid = statusService.queryMaxStatusId();
        Integer newpid = maxpid == null ? 1 : maxpid + 1;
        sm.doSubmit(user, pid, cid, langid, code, submittime);
        problemService.updateProblemtotalSubmit(pid);
        UserSolve userSolve = userSolveService.queryByUsernameAndPid(user, pid);
        if (userSolve == null) {
            // 该用户没有交过这道题目
            problemService.updateProblemtotalSubmitUser(pid);

        }
        return new JsonInfo("SUCCESS", "");
    }


    /**
     * 提交到本地
     */
    @PostMapping("/submitProblemToLocal")
    public JsonInfo submitProblemToLocalJudge(@RequestParam("pid") String pidStr,
                                              @RequestParam("timeLimit") String timeLimitStr,
                                              @RequestParam("memoryLimit") String MemoryLimitStr,
                                              @RequestParam("code") String code,
                                              @RequestParam("language") String language,
                                              @RequestParam("user") final String user,
                                              @RequestParam(value = "cid", required = false) String cidStr) throws InterruptedException {
        JsonInfo jsonInfo = new JsonInfo();

        Integer cid = -1;
        if (null != cidStr && "".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        if (cid != -1) {
            Contest contest = contestService.getContestById(cid);
            if (contest == null) {
                return new JsonInfo("FAIL", "没有查找到该比赛");
            }
            String endTime = contest.getEndTime();
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);

            if (endTime.compareTo(dateString) < 0) {
                return new JsonInfo("FAIL", "比赛已经结束，提交失败");
            }

            Integer userNum = contestService.getContestUser(cid, user);
            if (userNum == 0) {
                // 用户之前没有提交过题目，添加该用户
                Contestuser contestuser = new Contestuser();
                contestuser.setTime(dateString);
                contestuser.setCid(cid);
                contestuser.setUsername(user);
                contestuser.setInfo("");
                contestuser.setStatu(1);
                contestService.insertContestuser(contestuser);
            }
        }

        String type = "submit";
        Integer pid = Integer.parseInt(pidStr);
        Integer maxrid = statusService.queryMaxStatusId();
        Integer rid = maxrid == null ? 1 : maxrid + 1;
        Integer timeLimit = Integer.parseInt(timeLimitStr);
        Integer MemoryLimit = Integer.parseInt(MemoryLimitStr);

        LocalJudgeSubmitInfo localJudgeSubmitInfo = new LocalJudgeSubmitInfo();
        localJudgeSubmitInfo.setType(type);
        localJudgeSubmitInfo.setPid(pid);
        localJudgeSubmitInfo.setRid(rid);
        localJudgeSubmitInfo.setMemorylimit(MemoryLimit);
        localJudgeSubmitInfo.setTimelimit(timeLimit);
        localJudgeSubmitInfo.setCode(code);
        localJudgeSubmitInfo.setLanguageId(("JAVA").equalsIgnoreCase(language) ? 2 : ("Python").equalsIgnoreCase(language) ? 3 : 1);
        String submitJsonStr = LocalJudgeHttp.submitToLocalJudge(localJudgeSubmitInfo);
        JSONObject jsonObject = JSONObject.fromObject(submitJsonStr);

        Status beforeStatus = new Status();
        // 如果提交到本地评测机成功，则插入数据库
        if ("success".equals(jsonObject.getString("ret"))) {
            Integer langid = ResultString.getSubmitLanguage(language);
            beforeStatus.setId(rid);
            beforeStatus.setRuser(user);
            beforeStatus.setPid(pid);
            beforeStatus.setCid(cid);
            beforeStatus.setLang(langid);
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            beforeStatus.setSubmitTime(formatter.format(currentTime));
            beforeStatus.setResult(0);
            beforeStatus.setScore(-1);
            beforeStatus.setTimeUsed("-");
            beforeStatus.setMemoryUsed("-");
            beforeStatus.setCode(code);
            beforeStatus.setCodelen(code.length());
            statusService.insertStatus(beforeStatus);
            //题目号为pid的题目解决总数+1
            problemService.updateProblemtotalSubmit(pid);
            // 查询用户是否提交过这道题
            UserSolve userSolve = userSolveService.queryByUsernameAndPid(user, pid);
            if (userSolve == null) {
                // 该用户没有交过这道题目
                problemService.updateProblemtotalSubmitUser(pid);
            }
            jsonInfo.setSuccess("代码提交成功！");
        } else {
            return new JsonInfo("FAIL", "评测机访问失败！");
        }

        final Integer finalRid = beforeStatus.getId();
        final Integer finalPid = beforeStatus.getPid();
        final String finalUsername = beforeStatus.getRuser();
        // 提交成功后，开启另外的线程获取结果
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                try {
                    getResultFromLocalJudgeSystem(finalRid, finalPid, finalUsername);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        return jsonInfo;
    }

    @Transactional
    public void getResultFromLocalJudgeSystem(Integer rid, Integer pid, String username) throws InterruptedException {
        Status status = new Status();
        status.setId(rid);
        status.setPid(pid);
        status.setRuser(username);
        // 评测结果
        String judgingStatu = "judging";
        boolean quitLoop = false;
        int times = 100;
        System.out.println(executor.getActiveCount());
        do {
            String getResultJsonStr = LocalJudgeHttp.getResultFromLocalJudge(rid);
            JSONObject jsonObject = JSONObject.fromObject(getResultJsonStr);
            if ("success".equals(jsonObject.getString("ret"))) {
                JSONObject resultJsonObj = JSONObject.fromObject(jsonObject.getString("result"));
                judgingStatu = resultJsonObj.getString("type");
                if ("padding".equals(judgingStatu)) {
                    status.setResult(Result.PENDING.getValue());
                    status.setTimeUsed("-");
                    status.setMemoryUsed("-");
                    statusService.updateStatusAfterJudge(status);
                } else if ("judging".equals(judgingStatu)) {
                    status.setResult(Result.JUDGING.getValue());
                    status.setTimeUsed("-");
                    status.setMemoryUsed("-");
                    statusService.updateStatusAfterJudge(status);
                } else if ("CE".equals(judgingStatu)) {
                    // 插入数据库内容，并设置ceinfo为resultJsonObj.getString("info");
                    Ceinfo ceinfo = new Ceinfo();
                    ceinfo.setRid(status.getId());
                    ceinfo.setInfo(resultJsonObj.getString("info"));
                    ceinfoService.insertCeinfo(ceinfo);
                    status.setResult(Result.CE.getValue());
                    status.setTimeUsed("-");
                    status.setMemoryUsed("-");
                    statusService.updateStatusAfterJudge(status);
                    quitLoop = true;
                }
                //以下为运行正确返回的内容，即提交并且编译成功得到的结果
                else {
                    String resStatu = "";
                    int time = 0;
                    int memory = 0;
                    JSONArray retJsonArr = resultJsonObj.getJSONArray("ret");
                    // FIXME: 测试中评测机只返回一组有效记录，但是定义成数组，可能有多组，暂时没发现那种情况，先这么做
                    for (int i = 0; i < retJsonArr.size(); i++) {
                        resStatu = retJsonArr.getJSONArray(i).getString(1);
                        if (resStatu.equals("MLE") || resStatu.equals("OLE")) {
                            time = retJsonArr.getJSONArray(i).getInt(4);
                        } else {
                            time = retJsonArr.getJSONArray(i).getInt(2);
                        }
                        memory = retJsonArr.getJSONArray(i).getInt(3);
                    }
                    judgingStatu = resStatu;
                    status.setResult(Result.valueOf(judgingStatu).getValue());
                    status.setTimeUsed(time + "MS");
                    status.setMemoryUsed(memory + "KB");
                    statusService.updateStatusAfterJudge(status);
                    System.out.println("statuID: " + Result.valueOf(judgingStatu).getValue()
                            + "\tstatu: " + resStatu
                            + "\ttime: " + time + "MS"
                            + "\tmemory: " + memory + "KB");
                    quitLoop = true;
                }
            } else {
                quitLoop = true;
                // throw new RuntimeException("获取提交结果失败！");
            }
            Thread.sleep(2000);
            times--;
        } while (times > 0 && !quitLoop);

        // 200s的获取结果执行完毕或者拿到AC/CE编译的结果后执行
        // 先拿到用户的所有AC题目记录
        UserSolve userSolve = userSolveService.queryACProblem(status.getRuser(), status.getPid());
        if ("AC".equalsIgnoreCase(judgingStatu)) {
            // 题目 AC 数量加一
            problemService.updateProblemtotalAc(status.getPid());
            if (userSolve == null) {
                // 用户写题数量 + 1
                userService.addAcnum(status.getRuser());
                // 用户之前未 AC 过,AC用户数目加一
                problemService.updateProblemtotalAcUser(status.getPid());
                // 用户之前未尝试过现在解决了
                userSolveService.replaceUserSolve(status.getRuser(), status.getPid(), 1);
            }
        } else {
            if (userSolve == null)
                // 用户尝试过该题目，但没有解决
                userSolveService.replaceUserSolve(status.getRuser(), status.getPid(), 0);
        }

    }

}
