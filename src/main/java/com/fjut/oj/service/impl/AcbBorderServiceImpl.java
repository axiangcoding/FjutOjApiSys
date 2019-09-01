package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.AcbBorderMapper;
import com.fjut.oj.pojo.AcbBorderPO;
import com.fjut.oj.service.AcbBorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author axiang [20190807]
 */
@Service("AcbBorderService")
public class AcbBorderServiceImpl implements AcbBorderService {

    @Autowired
    AcbBorderMapper acbBorderMapper;

    @Override
    public Integer insertAcbBorder(AcbBorderPO acbBorderDO) {
        return acbBorderMapper.insertAcbBorder(acbBorderDO);
    }
}
