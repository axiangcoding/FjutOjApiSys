package com.fjut.oj.pojo;

/**
 * 题目标签表
 * @author cjt
  */
public class ProblemTagPO {

    private Integer id;
    private String  name;
    private Integer ttype;
    private Double  priority;

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

    public Integer getTtype() {
        return ttype;
    }

    public void setTtype(Integer ttype) {
        this.ttype = ttype;
    }

    public Double getPriority() {
        return priority;
    }

    public void setPriority(Double priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "ProblemTagPO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ttype=" + ttype +
                ", priority=" + priority +
                '}';
    }
}
