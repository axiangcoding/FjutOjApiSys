package com.fjut.oj.pojo;

import java.util.Date;

/**
 * 评测视图
 *
 * @Author: axiang [20190715]
 */
public class ViewUserStatus {
    private Integer id;
    private String nick;
    private String ruser;
    private Integer pid;
    private Integer cid;
    private Integer lang;
    private Date submitTime;
    private Integer result;
    private Integer score;
    private String timeUsed;
    private String memoryUsed;
    private Integer codelen;
    private String code;
    private String otherinfo;
    private String submitlanguage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getRuser() {
        return ruser;
    }

    public void setRuser(String ruser) {
        this.ruser = ruser;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getLang() {
        return lang;
    }

    public void setLang(Integer lang) {
        this.lang = lang;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(String timeUsed) {
        this.timeUsed = timeUsed;
    }

    public String getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(String memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public Integer getCodelen() {
        return codelen;
    }

    public void setCodelen(Integer codelen) {
        this.codelen = codelen;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOtherinfo() {
        return otherinfo;
    }

    public void setOtherinfo(String otherinfo) {
        this.otherinfo = otherinfo;
    }

    public String getSubmitlanguage() {
        return submitlanguage;
    }

    public void setSubmitlanguage(String submitlanguage) {
        this.submitlanguage = submitlanguage;
    }

}
