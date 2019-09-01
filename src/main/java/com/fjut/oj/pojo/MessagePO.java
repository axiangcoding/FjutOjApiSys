package com.fjut.oj.pojo;

import java.util.Date;

/**
 * 用户 message 消息
 *
 * @author axiang [20190828]
 */

public class MessagePO {

    private Integer mid;
    private String user;
    /**
     * 0 未读 1 已读
     */
    private Integer statu;
    private String title;
    private String text;
    private Date time;
    /**
     * 过期时间
     */
    private Date deadline;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getStatus() {
        return statu;
    }

    public void setStatus(Integer status) {
        this.statu = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }


    @Override
    public String toString() {
        return "MessagePO{" +
                "mid=" + mid +
                ", user='" + user + '\'' +
                ", statu=" + statu +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", deadline=" + deadline +
                '}';
    }
}
