package com.fjut.oj.pojo.enums;

/**
 * @author axiang [20190826]
 */
public enum BugType {
    OTHER(0, "其他"),
    SYSTEM_FAIL(1, "系统漏洞"),
    FUNCTION_ERROR(2, "功能异常"),
    LOGICAL_ERROR(3, "逻辑错误"),
    VIEW_PROBLEM(4, "界面问题");

    private int id;
    private String name;

    BugType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByID(int id) {
        for (BugType t : BugType.values()) {
            if (t.getId() == id) {
                return t.getName();
            }
        }
        return null;
    }
}
