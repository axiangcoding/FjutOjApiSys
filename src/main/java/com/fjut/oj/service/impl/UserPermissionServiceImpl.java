package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.UserPermissionMapper;
import com.fjut.oj.pojo.UserPer;
import com.fjut.oj.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author axiang
 */
@Service("UserPermissionService")
public class UserPermissionServiceImpl implements UserPermissionService {
    @Autowired
    private UserPermissionMapper permissionMapper;

    @Override
    public List<UserPer> queryUserPermission(String username) {
        return permissionMapper.queryUserPermission(username);
    }

    @Override
    public boolean queryIsAdmin(String username) {
        Integer permissionListCount = permissionMapper.queryIsAdmin(username);
        return (permissionListCount == 0) ? false : true;
    }

}
