package com.fjut.oj.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [20190810]
 */
public interface CodeViewMapper {
    Integer queryCanUserViewCodeByPid(@Param("username") String username, @Param("pid") Integer pid);
}
