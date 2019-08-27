package com.fjut.oj.pojo;

import java.util.Date;

// 讨论答复表
public class t_replyreply {
    private Integer did;
    private Integer rid;
    private Integer rrid;
    private Integer replyRid;
    private String  username;
    private Date    time;
    private String  text;
    private Integer visible;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getRrid() {
        return rrid;
    }

    public void setRrid(Integer rrid) {
        this.rrid = rrid;
    }

    public Integer getReplyRid() {
        return replyRid;
    }

    public void setReplyRid(Integer replyRid) {
        this.replyRid = replyRid;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
}
