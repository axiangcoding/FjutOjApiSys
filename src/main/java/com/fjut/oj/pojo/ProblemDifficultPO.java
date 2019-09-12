package com.fjut.oj.pojo;

/**
 * @author axiang [20190909]
 */
public class ProblemDifficultPO {
    private Integer id;
    private Integer pid;
    private Integer difficultType;

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

    public Integer getDifficultType() {
        return difficultType;
    }

    public void setDifficultType(Integer difficultType) {
        this.difficultType = difficultType;
    }

    @Override
    public String toString() {
        return "ProblemDifficultPO{" +
                "id=" + id +
                ", pid=" + pid +
                ", difficultType=" + difficultType +
                '}';
    }
}
