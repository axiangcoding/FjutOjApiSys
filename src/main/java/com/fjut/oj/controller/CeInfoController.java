package com.fjut.oj.controller;

import com.fjut.oj.pojo.CeInfoPO;
import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.service.CeinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author axiang [20190705]
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/ceinfo")
public class CeInfoController {

    @Autowired
    private CeinfoService ceinfoService;

    @GetMapping("/getCeInfo")
    public JsonInfoVO queryCeInfo(@RequestParam("rid") String ridStr) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        if (null == ridStr || ("").equals(ridStr)) {
            JsonInfoVO.setError("参数错误！");
            return JsonInfoVO;
        }
        Integer rid = Integer.parseInt(ridStr);
        CeInfoPO ceinfo = ceinfoService.queryCeinfo(rid);
        JsonInfoVO.addInfo(ceinfo);

        return JsonInfoVO;
    }
}
