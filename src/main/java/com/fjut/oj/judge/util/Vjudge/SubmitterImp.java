package com.fjut.oj.judge.util.Vjudge;

import com.fjut.oj.judge.util.ApplicationContextHelper;
import com.fjut.oj.pojo.Problem;
import com.fjut.oj.pojo.Status;
import com.fjut.oj.service.ProblemService;
import com.fjut.oj.service.StatusService;

import java.sql.Timestamp;

public class SubmitterImp implements Submitter {

    public StatusService statusService = ApplicationContextHelper.getBean(StatusService.class);
    public ProblemService problemService = ApplicationContextHelper.getBean(ProblemService.class);

    @Override
    public int doSubmit(String user, int pid, int cid, int language, String code, Timestamp submittime) {

        System.out.println("doSubmit");
        //System.out.println(statusService + "123" + problemService);
        Integer maxrid = statusService.queryMaxStatusId();
        System.out.println(maxrid + "123");
        Integer rid = (maxrid == null) ? 1 : maxrid + 1;
        Problem problem = problemService.queryProblemById(pid);
        Integer ppid = Integer.parseInt(problem.getOjspid());
        Integer ojid = problem.getOjid();
        Integer codelen = code.length();

        Status status = new Status();
        status.setId(rid);
        status.setRuser(user);
        status.setPid(pid);
        status.setCid(cid);
        status.setLang(language);
        System.out.println(submittime.toString());
        status.setSubmitTime(submittime.toString().substring(0,19));
        status.setResult(0);
        status.setScore(-1);
        status.setTimeUsed("-");
        status.setMemoryUsed("-");
        status.setCode(code);
        status.setCodelen(codelen);

        System.out.println(status.toString());
        // 把这个提交记录插入到表中
        statusService.insertStatus(status);

        SubmitInfo submitInfo = new SubmitInfo(rid, ppid + "", language, code, false);
        // 插入到对应的 oj 评测机队列里
        submitVJ(submitInfo, ojid);
        System.out.println("success++++++++++");
        System.out.println(submitInfo.toString());
        return 0;
    }

    @Override
    public int reJudge(int rid) {
        return 0;
    }

    /**
     * 把提交的信息添加到相应的oj交题线程中
     * @param info
     * @param oj
     * @return
     */
    private int submitVJ(SubmitInfo info, int oj){
        m.addSubmit(info, oj);
        return 1;
    }
}
