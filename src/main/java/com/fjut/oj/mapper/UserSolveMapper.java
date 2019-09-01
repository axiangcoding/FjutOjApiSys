package com.fjut.oj.mapper;

import com.fjut.oj.pojo.UserSolve;
import org.apache.ibatis.annotations.Param;

/**
 * @author cjt
 */
public interface UserSolveMapper {

    /**
     * 更新用户解决题目内容
     *
     * @param username
     * @param pid
     * @param status
     * @return
     */
    Integer replaceUserSolve(@Param("username") String username, @Param("pid") Integer pid, @Param("status") Integer status);

    /**
     * 根据Pid和用户名查询用户解决题目内容
     *
     * @param username
     * @param pid
     * @return
     */
    UserSolve queryByUsernameAndPid(@Param("username") String username, @Param("pid") Integer pid);

    /**
     * 根据用户名查询AC题目
     *
     * @param username
     * @param pid
     * @return
     */
    UserSolve queryACProblemByUsername(@Param("username") String username, @Param("pid") Integer pid);


}
