package com.fjut.oj.pojo;

import org.apache.ibatis.type.IntegerTypeHandler;

/**
 * @Author: axiang [2019/7/31]
 */
public class LocalJudgeSubmitInfo {
    private String type;
    private Integer pid;
    private Integer rid;
    private Integer timelimit;
    private Integer memorylimit;
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

    public Integer getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(Integer timelimit) {
        this.timelimit = timelimit;
    }

    public Integer getMemorylimit() {
        return memorylimit;
    }

    public void setMemorylimit(Integer memorylimit) {
        this.memorylimit = memorylimit;
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
}
