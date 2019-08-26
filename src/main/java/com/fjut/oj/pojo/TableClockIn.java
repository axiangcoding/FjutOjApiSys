package com.fjut.oj.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: axiang [20190610]
 */
public class TableClockIn {
    private Integer id;
    private String username;
    private Date time;
    private String sign;
    private String ip;
    private Integer todytimes;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getTodytimes() {
        return todytimes;
    }

    public void setTodytimes(Integer todytimes) {
        this.todytimes = todytimes;
    }

    @Override
    public String toString() {
        String str = "id: " + id
                + " username: " + username
                + " time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time)
                + " sign: " + sign
                + " ip: " + ip
                + " todytimes: " + todytimes;
        return str;
    }
}

