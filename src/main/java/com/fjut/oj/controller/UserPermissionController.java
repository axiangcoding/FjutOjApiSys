package com.fjut.oj.controller;

import com.fjut.oj.interceptor.CheckUserPrivate;
import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.pojo.UserPermissionPO;
import com.fjut.oj.service.UserPermissionService;
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
    public JsonInfoVO queryUserPermission(@RequestParam("username") String username) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        List<UserPermissionPO> list = permissionService.queryUserPermission(username);
        List<Integer> perList = new ArrayList<>();
        for (UserPermissionPO per : list) {
            perList.add(per.getPerid());
        }
        if (0 < perList.size()) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(perList);
        } else {
            JsonInfoVO.setFail("未找到权限！");
        }
        return JsonInfoVO;
    }

    @CheckUserPrivate
    @GetMapping("/getUserPermissionById")
    public JsonInfoVO queryUserPermissionById(@RequestParam("username") String username,
                                            @RequestParam("id") String idStr) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer id = Integer.parseInt(idStr);
        boolean isAvailable = permissionService.queryUserPermissionAvailable(username, id);
        if(isAvailable)
        {
            JsonInfoVO.setSuccess("拥有权限");
        }
        else{
            JsonInfoVO.setFail("没有这项权限");
        }
        return JsonInfoVO;
    }
}