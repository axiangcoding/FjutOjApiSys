package com.fjut.oj.pojo;

import java.util.Date;

/**
 * 讨论模块
 *
 * @author cjt
 */
public class t_discuss {

    private Integer id;
    private Integer cid;
    private String  title;
    private Integer panelclass;
    private String  username;
    private Date    time;
    private String  text;
    private Double  priority;
    private Integer top;
    private Integer visiable;
    private Integer reply;
    private Integer shownum;
    private Integer panelnobody;
    private Integer showauthor;
    private Integer showtime;
    private Integer replyhidden;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPanelclass() {
        return panelclass;
    }

    public void setPanelclass(Integer panelclass) {
        this.panelclass = panelclass;
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

    public Double getPriority() {
        return priority;
    }

    public void setPriority(Double priority) {
        this.priority = priority;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getVisiable() {
        return visiable;
    }

    public void setVisiable(Integer visiable) {
        this.visiable = visiable;
    }

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }

    public Integer getShownum() {
        return shownum;
    }

    public void setShownum(Integer shownum) {
        this.shownum = shownum;
    }

    public Integer getPanelnobody() {
        return panelnobody;
    }

    public void setPanelnobody(Integer panelnobody) {
        this.panelnobody = panelnobody;
    }

    public Integer getShowauthor() {
        return showauthor;
    }

    public void setShowauthor(Integer showauthor) {
        this.showauthor = showauthor;
    }

    public Integer getShowtime() {
        return showtime;
    }

    public void setShowtime(Integer showtime) {
        this.showtime = showtime;
    }

    public Integer getReplyhidden() {
        return replyhidden;
    }

    public void setReplyhidden(Integer replyhidden) {
        this.replyhidden = replyhidden;
    }
}
