package com.fjut.oj.service;

import com.fjut.oj.pojo.MallPO;

import java.util.List;

/**
 * @author axiang [20190717]
 */
public interface MallService {
    List<MallPO> queryAllMallGoods();

    MallPO queryMallGoodsById(Integer id);
}
