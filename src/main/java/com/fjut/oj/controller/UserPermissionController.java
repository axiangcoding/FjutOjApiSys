package com.fjut.oj.controller;

import com.fjut.oj.interceptor.CheckUserPrivate;
import com.fjut.oj.pojo.UserPer;
import com.fjut.oj.service.UserPermissionService;
import com.fjut.oj.util.JsonInfo;
import net.sf.json.JSON;
import org.jsoup.select.Evaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author axiang [20190708]
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/permission")
public class UserPermissionController {

    @Autowired
    private UserPermissionService permissionService;


    /**
     * 查询一个用户所有的权限
     */
    @CheckUserPrivate
    @GetMapping("/getUserPermission")
    public JsonInfo queryUserPermission(@RequestParam("username") String username) {
        JsonInfo jsonInfo = new JsonInfo();
        List<UserPer> list = permissionService.queryUserPermission(username);
        List<Integer> perList = new ArrayList<>();
        for (UserPer per : list) {
            perList.add(per.getPerid());
        }
        if (0 < perList.size()) {
            jsonInfo.setSuccess();
            jsonInfo.addInfo(perList);
        } else {
            jsonInfo.setFail("未找到权限！");
        }
        return jsonInfo;
    }

    @CheckUserPrivate
    @GetMapping("/getUserPermissionById")
    public JsonInfo queryUserPermissionById(@RequestParam("username") String username,
                                            @RequestParam("id") String idStr) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer id = Integer.parseInt(idStr);
        boolean isAvailable = permissionService.queryUserPermissionAvailable(username, id);
        if(isAvailable)
        {
            jsonInfo.setSuccess("拥有权限");
        }
        else{
            jsonInfo.setFail("没有这项权限");
        }
        return jsonInfo;
    }
}