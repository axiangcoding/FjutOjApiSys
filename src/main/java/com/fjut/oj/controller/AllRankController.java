package com.fjut.oj.controller;

import com.fjut.oj.pojo.User;
import com.fjut.oj.service.AllTopTenService;
import com.fjut.oj.util.JsonInfo;
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
    public JsonInfo getAllTopBorder() {
        JsonInfo jsonInfo = new JsonInfo();
        List<User> list = allTopTenService.getAcbTOP();
        List<User> list_1 = allTopTenService.getRatingTop();
        List<User> list_2 = allTopTenService.getAcTOP();
        // TODO: 活跃榜还没做

        jsonInfo.setSuccess();
        jsonInfo.addInfo(list);
        jsonInfo.addInfo(list_1);
        jsonInfo.addInfo(list_2);
        return jsonInfo;
    }
}
