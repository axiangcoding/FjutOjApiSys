package com.fjut.oj.controller;

import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.pojo.ProblemDifficultPO;
import com.fjut.oj.service.ProblemDifficultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author axiang [20190909]
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/problemDiff")
public class ProblemDiffController {
    @Autowired
    ProblemDifficultService problemDifficultService;

    @GetMapping("/getProblemDiff")
    public JsonInfoVO getProblemDiff()
    {
        JsonInfoVO jsonInfoVO = new JsonInfoVO();
        List<ProblemDifficultPO> problemDifficult = problemDifficultService.queryAllProblemDiff();
        jsonInfoVO.addInfo(problemDifficult);

        return jsonInfoVO;
    }
}
