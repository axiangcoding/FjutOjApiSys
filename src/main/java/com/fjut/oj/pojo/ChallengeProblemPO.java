package com.fjut.oj.pojo;

/**
 * 挑战模式题目表
 *
 * @author axiang [20190901]
 */
public class ChallengeProblemPO {

    private Integer id;
    private Integer pid;
    private Integer tpid;
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ChallengeProblemPO{" +
                "id=" + id +
                ", pid=" + pid +
                ", tpid=" + tpid +
                ", score=" + score +
                '}';
    }
}
