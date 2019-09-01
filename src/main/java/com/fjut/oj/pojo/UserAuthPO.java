package com.fjut.oj.pojo;

import java.util.Date;

/**
 * 用户认证表
 *
 * @author axiang [20190828]
 */
public class UserAuthPO {
    private Integer id;
    private String username;
    private String salt;
    private String password;
    private Integer attemptLoginFailCount;
    private Integer locked;
    private Date unlockTime;
    private Date lastLoginTime;

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAttemptLoginFailCount() {
        return attemptLoginFailCount;
    }

    public void setAttemptLoginFailCount(Integer attemptLoginFailCount) {
        this.attemptLoginFailCount = attemptLoginFailCount;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Date getUnlockTime() {
        return unlockTime;
    }

    public void setUnlockTime(Date unlockTime) {
        this.unlockTime = unlockTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "UserAuthPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", salt='" + salt + '\'' +
                ", password='" + password + '\'' +
                ", attemptLoginFailCount=" + attemptLoginFailCount +
                ", locked=" + locked +
                ", unlockTime=" + unlockTime +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
