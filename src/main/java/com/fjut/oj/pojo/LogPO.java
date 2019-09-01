package com.fjut.oj.pojo;

import java.util.Date;

/**
 * @author axiang [20190901]
 */
public class LogPO {
    private Integer id;
    private Date time;
    private String ipAddress;
    private String text;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    @Override
    public String toString() {
        return "LogPO{" +
                "id=" + id +
                ", time=" + time +
                ", ipAddress='" + ipAddress + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}