package com.fjut.oj.controller;

import com.fjut.oj.judge.entity.OJ.BNUOJ.BNUOJ;
import com.fjut.oj.judge.entity.OJ.CF.CF;
import com.fjut.oj.judge.entity.OJ.CodeVS.CodeVS;
import com.fjut.oj.judge.entity.OJ.HDU.HDU;
import com.fjut.oj.judge.entity.OJ.HUST.HUST;
import com.fjut.oj.judge.entity.OJ.NBUT.NBUT;
import com.fjut.oj.judge.entity.OJ.OTHOJ;
import com.fjut.oj.judge.entity.OJ.PKU.PKU;
import com.fjut.oj.judge.util.HTML.problemHTML;
import com.fjut.oj.pojo.Problem;
import com.fjut.oj.pojo.ProblemView;
import com.fjut.oj.pojo.ProblemSample;
import com.fjut.oj.service.ProblemSampleService;
import com.fjut.oj.service.ProblemService;
import com.fjut.oj.service.ProblemViewService;
import com.fjut.oj.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cjt
 * TODO: 把 JsonMsg 替换为 JsonInfo
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/addProblem")
public class AddProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private ProblemViewService problemViewService;

    @Autowired
    private ProblemSampleService problemSampleService;

    OTHOJ[] ojs = {
            new HDU(),
            new BNUOJ(),
            new NBUT(),
            new PKU(),
            new HUST(),
            new CF(),
            new CodeVS(),
    };

    @PostMapping("/getAddProblemTitle")
    public JsonMsg getAddProblemTitle(@RequestParam(value = "pid", required = false) String pidStr,
                                      @RequestParam(value = "ojStr", required = false) String ojStr) {
        if (pidStr == null || ojStr == null) {
            return JsonMsg.fail().addInfo("信息不足");
        }
        Integer ojid = null;
        if ("HDU".equals(ojStr)) {
            ojid = 0;
        } else if ("BNUOJ".equals(ojStr)) {
            ojid = 1;
        } else if ("NBUT".equals(ojStr)) {
            ojid = 2;
        } else if ("PKU".equals(ojStr)) {
            ojid = 3;
        } else if ("HUST".equals(ojStr)) {
            ojid = 4;
        } else if ("CF".equals(ojStr)) {
            ojid = 5;
        } else if ("CodeVS".equals(ojStr)) {
            ojid = 6;
        } else {
            return JsonMsg.fail().addInfo("oj名称输入有误");
        }

        Problem problem = problemService.queryProblemByOjidAndOjspid(ojid, pidStr);
        if (problem != null) {
            return JsonMsg.fail().addInfo("该题目已经存在!");
        }
        String title = ojs[ojid].getTitle(pidStr);
        return JsonMsg.success().addInfo(title);
    }


    @Transactional(rollbackFor = RuntimeException.class)
    @RequestMapping("/IAddProblem")
    @ResponseBody
    public JsonMsg insertProblem(HttpServletRequest req) {
        String pidStr = req.getParameter("pid");
        String ojStr = req.getParameter("ojStr");
        if (pidStr == null || ojStr == null) {
            return JsonMsg.fail().addInfo("信息不足");
        }
        Integer ojid = null;
        if ("HDU".equals(ojStr)) {
            ojid = 0;
        } else if ("BNUOJ".equals(ojStr)) {
            ojid = 1;
        } else if ("NBUT".equals(ojStr)) {
            ojid = 2;
        } else if ("PKU".equals(ojStr)) {
            ojid = 3;
        } else if ("HUST".equals(ojStr)) {
            ojid = 4;
        } else if ("CF".equals(ojStr)) {
            ojid = 5;
        } else if ("CodeVS".equals(ojStr)) {
            ojid = 6;
        } else {
            return JsonMsg.fail().addInfo("oj名称输入有误");
        }

        Problem problem1 = problemService.queryProblemByOjidAndOjspid(ojid, pidStr);
        if (problem1 != null) {
            return JsonMsg.fail().addInfo("该题目已经存在!");
        }

        Integer newpid = problemService.queryMaxProblemId() == null ? 0 : problemService.queryMaxProblemId() + 1;
        Problem problem = new Problem();
        problem.setPid(newpid);
        problem.setPtype(1);
        String title = ojs[ojid].getTitle(pidStr);
        problem.setTitle(title);
        problem.setOjid(ojid);
        problem.setOjspid(pidStr);
        problem.setVisiable(1);
        problem.setAuthor("");
        problem.setSpj(false);
        problem.setOwner("");
        System.out.println(problem.toString());
        Integer addnum1 = problemService.insertProblem(problem);

        problemHTML p = ojs[ojid].getProblemHTML(pidStr);

        ProblemView problemView = new ProblemView();
        problemView.setPid(newpid);
        problemView.setTimelimit(p.getTimeLimit());
        problemView.setMenorylimit(p.getMenoryLimit());
        problemView.setInt64(p.getInt64());
        problemView.setSpj(0);
        problemView.setDis(p.getDis());
        problemView.setInput(p.getInput());
        problemView.setOutput(p.getOutput());
        System.out.println(problemView.toString());
        Integer addnum2 = problemViewService.insertProblemView(problemView);

        ProblemSample problemsample = new ProblemSample();
        problemsample.setPid(newpid);
        problemsample.setId(0);
        problemsample.setInput(p.getSampleInput().get(0));
        problemsample.setOutput(p.getSampleOutput().get(0));
        System.out.println(problemsample.toString());
        Integer addnum3 = problemSampleService.insertProblemSample(problemsample);

        if (addnum1 != 0 && addnum2 != 0 && addnum3 != 0) {
            return JsonMsg.success().addInfo("插入成功！");
        }
        return JsonMsg.fail().addInfo("插入失败");
    }
}
