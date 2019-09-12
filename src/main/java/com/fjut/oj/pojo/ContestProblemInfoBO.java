package com.fjut.oj.pojo;

/**
 * 比赛题目BO类
 * 与比赛题目类不同，注意区分
 * @author cjt
 */
public class ContestProblemInfoBO {
    private Integer pid;
    private Integer tpid;
    private String title;
    private Integer totalAcUser;
    private Integer totalSubmit;


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalAcUser() {
        return totalAcUser;
    }

    public void setTotalAcUser(Integer totalAcUser) {
        this.totalAcUser = totalAcUser;
    }

    public Integer getTotalSubmit() {
        return totalSubmit;
    }

    public void setTotalSubmit(Integer totalSubmit) {
        this.totalSubmit = totalSubmit;
    }

    @Override
    public String toString() {
        return "ContestProblemInfoBO{" +
                "pid=" + pid +
                ", tpid=" + tpid +
                ", title='" + title + '\'' +
                ", totalAcUser=" + totalAcUser +
                ", totalSubmit=" + totalSubmit +
                '}';
    }
}
