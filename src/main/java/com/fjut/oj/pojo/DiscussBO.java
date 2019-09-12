package com.fjut.oj.pojo;

import java.util.Date;

/**
 * @author axiang [20190909]
 */
public class DiscussBO {
    private Integer id;
    private String title;
    private Date time;
    private String author;
    private Date lastReplyTime;
    private String lastReplyUsername;
    private Double priority;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(Date lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    public String getLastReplyUsername() {
        return lastReplyUsername;
    }

    public void setLastReplyUsername(String lastReplyUsername) {
        this.lastReplyUsername = lastReplyUsername;
    }

    public Double getPriority() {
        return priority;
    }

    public void setPriority(Double priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "DiscussBO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", time=" + time +
                ", author='" + author + '\'' +
                ", lastReplyTime=" + lastReplyTime +
                ", lastReplyUsername='" + lastReplyUsername + '\'' +
                ", priority=" + priority +
                '}';
    }
}
