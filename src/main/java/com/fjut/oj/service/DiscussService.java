package com.fjut.oj.service;

import com.fjut.oj.pojo.DiscussBO;

import java.util.List;

/**
 * @author axiang [20190909]
 */
public interface DiscussService {
    /**
     * 根据条件查询一次30条讨论记录
     * @param startIndex
     * @return
     */
    List<DiscussBO> queryDiscussByConditions(Integer startIndex);


}
