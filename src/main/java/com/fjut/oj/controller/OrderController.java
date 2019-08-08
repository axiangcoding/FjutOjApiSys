package com.fjut.oj.controller;

import com.fjut.oj.interceptor.CheckUserPrivate;
import com.fjut.oj.pojo.AcbBorder;
import com.fjut.oj.pojo.Mall;
import com.fjut.oj.pojo.Order;
import com.fjut.oj.service.AcbBorderService;
import com.fjut.oj.service.MallService;
import com.fjut.oj.service.OrderService;
import com.fjut.oj.service.UserService;
import com.fjut.oj.util.JsonInfo;
import org.codehaus.jackson.node.BooleanNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author: axiang [20190807]
 */
@Controller
@RequestMapping("/order")
@CrossOrigin
@ResponseBody
public class OrderController {
    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    MallService mallService;

    @Autowired
    AcbBorderService acbBorderService;

    /**
     * TODO: 暂时未设置购买权限的限制
     * 购买商品
     * @param username
     * @param goodsId
     * @param buyNum
     * @return
     */
    @CheckUserPrivate
    @PostMapping("/createOrder")
    public JsonInfo insertOrder(@RequestParam("username") String username,
                                @RequestParam("goodsId") Integer goodsId,
                                @RequestParam("buyNum") Integer buyNum) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer acbNumber = userService.queryAcbNumber(username);
        Mall mall = mallService.queryMallGoodsById(goodsId);
        if (null == mall) {
            jsonInfo.setFail("购买失败！商品不存在！");
            return jsonInfo;
        }
        if (acbNumber < mall.getAcb() * buyNum) {
            jsonInfo.setFail("购买失败！ACB不足！");
            return jsonInfo;
        }
        if (buyNum > mall.getBuyLimit() || buyNum > mall.getStock()) {
            jsonInfo.setFail("购买失败！购买超出限制！");
            return jsonInfo;
        }

        Integer acbChange = mall.getAcb() * buyNum;
        Date currentDate = new Date();
        Order order = new Order();
        order.setUsername(username);
        order.setAcb(acbChange);
        order.setIsCancel(false);
        order.setGoodsId(goodsId);
        order.setTime(currentDate);
        // TODO:购买成功虚拟物品后添加记录的逻辑还没写
        // 更新订单记录
        boolean isOrderCompleted = orderService.insertOrder(order);
        if(isOrderCompleted)
        {
            jsonInfo.setSuccess("购买成功！");
        }
        else{
            jsonInfo.setFail("购买出错！");
        }
        return jsonInfo;
    }
}
