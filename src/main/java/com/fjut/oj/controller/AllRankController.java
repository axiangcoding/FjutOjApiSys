package com.fjut.oj.controller;

import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.pojo.UserPO;
import com.fjut.oj.service.AllTopTenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author axiang [20190815]
 */
@Controller
@CrossOrigin
@ResponseBody
public class AllRankController {

    @Autowired
    private AllTopTenService allTopTenService;

    @GetMapping("/getAllTopBorder")
    public JsonInfoVO getAllTopBorder() {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        List<UserPO> list = allTopTenService.getAcbTOP();
        List<UserPO> list_1 = allTopTenService.getRatingTop();
        List<UserPO> list_2 = allTopTenService.getAcTOP();
        // TODO: 活跃榜还没做

        JsonInfoVO.setSuccess();
        JsonInfoVO.addInfo(list);
        JsonInfoVO.addInfo(list_1);
        JsonInfoVO.addInfo(list_2);
        return JsonInfoVO;
    }
}
