package com.fjut.oj.controller;

import com.fjut.oj.interceptor.CheckUserPrivate;
import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.pojo.MallPO;
import com.fjut.oj.pojo.OrderPO;
import com.fjut.oj.service.AcbBorderService;
import com.fjut.oj.service.MallService;
import com.fjut.oj.service.OrderService;
import com.fjut.oj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author axiang [20190807]
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
    public JsonInfoVO insertOrder(@RequestParam("username") String username,
                                  @RequestParam("goodsId") Integer goodsId,
                                  @RequestParam("buyNum") Integer buyNum) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer acbNumber = userService.queryAcbNumber(username);
        MallPO mall = mallService.queryMallGoodsById(goodsId);
        if (null == mall) {
            JsonInfoVO.setFail("购买失败！商品不存在！");
            return JsonInfoVO;
        }
        if (acbNumber < mall.getAcb() * buyNum) {
            JsonInfoVO.setFail("购买失败！ACB不足！");
            return JsonInfoVO;
        }
        if (buyNum > mall.getBuyLimit() || buyNum > mall.getStock()) {
            JsonInfoVO.setFail("购买失败！购买超出限制！");
            return JsonInfoVO;
        }

        Integer acbChange = mall.getAcb() * buyNum;
        Date currentDate = new Date();
        OrderPO orderDO = new OrderPO();
        orderDO.setUsername(username);
        orderDO.setAcb(acbChange);
        orderDO.setIsCancel(false);
        orderDO.setGoodsId(goodsId);
        orderDO.setTime(currentDate);
        // TODO:购买成功虚拟物品后添加记录的逻辑还没写
        // 更新订单记录
        boolean isOrderCompleted = orderService.insertOrder(orderDO);
        if(isOrderCompleted)
        {
            JsonInfoVO.setSuccess("购买成功！");
        }
        else{
            JsonInfoVO.setFail("购买出错！");
        }
        return JsonInfoVO;
    }
}
