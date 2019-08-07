package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.AcbBorderMapper;
import com.fjut.oj.mapper.OrderMapper;
import com.fjut.oj.mapper.UserMapper;
import com.fjut.oj.pojo.AcbBorder;
import com.fjut.oj.pojo.Order;
import com.fjut.oj.service.OrderService;
import org.codehaus.jackson.node.BooleanNode;
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
    public boolean insertOrder(Order order) {
        boolean isOrderCompleted = true;
        try{
            Integer orderAns = orderMapper.insertOrder(order);
            if(1 != orderAns)
            {
                isOrderCompleted = false;
                throw new RuntimeException();
            }
            Integer userAcbChangeAns = userMapper.updateAcbNumber(order.getUsername(), -order.getAcb());
            if(1 != userAcbChangeAns)
            {
                System.out.println("出错了！");
                isOrderCompleted = false;
                throw new RuntimeException();
            }
            AcbBorder acbBorder =  new AcbBorder();
            acbBorder.setTime(order.getTime());
            acbBorder.setMark("订单号："+order.getId());
            acbBorder.setReason(6);
            acbBorder.setAcbchange(-order.getAcb());
            acbBorder.setUsername(order.getUsername());
            Integer borderAns = acbBorderMapper.insertAcbBorder(acbBorder);
            if(1 != borderAns)
            {
                isOrderCompleted = false;
                throw new RuntimeException();
            }
        }catch (RuntimeException e)
        {
            throw e;
        }finally {
            return isOrderCompleted;
        }
    }
}
