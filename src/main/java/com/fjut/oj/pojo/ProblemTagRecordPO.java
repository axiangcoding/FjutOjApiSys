package com.fjut.oj.pojo;

/**
 * 用户贴题目标签信息
 *
 * @author cjt
  */

public class ProblemTagRecordPO {

    private Integer pid;
    private String  username;
    private Integer tagid;
    private Integer rating;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ProblemTagRecordPO{" +
                "pid=" + pid +
                ", username='" + username + '\'' +
                ", tagid=" + tagid +
                ", rating=" + rating +
                '}';
    }
}
