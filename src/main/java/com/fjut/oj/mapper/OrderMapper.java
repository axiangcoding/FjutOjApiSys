package com.fjut.oj.mapper;

import com.fjut.oj.pojo.TableOrder;
import org.apache.ibatis.annotations.Param;

/**
 * @author axiang [20190807]
 */
public interface OrderMapper {
    /**
     * 插入订单信息
     * @param tableOrder
     * @return
     */
    Integer insertOrder(@Param("tableOrder") TableOrder tableOrder);
}
