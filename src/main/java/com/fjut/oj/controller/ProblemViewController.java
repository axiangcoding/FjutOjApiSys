package com.fjut.oj.controller;

import com.fjut.oj.pojo.Problem;
import com.fjut.oj.pojo.ProblemView;
import com.fjut.oj.pojo.Problemsample;
import com.fjut.oj.pojo.UserSolve;
import com.fjut.oj.service.ProblemService;
import com.fjut.oj.service.ProblemViewService;
import com.fjut.oj.service.UserSolveService;
import com.fjut.oj.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * TODO: 把 JsonMsg 替换为 JsonInfo
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

    @RequestMapping("/Gproblemview")
    @ResponseBody
    public JsonMsg queryProblemView(HttpServletRequest req, HttpServletResponse resp) {
        Integer pid = Integer.parseInt(req.getParameter("pid"));
        ProblemView problemView = problemViewService.queryProblemView(pid);
        Problem problem = problemService.queryProblemById(pid);
        Problemsample problemsamples = problemService.getProblemHTMLProblemSample(pid);

        String user = req.getParameter("user");
        Boolean solve = false;
        if (user!=null){
            UserSolve userSolve = userSolveService.queryACProblem(user,pid);
            if(userSolve!=null) {
                solve = true;
            }
        }
        return JsonMsg.success().addInfo(problemView).addInfo(problem).addInfo(problemsamples).addInfo(solve);
    }
}
