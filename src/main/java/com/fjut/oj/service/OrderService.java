package com.fjut.oj.service;

import com.fjut.oj.pojo.TableOrder;

/**
 * @author axiang [20190807]
 */
public interface OrderService {
    /**
     * 插入订单
     * @param tableOrder
     * @return
     */
    boolean insertOrder(TableOrder tableOrder);
}
