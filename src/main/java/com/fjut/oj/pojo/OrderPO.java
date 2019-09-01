package com.fjut.oj.pojo;

import java.util.Date;

/**
 * 订单表
 *
 * @Author: axiang [20190807]
 */
public class OrderPO {
    private Integer id;
    private String username;
    private Integer goodsId;
    private Integer acb;
    private Date time;
    private Boolean isCancel;

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

    public Boolean getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Boolean cancel) {
        isCancel = cancel;
    }

    @Override
    public String toString() {
        return "OrderPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", goodsId=" + goodsId +
                ", acb=" + acb +
                ", time=" + time +
                ", isCancel=" + isCancel +
                '}';
    }
}
