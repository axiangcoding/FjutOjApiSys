package com.fjut.oj.controller;

import com.fjut.oj.pojo.Problem;
import com.fjut.oj.pojo.ProblemView;
import com.fjut.oj.pojo.Problemsample;
import com.fjut.oj.pojo.UserSolve;
import com.fjut.oj.service.ProblemService;
import com.fjut.oj.service.ProblemViewService;
import com.fjut.oj.service.UserSolveService;
import com.fjut.oj.util.JsonInfo;
import com.fjut.oj.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cjt axiang [20190808]
 */
@Controller
@CrossOrigin
@RequestMapping("/problemview")
public class ProblemViewController {

    @Autowired
    private ProblemViewService problemViewService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private UserSolveService userSolveService;

    @RequestMapping("/getProblemView")
    @ResponseBody
    public JsonInfo queryProblemView(@RequestParam("pid") String pidStr,
                                     @RequestParam(value = "username",required = false) String username)
    {
        JsonInfo jsonInfo = new JsonInfo();
        Integer pid = Integer.parseInt(pidStr);
        ProblemView problemView = problemViewService.queryProblemView(pid);
        Problem problem = problemService.queryProblemById(pid);
        Problemsample problemsamples;
        problemsamples = problemService.getProblemHTMLProblemSample(pid);
        Boolean solve = false;
        if (username != null) {
            UserSolve userSolve = userSolveService.queryACProblem(username, pid);
            if (userSolve != null) {
                solve = true;
            }
        }
        jsonInfo.setSuccess();
        jsonInfo.addInfo(problemView);
        jsonInfo.addInfo(problem);
        jsonInfo.addInfo(problemsamples);
        jsonInfo.addInfo(solve);
        return jsonInfo;

    }
}
