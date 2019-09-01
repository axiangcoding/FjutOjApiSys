package com.fjut.oj.pojo;

/**
 * @author axiang [20190731]
 */
public class LocalJudgeSubmitInfoPO {
    private String type;
    private Integer pid;
    private Integer rid;
    private Integer timeLimit;
    private Integer memoryLimit;
    private String code;
    private Integer languageId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }


    @Override
    public String toString() {
        return "LocalJudgeSubmitInfoPO{" +
                "type='" + type + '\'' +
                ", pid=" + pid +
                ", rid=" + rid +
                ", timeLimit=" + timeLimit +
                ", memoryLimit=" + memoryLimit +
                ", code='" + code + '\'' +
                ", languageId=" + languageId +
                '}';
    }
}
