package com.fjut.oj.pojo;

import java.sql.Timestamp;

public class SubmisssionRecord {

    private String ruser;
    private int id;
    private int pid;
    private int cid;
    private int language;
    private String code;
    private int codelen;
    private Timestamp SubmitTime;
    private int result;
    private int score = -1;
    private String TimeUsed;
    private String MemoryUsed;

    public String getRuser() {
        return ruser;
    }

    public void setRuser(String ruser) {
        this.ruser = ruser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCodelen() {
        return codelen;
    }

    public void setCodelen(int codelen) {
        this.codelen = codelen;
    }

    public Timestamp getSubmitTime() {
        return SubmitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        SubmitTime = submitTime;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTimeUsed() {
        return TimeUsed;
    }

    public void setTimeUsed(String timeUsed) {
        TimeUsed = timeUsed;
    }

    public String getMemoryUsed() {
        return MemoryUsed;
    }

    public void setMemoryUsed(String memoryUsed) {
        MemoryUsed = memoryUsed;
    }

    //    public SubmisssionRecord(int id, String ruser, int pid, int cid, int language, String code, Timestamp time){
//        this.id = id;
//        this.ruser = ruser;
//        this.pid = pid;//local pid
//        this.cid = cid;
//        this.language = language;
//        this.code = code;
//        this.codelen = code.replaceAll(" ","").replaceAll("\n","").replaceAll("\r","").replaceAll("\t","").length();
//        this.SubmitTime = time;
//        this.result = SubmitResultType.PENDING;
//        this.TimeUsed = "-";
//        this.MemoryUsed = "-";
//    }
//
//    public SubmisssionRecord(){}
//
//    public static SubmitResultType  intToResult(int i){
//        return SubmitResultType.getSubmitResultType(i);
//    }
//
//    public static int resultToInt(SubmitResultType r){
//        return r.getValue();
//    }
//
//    public int getPid() { return pid; }
//
//    public int getId() { return id; }
//
//    public void setId(int id) { this.id=id;}
//
//    public int getLanguage() { return this.language; }
//
//    public String getCode() {  return this.code; }
//
//    public int getCodelen(){return codelen;}
//
//    public void setStatusResult(SubmitResultType res,String time,String Memory){
//        result=res;
//        TimeUsed=time;
//        MemoryUsed=Memory;
////        if(cid!=-1){
////            ContestPO c= ContestMain.getContest(cid);
////            c.getRank()._add(Main.status.getStatu(rid),c);//通知更新排行榜
////        }
//    }
//
//    public int getCid(){
//        return cid;
//    }
//
//    public void setCid(int cid){
//        this.cid=cid;
//    }
//
//    public SubmitResultType getResult(){
//        return result;
//    }
//
//    public String getResultString(){
//        SubmitResultType s=result;
//        if(s==SubmitResultType.AC) return "Accepted";
//        if(s==SubmitResultType.CE) return "Compilation Error";
//        if(s==SubmitResultType.DANGER) return "System Error";
//        if(s==SubmitResultType.MLE) return "Memory Limit Exceeded";
//        if(s==SubmitResultType.OLE) return"Output Limit Exceeded";
//        if(s==SubmitResultType.PE) return "Presentation Error";
//        if(s==SubmitResultType.PENDING) return "Pendding...";
//        if(s==SubmitResultType.JUDGING) return "Judging...";
//        if(s==SubmitResultType.RE) return "Runtime Error";
//        if(s==SubmitResultType.RUNNING) return "Running...";
//        if(s==SubmitResultType.TLE) return "Time Limit Exceeded";
//        if(s==SubmitResultType.WA) return "Wrong Answer";
//        if(s==SubmitResultType.ERROR) return "Submit Error";
//        if(s==SubmitResultType.SCORE) return "Score";
//        return "System Error";
//    }
//
//    public String getTimeUsed(){  return TimeUsed; }
//
//    public String getMemoryUsed(){  return MemoryUsed; }
//
//    public String getRuser(){return ruser;}
//
//    public Timestamp getSbmitTime(){return SubmitTime;}
//
//    public int getScore() {
//        return score;
//    }
//
//    public void setScore(int score) {
//        this.score = score;
//    }

}
