package com.fjut.oj.util;


import java.util.ArrayList;
import java.util.List;

/**
 * @author QAQ [20150606]
 */
public class ProblemHTML {

    public String getTitle() {
        return Title;
    }

    public String getDis() {
        return Dis;
    }

    public String getInput() {
        return Input;
    }

    public String getOutput() {
        return Output;
    }

    public List<String> getSampleInput() {
        return SampleInput;
    }

    public List<String> getSampleOutput() {
        return SampleOutput;
    }

    public String getTimeLimit() {
        return TimeLimit;
    }

    public String getMenoryLimit() {
        return MenoryLimit;
    }

    public String getInt64() {
        return Int64;
    }

    public int getSpj() {
        return spj ? 1 : 0;
    }

    public boolean isSpj() {
        return spj;
    }

    private String Title;
    private String Dis;
    private String Input;
    private String Output;
    private List<String> SampleInput;
    private List<String> SampleOutput;
    private String TimeLimit;
    private String MenoryLimit;
    private String Int64;
    private boolean spj;
    private boolean admin = false;
    private int pid;
    private boolean isInContest = false;

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setSpj(int i) {
        spj = (i != 0);
    }

    public ProblemHTML() {
        Dis = "";
        Input = "";
        Output = "";
        TimeLimit = "";
        MenoryLimit = "";
        Int64 = "";
        spj = false;
        SampleInput = new ArrayList<String>();
        SampleOutput = new ArrayList<String>();
    }

    public ProblemHTML(int pid) {
        Dis = "";
        Input = "";
        Output = "";
        TimeLimit = "";
        MenoryLimit = "";
        Int64 = "";
        spj = false;
        SampleInput = new ArrayList<String>();
        SampleOutput = new ArrayList<String>();
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "ProblemHTML{" +
                "Title='" + Title + '\'' +
                ", Dis='" + Dis + '\'' +
                ", Input='" + Input + '\'' +
                ", Output='" + Output + '\'' +
                ", SampleInput=" + SampleInput +
                ", SampleOutput=" + SampleOutput +
                ", TimeLimit='" + TimeLimit + '\'' +
                ", MenoryLimit='" + MenoryLimit + '\'' +
                ", Int64='" + Int64 + '\'' +
                ", spj=" + spj +
                ", admin=" + admin +
                ", pid=" + pid +
                ", isInContest=" + isInContest +
                '}';
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDis(String dis) {
        Dis = dis;
    }

    public void setInput(String input) {
        Input = input;
    }

    public void setOutput(String output) {
        Output = output;
    }

    public void setSampleInput(List<String> sampleInput) {
        SampleInput = sampleInput;
    }

    public void setSampleOutput(List<String> sampleOutput) {
        SampleOutput = sampleOutput;
    }

    public void setTimeLimit(String timeLimit) {
        TimeLimit = timeLimit;
    }

    public void setMenoryLimit(String menoryLimit) {
        MenoryLimit = menoryLimit;
    }

    public void setInt64(String int64) {
        Int64 = int64;
    }

    public void setSpj(boolean spj) {
        this.spj = spj;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getPid() {
        return pid;
    }

    public boolean isInContest() {
        return isInContest;
    }

    public void setInContest(boolean inContest) {
        isInContest = inContest;
    }

    private String getTitleHTML() {
        return "<h1 style='text-align:center'>" + Title + "</h1>";
    }

    private String getLimitHTML() {
        String s = "";
        s += "<div class='row'>" +
                "<div style='text-align:center'>" +
                "TimeLimit:" + TimeLimit + "&nbsp;&nbsp;MemoryLimit:" + MenoryLimit +
                "</div>" +
                "</div>";
        return s;
    }

    private String getInt64HTML() {
        String s = "";
        s += "<div class='row'>" +
                "<div style='text-align:center'>" +
                "64-bit integer IO format:<span class='badge'>" + Int64 + "</span>" +
                "</div>" +
                "</div>";
        return s;
    }

    private String getSpjHTML() {
        String s = "";
        if (spj) {
            s += "<div class='row'>" +
                    "<center>" +
                    "Special Judge" + "red" +
                    "</center>" +
                    "</div>";
        }
        return s;
    }

    private String edit(String href, String s) {
        return "<a class=\"btn btn-default btn-sm\" style=\"\n" +
                "    margin-top: -16px;\n" +
                "    margin-right: -16px;\n" +
                "    float: right;\n" +
                "    border-radius: 0px 0px 0px 6px;\n" +
                "\" href='" + href + "'>" + s + "</a>";
    }

    public void addSample(String sampleinput, String sampleoutput) {
        SampleInput.add(sampleinput);
        SampleOutput.add(sampleoutput);
    }
}
