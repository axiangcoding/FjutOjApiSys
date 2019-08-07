package com.fjut.oj.pojo;

import java.util.Date;

/**
 * @Author: axiang [2019/8/7]
 */
public class Order {
    private Integer id;
    private String username;
    private Integer goodsId;
    private Integer acb;
    private Date time;
    private boolean isCancel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAcb() {
        return acb;
    }

    public void setAcb(Integer acb) {
        this.acb = acb;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(boolean cancel) {
        isCancel = cancel;
    }
}
