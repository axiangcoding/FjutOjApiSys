package com.fjut.oj.mapper;

import com.fjut.oj.pojo.NewDiscuss;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cjt
 */
public interface NewDiscussMapper {

    List<NewDiscuss> queryDiscussByPage(@Param("start") Integer start);

    Integer queryDiscussCount();

    Integer insertDiscuss(@Param("newDiscuss") NewDiscuss newDiscuss);

    Integer queryMaxCountDiscussId();

    Integer queryDiscussCountById(@Param("id") Integer id);

    Integer updateDisscussPirority(@Param("id") Integer id, @Param("priority") Double priority);
}
