package com.fjut.oj.pojo;

import java.util.Date;

/**
 * Bug 报告
 *
 * @author axiang [20190825]
 */
public class BugReportPO {
    private Integer id;
    private String username;
    private String title;
    private Integer type;
    private String text;
    private Date time;
    private Integer isFixed;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getIsFixed() {
        return isFixed;
    }

    public void setIsFixed(Integer isFixed) {
        this.isFixed = isFixed;
    }

    @Override
    public String toString() {
        return "BugReportPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", isFixed=" + isFixed +
                '}';
    }
}
