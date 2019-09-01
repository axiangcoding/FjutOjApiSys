package com.fjut.oj.service;

import com.fjut.oj.pojo.OrderPO;

/**
 * @author axiang [20190807]
 */
public interface OrderService {
    /**
     * 插入订单
     * @param orderDO
     * @return
     */
    boolean insertOrder(OrderPO orderDO);
}
