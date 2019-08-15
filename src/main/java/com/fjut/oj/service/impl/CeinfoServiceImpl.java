package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.CeInfoMapper;
import com.fjut.oj.pojo.CeInfo;
import com.fjut.oj.service.CeinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ceinfoService")
public class CeinfoServiceImpl implements CeinfoService {

    @Autowired
    private CeInfoMapper ceinfoMapper;

    @Override
    public List<CeInfo> queryAllCeinfo() {
        return ceinfoMapper.queryAllCeInfo();
    }

    @Override
    public CeInfo queryCeinfo(Integer rid) {
        return ceinfoMapper.queryCeInfo(rid);
    }

    @Override
    public boolean insertCeinfo(CeInfo ce) {
        Integer num = ceinfoMapper.insertCeInfo(ce);
        return num != 0;
    }
}
