package com.fjut.oj.controller;

import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.pojo.MallPO;
import com.fjut.oj.service.MallService;
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
    public JsonInfoVO queryMallGoods() {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        List<MallPO> malls = mallService.queryAllMallGoods();
        if (0 < malls.size()) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(malls);
        } else {
            JsonInfoVO.setFail("未找到商品");
        }
        return JsonInfoVO;
    }

    @GetMapping("/getMallGoodsById")
    public JsonInfoVO queryMallGoodsById(@RequestParam("id") Integer id) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        MallPO mall = mallService.queryMallGoodsById(id);
        if (null != mall) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(mall);
        } else {
            JsonInfoVO.setFail("未找到该商品！");
        }
        return JsonInfoVO;
    }

}
