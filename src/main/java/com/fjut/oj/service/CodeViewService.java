package com.fjut.oj.service;

public interface CodeViewService {
    /**
     * 根据Pid, username来查询用户是否可以查看某个评测的代码
     * @param username
     * @param pid
     * @return
     */
    boolean queryCanUserViewCodeByPid(String username, Integer pid);
}
