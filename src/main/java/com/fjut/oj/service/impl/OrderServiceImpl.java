package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.AcbBorderMapper;
import com.fjut.oj.mapper.OrderMapper;
import com.fjut.oj.mapper.UserMapper;
import com.fjut.oj.pojo.AcbBorderPO;
import com.fjut.oj.pojo.OrderPO;
import com.fjut.oj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: axiang [20190807]
 */

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AcbBorderMapper acbBorderMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean insertOrder(OrderPO orderDO) {
        try {
            Integer orderAns = orderMapper.insertOrder(orderDO);
            if (1 != orderAns) {

                throw new RuntimeException();
            }
            Integer userAcbChangeAns = userMapper.updateACBNumber(orderDO.getUsername(), -orderDO.getAcb());
            if (1 != userAcbChangeAns) {
                throw new RuntimeException();
            }
            AcbBorderPO acbBorderDO = new AcbBorderPO();
            acbBorderDO.setTime(orderDO.getTime());
            acbBorderDO.setMark("订单号：" + orderDO.getId());
            acbBorderDO.setReason(6);
            acbBorderDO.setAcbchange(-orderDO.getAcb());
            acbBorderDO.setUsername(orderDO.getUsername());
            Integer borderAns = acbBorderMapper.insertAcbBorder(acbBorderDO);
            if (1 != borderAns) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            throw e;
        }
        return true;

    }
}
