package com.fjut.oj.pojo;

import java.util.Date;

/**
 * @author axiang [20190909]
 */
public class DiscussReplyPO {
    private Integer id;
    private Integer discussId;
    private Integer replyId;
    private Date time;

    private String author;
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDiscussId() {
        return discussId;
    }

    public void setDiscussId(Integer discussId) {
        this.discussId = discussId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return "DiscussReplyPO{" +
                "id=" + id +
                ", discussId=" + discussId +
                ", replyId=" + replyId +
                ", time=" + time +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

}
