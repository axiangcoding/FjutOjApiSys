package com.fjut.oj.mapper;

import com.fjut.oj.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cjt
 */
public interface UserMapper {

    /**
     * 插入用户信息
     *
     * @param user
     * @return
     */
    int insertUser(@Param("user") User user);


    /**
     * 根据用户名删除用户信息
     *
     * @param username
     */
    void deleteUserByUsername(@Param("username") String username);

    /**
     * 根据用户名更新用户信息
     *
     * @param user
     * @return
     */
    Integer updateUserByUsername(@Param("user") User user);

    /**
     * 增加一条AC数量
     *
     * @param username
     * @return
     */
    Integer updateACNumAddOneByUsername(@Param("username") String username);

    /**
     * 更新ACB数量
     *
     * @param username
     * @param acbChange
     * @return
     */
    Integer updateACBNumber(@Param("username") String username, @Param("acbChange") Integer acbChange);

    /**
     * 查询全部用户数量
     *
     * @return
     */
    Integer queryUserCount();

    User queryByUsernameAndPassword(@Param("username") String username);

    List<AwardInfo> queryAwardInfo(@Param("username") String username);

    /**
     * 通过用户名查找用户
     *
     * @param username
     * @return
     */
    User queryUserByUsername(@Param("username") String username);

    Integer getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);


    List<UserRadar1> queryUserRadar1(@Param("user") String user);

    List<UserRadar2> queryUserRadar2(@Param("user") String user);

    Integer queryPutTagNumByUsername(@Param("username") String username);

    List<Integer> queryStatusProblemsByUsername(@Param("status") Integer status, @Param("username") String username);

    List<Integer> queryCanViewCodeProblemsByUsername(@Param("username") String username);  // 查询用户已经贴过标签的题目

    List<User> queryRichTop10();

    List<User> queryAcnumTop10();

    List<Integer> queryUserPermission(@Param("username") String username);

    List<User> getRatingTop(@Param("from") int from, @Param("num") int num);

    List<User> getRichTop();

    List<User> getAcTop();

    List<RatingGraph> getRatingGraph(@Param("username") String username);


    Integer queryAcbNumber(@Param("username") String username);


}
