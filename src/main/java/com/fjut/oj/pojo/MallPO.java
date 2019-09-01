package com.fjut.oj.pojo;

import java.util.Date;

/**
 * 商城
 *
 * @author axiang [20190717]
 */
public class MallPO {
    private Integer id;
    private String title;
    private Integer acb;
    private Integer stock;
    private String des;
    private Boolean isHidden;
    private String user;
    private Date time;
    private Integer buyLimit;
    private Integer buyVerifyLimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAcb() {
        return acb;
    }

    public void setAcb(Integer acb) {
        this.acb = acb;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean hidden) {
        isHidden = hidden;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getBuyLimit() {
        return buyLimit;
    }

    public void setBuyLimit(Integer buyLimit) {
        this.buyLimit = buyLimit;
    }

    public Integer getBuyVerifyLimit() {
        return buyVerifyLimit;
    }

    public void setBuyVerifyLimit(Integer buyVerifyLimit) {
        this.buyVerifyLimit = buyVerifyLimit;
    }

    @Override
    public String toString() {
        return "MallPO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", acb=" + acb +
                ", stock=" + stock +
                ", des='" + des + '\'' +
                ", isHidden=" + isHidden +
                ", user='" + user + '\'' +
                ", time=" + time +
                ", buyLimit=" + buyLimit +
                ", buyVerifyLimit=" + buyVerifyLimit +
                '}';
    }
}
