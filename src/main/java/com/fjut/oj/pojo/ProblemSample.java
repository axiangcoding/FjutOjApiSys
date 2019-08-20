package com.fjut.oj.pojo;

/**
 * @author cjt
 */
public class ProblemSample {
    private Integer pid;
    private Integer id;
    private String  input;
    private String  output;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "t_problemsample{" +
                "pid=" + pid +
                ", id=" + id +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
