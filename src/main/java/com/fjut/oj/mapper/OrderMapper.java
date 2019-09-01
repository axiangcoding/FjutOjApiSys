package com.fjut.oj.mapper;

import com.fjut.oj.pojo.OrderPO;
import org.apache.ibatis.annotations.Param;

/**
 * @author axiang [20190807]
 */
public interface OrderMapper {
    /**
     * 插入订单信息
     * @param orderDO
     * @return
     */
    Integer insertOrder(@Param("orderDO") OrderPO orderDO);
}
