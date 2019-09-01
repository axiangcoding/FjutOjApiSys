package com.fjut.oj.controller;

import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.pojo.KeyValuePO;
import com.fjut.oj.service.KeyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author axiang [20190829]
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/system")
public class SystemController {
    @Autowired
    KeyValueService keyValueService;

    @GetMapping("/getSystemValue")
    public JsonInfoVO queryKeyValue(@RequestParam("key") String key) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        KeyValuePO keyValue = keyValueService.queryValueByKey(key);
        if(null == keyValue)
        {
            JsonInfoVO.setFail("找不到键值");
            return JsonInfoVO;
        }
        JsonInfoVO.setSuccess();
        JsonInfoVO.addInfo(keyValue);
        return JsonInfoVO;
    }
}
