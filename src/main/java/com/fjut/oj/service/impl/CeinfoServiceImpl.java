package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.CeinfoMapper;
import com.fjut.oj.pojo.Ceinfo;
import com.fjut.oj.service.CeinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ceinfoService")
public class CeinfoServiceImpl implements CeinfoService {

    @Autowired
    private CeinfoMapper ceinfoMapper;

    @Override
    public List<Ceinfo> queryAllCeinfo() {
        return ceinfoMapper.queryAllCeinfo();
    }

    @Override
    public Ceinfo queryCeinfo(Integer rid) {
        return ceinfoMapper.queryCeinfo(rid);
    }

    @Override
    public boolean insertCeinfo(Ceinfo ce) {
        Integer num = ceinfoMapper.insertCeinfo(ce);
        return num != 0;
    }
}
