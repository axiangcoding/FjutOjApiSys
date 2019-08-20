package com.fjut.oj.service;

import com.fjut.oj.pojo.User;
import org.apache.ibatis.type.IntegerTypeHandler;

import java.util.List;

/**
 * @author cjt
 */
public interface UserService {
    /**
     * 查询全部用户数量
     *
     * @return
     */
    int queryUserCount();

    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    boolean insertUser(User user);

    int updateUserByUsername(User user);             // 通过用户名更新用户

    User getUserByUsername(String username);          // 通过用户名查找用户

    Integer getUserByUsernameAndPassword(String username, String password); // 用户名密码是否匹配

    void deleteUserByUsername(String username);     // 通过用户名删除用户

    Integer queryPutTagNumByUsername(String username);   // 查询一个用户贴标签的数量

    List<Integer> queryStatusProblemsByUsername(Integer status, String username); // 查询用户通过和未通过的题目  status 为 1 获取user已经AC题目列表 为 0 获取user提交过但没有AC的题目列表（不包括contest内的）

    List<Integer> queryNotPutTagProblemsByUsername(String username); // 查询用户未贴标签的题目

    List<User> queryRichTop10();                  // 获取用户 ACB 排行榜前10

    List<User> queryAcNumTop10();                 // 获取用户 AC 题目数量前10

    /**
     * 获取用户权限
     *
     * @param username
     * @return
     */
    List<Integer> queryUserPermission(String username);

    List<String> queryAwardInfo(String username);

    Object getRatingGraph(String username);

    Integer addAcnum(String username);

    Object getAcGraph(String username);

    Integer queryAcbNumber(String username);

    Integer updateAcbNumber(String username, Integer acbChange);
}
