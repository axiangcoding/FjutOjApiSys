package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.MallMapper;
import com.fjut.oj.pojo.Mall;
import com.fjut.oj.service.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: axiang [20190717]
 */
@Service("MallService")
public class MallServiceImpl implements MallService {
    @Autowired
    MallMapper mallMapper;

    @Override
    public List<Mall> queryAllMallGoods() {
        return mallMapper.queryAllMallGoods();
    }

    @Override
    public Mall queryMallGoodsById(Integer id) {
        return mallMapper.queryMallGoodsById(id);
    }
}
