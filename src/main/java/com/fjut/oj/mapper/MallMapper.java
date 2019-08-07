package com.fjut.oj.mapper;

import com.fjut.oj.pojo.Mall;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: axiang [20190717]
 */
public interface MallMapper {

    List<Mall> queryAllMallGoods();

    Mall queryMallGoodsById(@Param("id")Integer id);


}
