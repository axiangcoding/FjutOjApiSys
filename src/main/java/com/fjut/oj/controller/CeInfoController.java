package com.fjut.oj.controller;

import com.fjut.oj.pojo.Ceinfo;
import com.fjut.oj.service.CeinfoService;
import com.fjut.oj.util.JsonInfo;
import com.fjut.oj.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public JsonInfo queryCeInfo(@RequestParam("rid")String ridStr){
        JsonInfo jsonInfo = new JsonInfo();
        if (null == ridStr || ("").equals(ridStr)){
            jsonInfo.setError("参数错误！");
        }else{
            Integer rid = Integer.parseInt(ridStr);
            Ceinfo ceinfo = ceinfoService.queryCeinfo(rid);
            jsonInfo.addInfo(ceinfo);
        }
        return jsonInfo;
    }
}
