package com.fjut.oj.pojo.enums;

/**
 * @author QAQ [20171105]
 */
public enum CodeLanguage {
    GPP(0, "G++"),
    GCC(1, "GCC"),
    JAVA(2, "JAVA"),
    PYTHON2(3, "Python2"),
    GPP11(4, "G++11"),
    GCC11(5, "GCC11"),
    VCPP(6, "VC++"),
    CSHARP(7, "C#"),
    GO1_8(8, "GO 1.8"),
    JS(9, "JavaScript"),
    PASCAL(10, "Pascal");

    private int id;
    private String show;

    CodeLanguage(int id, String show) {
        this.id = id;
        this.show = show;
    }

    public int getId() {
        return id;
    }

    public String getShow() {
        return show;
    }

    public static CodeLanguage getByID(int id) {
        for (CodeLanguage l : CodeLanguage.values()) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }
}
