package com.fjut.oj.mapper;

import com.fjut.oj.pojo.UserSolve;
import org.apache.ibatis.annotations.Param;

/**
 * @author cjt
 */
public interface UserSolveMapper {

    Integer replaceUserSolve(@Param("username") String username, @Param("pid") Integer pid, @Param("status") Integer status);

    UserSolve queryByUsernameAndPid(@Param("username") String username, @Param("pid") Integer pid);

    UserSolve queryACProblem(@Param("username") String username, @Param("pid") Integer pid);


}
