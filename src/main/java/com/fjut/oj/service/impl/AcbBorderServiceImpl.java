package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.AcbBorderMapper;
import com.fjut.oj.pojo.AcbBorder;
import com.fjut.oj.service.AcbBorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: axiang [2019/8/7]
 */
@Service("AcbBorderService")
public class AcbBorderServiceImpl implements AcbBorderService {

    @Autowired
    AcbBorderMapper acbBorderMapper;

    @Override
    public Integer insertAcbBorder(AcbBorder acbBorder) {
        return acbBorderMapper.insertAcbBorder(acbBorder);
    }
}
