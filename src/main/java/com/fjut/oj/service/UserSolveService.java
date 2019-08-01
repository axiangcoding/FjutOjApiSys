package com.fjut.oj.service;

import com.fjut.oj.pojo.UserSolve;

public interface UserSolveService {

    UserSolve queryByUsernameAndPid(String username, Integer pid);

    UserSolve queryACProblem(String username, Integer pid);

    Integer replaceUserSolve(String username, Integer pid, Integer status);

}
