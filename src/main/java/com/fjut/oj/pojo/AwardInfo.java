package com.fjut.oj.pojo;

/**
 * @author cjt
 */
public class AwardInfo {
    private String time;
    private int contest_level;
    private int awards_level;
    private String text;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getContestLevel() {
        return contest_level;
    }

    public void setContestLevel(int contestLevel) {
        this.contest_level = contestLevel;
    }

    public int getAwardLevel() {
        return awards_level;
    }

    public void setAwardLevel(int awardLevel) {
        this.awards_level = awardLevel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        String text = this.text.isEmpty()?this.text:"("+this.text+")";
        return this.time+"：参加"+this.contest_level+"获得了"+this.awards_level+text;
    }
}
