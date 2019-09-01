package com.fjut.oj.pojo;


/**
 * @author
 */
public class UserPermissionPO {

    private String username;
    private Integer perid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPerid() {
        return perid;
    }

    public void setPerid(Integer perid) {
        this.perid = perid;
    }

    @Override
    public String toString() {
        return "UserPermissionPO{" +
                "username='" + username + '\'' +
                ", perid=" + perid +
                '}';
    }
}
