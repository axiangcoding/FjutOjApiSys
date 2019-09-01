package com.fjut.oj.controller;

import com.fjut.oj.pojo.Mall;
import com.fjut.oj.service.MallService;
import com.fjut.oj.util.JsonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author axiang [20190717]
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/mall")
public class MallController {
    @Autowired
    MallService mallService;

    @GetMapping("/getMallGoods")
    public JsonInfo queryMallGoods() {
        JsonInfo jsonInfo = new JsonInfo();
        List<Mall> malls = mallService.queryAllMallGoods();
        if (0 < malls.size()) {
            jsonInfo.setSuccess();
            jsonInfo.addInfo(malls);
        } else {
            jsonInfo.setFail("未找到商品");
        }
        return jsonInfo;
    }

    @GetMapping("/getMallGoodsById")
    public JsonInfo queryMallGoodsById(@RequestParam("id") Integer id) {
        JsonInfo jsonInfo = new JsonInfo();
        Mall mall = mallService.queryMallGoodsById(id);
        if (null != mall) {
            jsonInfo.setSuccess();
            jsonInfo.addInfo(mall);
        } else {
            jsonInfo.setFail("未找到该商品！");
        }
        return jsonInfo;
    }

}
