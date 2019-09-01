package com.fjut.oj.controller;

import com.fjut.oj.pojo.*;
import com.fjut.oj.service.ProblemService;
import com.fjut.oj.service.ProblemViewService;
import com.fjut.oj.service.UserSolveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cjt
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
    public JsonInfoVO queryProblemView(@RequestParam("pid") String pidStr,
                                       @RequestParam(value = "username",required = false) String username)
    {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer pid = Integer.parseInt(pidStr);
        ProblemView problemView = problemViewService.queryProblemView(pid);
        Problem problem = problemService.queryProblemById(pid);
        ProblemSample problemSam;
        problemSam = problemService.getProblemHTMLProblemSample(pid);
        Boolean solve = false;
        if (username != null) {
            UserSolve userSolve = userSolveService.queryACProblem(username, pid);
            if (userSolve != null) {
                solve = true;
            }
        }
        JsonInfoVO.setSuccess();
        JsonInfoVO.addInfo(problemView);
        JsonInfoVO.addInfo(problem);
        JsonInfoVO.addInfo(problemSam);
        JsonInfoVO.addInfo(solve);
        return JsonInfoVO;

    }
}
