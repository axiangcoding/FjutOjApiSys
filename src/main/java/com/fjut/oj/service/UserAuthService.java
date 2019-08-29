package com.fjut.oj.service;

import java.util.Date;

/**
 * @author axiang [20190828]
 */
public interface UserAuthService {

    /**
     * 根据用户名查询该用户名是否存在
     * @param username
     * @return
     */
    boolean queryUserExistByUsername(String username);

    /**
     * 根据用户名密码查询用户是否登录成功
     *
     * @param username
     * @param password
     * @return
     */
    boolean queryUserAuthByUsernameAndPassword(String username, String password);

    /**
     * 根据用户名查询用户密码盐值
     *
     * @param username
     * @return
     */
    String queryUserSaltByUsername(String username);

    /**
     * 查询用户登录的解锁时间
     * @param username
     * @return
     */
    Date queryUserUnlockTimeByUsername(String username);

    /**
     * 查询用户的登录失败次数
     *
     * @param username
     * @return
     */
    Integer queryUserAuthAttemptNumberByUsername(String username);

}
