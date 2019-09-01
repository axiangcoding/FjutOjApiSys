package com.fjut.oj.pojo;

/**
 * 队伍成员信息
 *
 * @author cjt
 */
public class TeamMemberInfo {

    private Integer id;
    private String  time;
    private String  username1;
    private String  username2;
    private String  username3;
    private String  name1;
    private String  name2;
    private String  name3;
    private Integer contest_level;
    private Integer awards_level;
    private String  text;

    private String contestLevelStr;
    private String awardsLevelStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public String getUsername3() {
        return username3;
    }

    public void setUsername3(String username3) {
        this.username3 = username3;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public Integer getContest_level() {
        return contest_level;
    }

    public void setContest_level(Integer contest_level) {
        this.contest_level = contest_level;
    }

    public Integer getAwards_level() {
        return awards_level;
    }

    public void setAwards_level(Integer awards_level) {
        this.awards_level = awards_level;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getContestLevelStr() {
        return contestLevelStr;
    }

    public void setContestLevelStr(String contestLevelStr) {
        this.contestLevelStr = contestLevelStr;
    }

    public String getAwardsLevelStr() {
        return awardsLevelStr;
    }

    public void setAwardsLevelStr(String awardsLevelStr) {
        this.awardsLevelStr = awardsLevelStr;
    }
}
