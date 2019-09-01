package com.fjut.oj.controller;

import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.pojo.ProblemTagPO;
import com.fjut.oj.service.ProblemTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author cjt
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/problemTag")
public class ProblemTagController {

    @Autowired
    private ProblemTagService problemTagService;

    @GetMapping("/getAllProblemTag")
    public JsonInfoVO queryAllProblemTag() {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        List<ProblemTagPO> list = problemTagService.queryAllProblemTag();
        if (0 < list.size()) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(list);
        } else {
            JsonInfoVO.setFail("未找到标签");
        }
        return JsonInfoVO;
    }
}
