package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.AcbBorderMapper;
import com.fjut.oj.mapper.OrderMapper;
import com.fjut.oj.mapper.UserMapper;
import com.fjut.oj.pojo.AcbBorder;
import com.fjut.oj.pojo.TableOrder;
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
    public boolean insertOrder(TableOrder tableOrder) {
        try {
            Integer orderAns = orderMapper.insertOrder(tableOrder);
            if (1 != orderAns) {

                throw new RuntimeException();
            }
            Integer userAcbChangeAns = userMapper.updateACBNumber(tableOrder.getUsername(), -tableOrder.getAcb());
            if (1 != userAcbChangeAns) {
                throw new RuntimeException();
            }
            AcbBorder acbBorder = new AcbBorder();
            acbBorder.setTime(tableOrder.getTime());
            acbBorder.setMark("订单号：" + tableOrder.getId());
            acbBorder.setReason(6);
            acbBorder.setAcbchange(-tableOrder.getAcb());
            acbBorder.setUsername(tableOrder.getUsername());
            Integer borderAns = acbBorderMapper.insertAcbBorder(acbBorder);
            if (1 != borderAns) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            throw e;
        }
        return true;

    }
}
