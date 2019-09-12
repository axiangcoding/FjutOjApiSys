package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.DiscussMapper;
import com.fjut.oj.pojo.DiscussBO;
import com.fjut.oj.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author axiang [20190909]
 */
@Service("DiscussService")
public class DiscussServiceImpl implements DiscussService {
    @Autowired
    DiscussMapper discussMapper;

    @Override
    public List<DiscussBO> queryDiscussByConditions(Integer startIndex) {
        return null;
    }
}
