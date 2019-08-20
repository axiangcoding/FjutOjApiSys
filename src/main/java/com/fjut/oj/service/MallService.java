package com.fjut.oj.service;

import com.fjut.oj.pojo.Mall;

import java.util.List;

/**
 * @author axiang [20190717]
 */
public interface MallService {
    List<Mall> queryAllMallGoods();

    Mall queryMallGoodsById(Integer id);
}
