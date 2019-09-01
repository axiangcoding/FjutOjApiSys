package com.fjut.oj.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author axiang [20190810]
 */
public interface CodeViewMapper {
    /**
     * 根据Pid, username来查询用户是否可以查看某个评测的代码
     *
     * @param username
     * @param pid
     * @return
     */
    Integer queryCanUserViewCodeByPid(@Param("username") String username, @Param("pid") Integer pid);
}
