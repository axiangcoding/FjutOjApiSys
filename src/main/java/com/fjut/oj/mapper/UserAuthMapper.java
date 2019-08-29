package com.fjut.oj.mapper;

import com.fjut.oj.pojo.TableUserAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author axiang [20190828]
 */
public interface UserAuthMapper {

    /**
     * 插入一条用户登录权限信息
     *
     * @param tableUserAuth
     * @return
     */
    Integer insertUserAuth(@Param("tableUserAuth") TableUserAuth tableUserAuth);

    /**
     * 用户登录的尝试次数+1
     *
     * @param username
     * @return
     */
    Integer updateUserAuthAttemptFailAddOneByUsername(@Param("username") String username);

    /**
     * 用户登录的尝试次数清空
     *
     * @param username
     * @return
     */
    Integer updateUserAuthAttemptFailSetZeroByUsername(@Param("username") String username);

    /**
     * 更新用户登录的解锁时间
     *
     * @param unlockTime
     * @param username
     * @return
     */
    Integer updateUserAuthUnlockTimeByUsername(@Param("unlockTime") Date unlockTime,
                                               @Param("username") String username);

    /**
     * 查询用户名是否存在
     *
     * @param username
     * @return
     */
    Integer queryUserAuthCountByUsername(String username);

    /**
     * 根据用户名密码查询满足的记录数
     *
     * @param username
     * @param password
     * @return
     */
    Integer queryUserAuthByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询用户密码盐值
     *
     * @param username
     * @return
     */
    String queryUserAuthSaltByUsername(@Param("username") String username);

    /**
     * 查询用户的登录失败次数
     *
     * @param username
     * @return
     */
    Integer queryUserAuthAttemptNumberByUsername(@Param("username") String username);

    /**
     * 查询用户登录的解锁时间
     *
     * @param username
     * @return
     */
    Date queryUserAuthUnlockTimeByUsername(@Param("username") String username);


}
