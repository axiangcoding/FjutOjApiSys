package com.fjut.oj.pojo;

import java.util.Date;

/**
 * @author axiang [2019/8/7]
 */
public class AcbBorderPO {
    private Integer id;
    private String username;
    private Integer acbchange;
    private Integer reason;
    private String mark;
    private Date time;

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

    public Integer getAcbchange() {
        return acbchange;
    }

    public void setAcbchange(Integer acbchange) {
        this.acbchange = acbchange;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "AcbBorderPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", acbchange=" + acbchange +
                ", reason=" + reason +
                ", mark='" + mark + '\'' +
                ", time=" + time +
                '}';
    }
}
