package com.fjut.oj.service;

import com.fjut.oj.pojo.Order;

/**
 * @author axiang [20190807]
 */
public interface OrderService {
    /**
     * 插入订单
     * @param order
     * @return
     */
    boolean insertOrder(Order order);
}
