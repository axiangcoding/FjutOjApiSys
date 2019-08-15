package com.fjut.oj.mapper;

import com.fjut.oj.pojo.Mall;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [20190717]
 */
public interface MallMapper {

    /**
     * 查询全部商品信息
     * @return
     */
    List<Mall> queryAllMallGoods();

    /**
     * 根据 商品id 查询商品
     * @param id
     * @return
     */
    Mall queryMallGoodsById(@Param("id")Integer id);


}
