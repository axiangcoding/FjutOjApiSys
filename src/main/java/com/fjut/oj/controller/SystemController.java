package com.fjut.oj.controller;

import com.fjut.oj.pojo.TableKeyValue;
import com.fjut.oj.service.KeyValueService;
import com.fjut.oj.util.JsonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author axiang [2019/8/29]
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/system")
public class SystemController {
    @Autowired
    KeyValueService keyValueService;

    @GetMapping("/getSystemValue")
    public JsonInfo queryKeyValue(@RequestParam("key") String key) {
        JsonInfo jsonInfo = new JsonInfo();
        TableKeyValue keyValue = keyValueService.queryValueByKey(key);
        if(null == keyValue)
        {
            jsonInfo.setFail("找不到键值");
            return jsonInfo;
        }
        jsonInfo.setSuccess();
        jsonInfo.addInfo(keyValue);
        return jsonInfo;
    }
}
