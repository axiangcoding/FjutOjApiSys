package com.fjut.oj.controller;

import com.fjut.oj.pojo.CeInfo;
import com.fjut.oj.service.CeinfoService;
import com.fjut.oj.util.JsonInfo;
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
    public JsonInfo queryCeInfo(@RequestParam("rid") String ridStr) {
        JsonInfo jsonInfo = new JsonInfo();
        if (null == ridStr || ("").equals(ridStr)) {
            jsonInfo.setError("参数错误！");
            return jsonInfo;
        }
        Integer rid = Integer.parseInt(ridStr);
        CeInfo ceinfo = ceinfoService.queryCeinfo(rid);
        jsonInfo.addInfo(ceinfo);

        return jsonInfo;
    }
}
