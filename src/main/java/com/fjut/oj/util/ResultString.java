package com.fjut.oj.util;

public class ResultString {
    public static String getResultString(int resultId) {
        if (resultId == 0) {
            return "Pendding...";
        }
        if (resultId == 1) {
            return "Accepted";
        }
        if (resultId == 2) {
            return "Wrong Answer";
        }
        if (resultId == 3) {
            return "Compilation Error";
        }
        if (resultId == 4) {
            return "Runtime Error";
        }
        if (resultId == 5) {
            return "Time Limit Exceeded";
        }
        if (resultId == 6) {
            return "Memory Limit Exceeded";
        }
        if (resultId == 7) {
            return "Output Limit Exceeded";
        }
        if (resultId == 8) {
            return "Presentation Error";
        }
        if (resultId == 9) {
            return "System Error";
        }
        if (resultId == 10) {
            return "Running...";
        }
        if (resultId == 11) {
            return "Submit Error";
        }
        if (resultId == 12) {
            return "Judging...";
        }
        if (resultId == 13) {
            return "Score";
        }
        return "System Error";
    }

    public static String getSubmitLanguage(int langId) {
        if (langId == 0) {
            return "G++";
        }
        if (langId == 1) {
            return "GCC";
        }
        if (langId == 2) {
            return "JAVA";
        }
        if (langId == 3) {
            return "Python2";
        }
        if (langId == 4) {
            return "G++11";
        }
        if (langId == 5) {
            return "GCC11";
        }
        if (langId == 6) {
            return "VC++";
        }
        if (langId == 7) {
            return "C#";
        }
        if (langId == 8) {
            return "GO 1.8";
        }
        if (langId == 9) {
            return "JavaScript";
        }
        if (langId == 10) {
            return "Pascal";
        }
        return "error";
    }

    public static Integer getSubmitLanguage(String lang) {
        if ("G++".equalsIgnoreCase(lang)) {
            // + 号通过 url 传递时要转化成 %2b
            return 0;
        }
        if ("GCC".equalsIgnoreCase(lang)) {
            return 1;
        }
        if ("JAVA".equalsIgnoreCase(lang)) {
            return 2;
        }
        if ("Python2".equalsIgnoreCase(lang)) {
            return 3;
        }
        if ("G++11".equalsIgnoreCase(lang)) {
            // + 号通过 url 传递时要转化成 %2b
            return 4;
        }
        if ("GCC11".equalsIgnoreCase(lang)) {
            return 5;
        }
        if ("VC++".equalsIgnoreCase(lang)) {
            return 6;
        }
        if ("C#".equalsIgnoreCase(lang)) {
            // # 号 %23
            return 7;
        }
        if ("GO 1.8".equalsIgnoreCase(lang)) {
            return 8;
        }
        if ("JavaScript".equalsIgnoreCase(lang)) {
            return 9;
        }
        return 0;
    }

    public static Integer getResultString(String resultStr) {
        if ("Accepted".equals(resultStr)) {
            return 1;
        }
        if ("Compilation Error".equals(resultStr)) {
            return 3;
        }
        if ("System Error".equals(resultStr)) {
            return 9;
        }
        if ("Memory Limit Exceeded".equals(resultStr)) {
            return 6;
        }
        if ("Output Limit Exceeded".equals(resultStr)) {
            return 7;
        }
        if ("Presentation Error".equals(resultStr)) {
            return 8;
        }
        if ("Pendding...".equals(resultStr)) {
            return 0;
        }
        if ("Judging...".equals(resultStr)) {
            return 12;
        }
        if ("Runtime Error".equals(resultStr)) {
            return 4;
        }
        if ("Running......".equals(resultStr)) {
            return 10;
        }
        if ("Time Limit Exceeded".equals(resultStr)) {
            return 5;
        }
        if ("Wrong Answer".equals(resultStr)) {
            return 2;
        }
        if ("Score".equals(resultStr)) {
            return 13;
        }
        return 5;
    }

    public static Integer contestLevelToint(String contestStr) {
        if ("ACM省赛".equals(contestStr)) {
            return 0;
        }
        if ("ACM/ICPC区域赛".equals(contestStr)) {
            return 1;
        }
        if ("EC-Final".equals(contestStr)) {
            return 2;
        }
        if ("世界总决赛".equals(contestStr)) {
            return 3;
        }
        if ("全国蓝桥杯大赛".equals(contestStr)) {
            return 4;
        }
        if ("ACM全国邀请赛".equals(contestStr)) {
            return 5;
        }
        if ("全国大学生程序设计竞赛".equals(contestStr)) {
            return 6;
        }
        return -1;
    }

    public static String contestLevelToStr(Integer num) {
        if (num == 0) {
            return "ACM省赛";
        }
        if (num == 1) {
            return "ACM/ICPC区域赛";
        }
        if (num == 2) {
            return "EC-Final";
        }
        if (num == 3) {
            return "世界总决赛";
        }
        if (num == 4) {
            return "全国蓝桥杯大赛";
        }
        if (num == 5) {
            return "ACM全国邀请赛";
        }
        if (num == 6) {
            return "全国大学生程序设计竞赛";
        }
        return "其他";
    }

    public static Integer awardLevelToint(String awardStr) {
        if ("顽强拼搏奖".equals(awardStr)) {
            return -1;
        }
        if ("优秀奖/鼓励奖".equals(awardStr)) {
            return 0;
        }
        if ("铜奖".equals(awardStr)) {
            return 1;
        }
        if ("银奖".equals(awardStr)) {
            return 2;
        }
        if ("金奖".equals(awardStr)) {
            return 3;
        }
        if ("一等奖".equals(awardStr)) {
            return 4;
        }
        if ("二等奖".equals(awardStr)) {
            return 5;
        }
        if ("三等奖".equals(awardStr)) {
            return 6;
        }
        return -2;
    }

    public static String awardLevelToStr(Integer num) {
        if (num == -1) {
            return "顽强拼搏奖";
        }
        if (num == 0) {
            return "优秀奖/鼓励奖";
        }
        if (num == 1) {
            return "铜奖";
        }
        if (num == 2) {
            return "银奖";
        }
        if (num == 3) {
            return "金奖";
        }
        if (num == 4) {
            return "一等奖";
        }
        if (num == 5) {
            return "二等奖";
        }
        if (num == 6) {
            return "三等奖";
        }
        return "其他";
    }
}
