package com.fjut.oj.pojo;

/**
 * 用户表
 * @author cjt
 *
 */
public class UserPO {
    private String  username;
    private String  password;
    private String  nick;
    private Integer gender;
    private String  school;
    private String  Email;
    /** 签名 */
    private String  motto;
    private String  registertime;
    private Integer type;
    private String  Mark;
    private Integer rating;
    private Integer ratingnum;
    private Integer acb;
    private String  name;
    /**  学院 */
    private String  faculty;
    private String  major;
    private String  cla;
    private String  no;
    private String  phone;
    private Integer acnum;
    private Integer inTeamStatus;
    private Integer inTeamLv;
    private Integer rank;
    private String  graduationTime;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMark() {
        return Mark;
    }

    public void setMark(String mark) {
        Mark = mark;
    }

    public Integer getRatingnum() {
        return ratingnum;
    }

    public void setRatingnum(Integer ratingnum) {
        this.ratingnum = ratingnum;
    }

    public Integer getAcb() {
        return acb;
    }

    public void setAcb(Integer acb) {
        this.acb = acb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCla() {
        return cla;
    }

    public void setCla(String cla) {
        this.cla = cla;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAcnum() {
        return acnum;
    }

    public void setAcnum(Integer acnum) {
        this.acnum = acnum;
    }

    public Integer getInTeamStatus() {
        return inTeamStatus;
    }

    public void setInTeamStatus(Integer inTeamStatus) {
        this.inTeamStatus = inTeamStatus;
    }

    public Integer getInTeamLv() {
        return inTeamLv;
    }

    public void setInTeamLv(Integer inTeamLv) {
        this.inTeamLv = inTeamLv;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    @Override
    public String toString() {
        return "UserPO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nick='" + nick + '\'' +
                ", gender=" + gender +
                ", school='" + school + '\'' +
                ", Email='" + Email + '\'' +
                ", motto='" + motto + '\'' +
                ", registertime='" + registertime + '\'' +
                ", type=" + type +
                ", Mark='" + Mark + '\'' +
                ", rating=" + rating +
                ", ratingnum=" + ratingnum +
                ", acb=" + acb +
                ", name='" + name + '\'' +
                ", faculty='" + faculty + '\'' +
                ", major='" + major + '\'' +
                ", cla='" + cla + '\'' +
                ", no='" + no + '\'' +
                ", phone='" + phone + '\'' +
                ", acnum=" + acnum +
                ", inTeamStatus=" + inTeamStatus +
                ", inTeamLv=" + inTeamLv +
                ", rank=" + rank +
                ", graduationTime='" + graduationTime + '\'' +
                '}';
    }
}
