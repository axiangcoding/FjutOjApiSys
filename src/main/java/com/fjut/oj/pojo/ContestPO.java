package com.fjut.oj.pojo;

import java.util.Date;

/**
 * 比赛类
 * @author cjt
 */
public class ContestPO {
    private Integer id;
    private String name;
    private Date beginTime;
    private Date endTime;
    private Integer rankType;
    private Integer ctype;
    private String password;
    private Date registerstarttime;
    private Date registerendtime;
    private String info;
    private Integer computerating;
    private String createuser;
    private Integer kind;
    private boolean problemCanPutTag;
    private boolean statusReadOut;
    private boolean registerShowComplete;
    private boolean isHideOthersStatus;
    private boolean isHideOthersStatusInfo;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getRankType() {
        return rankType;
    }

    public void setRankType(Integer rankType) {
        this.rankType = rankType;
    }

    public Integer getCtype() {
        return ctype;
    }

    public void setCtype(Integer ctype) {
        this.ctype = ctype;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterstarttime() {
        return registerstarttime;
    }

    public void setRegisterstarttime(Date registerstarttime) {
        this.registerstarttime = registerstarttime;
    }

    public Date getRegisterendtime() {
        return registerendtime;
    }

    public void setRegisterendtime(Date registerendtime) {
        this.registerendtime = registerendtime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getComputerating() {
        return computerating;
    }

    public void setComputerating(Integer computerating) {
        this.computerating = computerating;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public boolean isProblemCanPutTag() {
        return problemCanPutTag;
    }

    public void setProblemCanPutTag(boolean problemCanPutTag) {
        this.problemCanPutTag = problemCanPutTag;
    }

    public boolean isStatusReadOut() {
        return statusReadOut;
    }

    public void setStatusReadOut(boolean statusReadOut) {
        this.statusReadOut = statusReadOut;
    }

    public boolean isRegisterShowComplete() {
        return registerShowComplete;
    }

    public void setRegisterShowComplete(boolean registerShowComplete) {
        this.registerShowComplete = registerShowComplete;
    }

    public boolean isHideOthersStatus() {
        return isHideOthersStatus;
    }

    public void setHideOthersStatus(boolean hideOthersStatus) {
        isHideOthersStatus = hideOthersStatus;
    }

    public boolean isHideOthersStatusInfo() {
        return isHideOthersStatusInfo;
    }

    public void setHideOthersStatusInfo(boolean hideOthersStatusInfo) {
        isHideOthersStatusInfo = hideOthersStatusInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ContestPO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", rankType=" + rankType +
                ", ctype=" + ctype +
                ", password='" + password + '\'' +
                ", registerstarttime=" + registerstarttime +
                ", registerendtime=" + registerendtime +
                ", info='" + info + '\'' +
                ", computerating=" + computerating +
                ", createuser='" + createuser + '\'' +
                ", kind=" + kind +
                ", problemCanPutTag=" + problemCanPutTag +
                ", statusReadOut=" + statusReadOut +
                ", registerShowComplete=" + registerShowComplete +
                ", isHideOthersStatus=" + isHideOthersStatus +
                ", isHideOthersStatusInfo=" + isHideOthersStatusInfo +
                ", status=" + status +
                '}';
    }
}
