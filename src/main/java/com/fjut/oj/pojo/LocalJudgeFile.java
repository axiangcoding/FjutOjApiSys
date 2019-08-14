package com.fjut.oj.pojo;

/**
 * @Author: axiang [20190814] 从本地判题题库中获取文件详情
 */
public class LocalJudgeFile {
    private String problemName;
    private String inputFiles;
    private String outputFiles;
    private String spjFiles;
    private String otherFiles;

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getInputFiles() {
        return inputFiles;
    }

    public void setInputFiles(String inputFiles) {
        this.inputFiles = inputFiles;
    }

    public String getOutputFiles() {
        return outputFiles;
    }

    public void setOutputFiles(String outputFiles) {
        this.outputFiles = outputFiles;
    }

    public String getSpjFiles() {
        return spjFiles;
    }

    public void setSpjFiles(String spjFiles) {
        this.spjFiles = spjFiles;
    }

    public String getOtherFiles() {
        return otherFiles;
    }

    public void setOtherFiles(String otherFiles) {
        this.otherFiles = otherFiles;
    }
}
