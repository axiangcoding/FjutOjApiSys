package com.fjut.oj.mapper;

import com.fjut.oj.pojo.UserPer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPermissionMapper {
    /**
     * 获取用户权限详情
     * @param username
     * @return
     */
    List<UserPer> queryUserPermission(@Param("username") String username);

    /**
     * 获取用户的某项权限是否存在
     * @param username
     * @param id
     * @return
     */
    Integer queryUserPermissionAvailable(@Param("username") String username, @Param("id") Integer id);

    /**
     * 获取用户是否为管理员
     * @param username
     * @return
     */
    Integer queryIsAdmin(@Param("username") String username);
}
