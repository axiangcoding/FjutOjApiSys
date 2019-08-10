package com.fjut.oj.service;

import com.fjut.oj.pojo.UserPer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author axiang
 */

public interface UserPermissionService {
    /**
     * 获取用户权限列表
     * @param username
     * @return
     */
    List<UserPer> queryUserPermission(String username);

    boolean queryUserPermissionAvailable(String username,Integer id);

    /**
     * 判断是非为管理员
     * @param username
     * @return
     */
    boolean queryIsAdmin(String username);
}
