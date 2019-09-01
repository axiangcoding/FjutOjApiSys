package com.fjut.oj.pojo;

/**
 * @author axiang [201908010]
 */
public class ViewCodePO {
    private String username;
    private Integer pid;
    private Integer type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ViewCodePO{" +
                "username='" + username + '\'' +
                ", pid=" + pid +
                ", type=" + type +
                '}';
    }
}
