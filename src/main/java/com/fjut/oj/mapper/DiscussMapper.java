package com.fjut.oj.mapper;

import com.fjut.oj.pojo.DiscussPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [20190909]
 */
public interface DiscussMapper {
    /**
     * 根据条件查询一次30条讨论记录
     * @param startIndex
     * @return
     */
    List<DiscussPO> queryDiscussByConditions(@Param("startIndex") Integer startIndex);

//    List<DiscussBO> queryDiscussViewByConditions(@Param("startIndex") Integer startIndex);
}
