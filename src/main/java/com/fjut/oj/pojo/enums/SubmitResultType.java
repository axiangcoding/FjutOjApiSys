package com.fjut.oj.pojo.enums;

/**
 * @author cjt
 */
public enum SubmitResultType {
    PENDING(0),
    AC(1),
    WA(2),
    CE(3),
    RE(4),
    TLE(5),
    MLE(6),
    OLE(7),
    PE(8),
    DANGER(9),
    RUNNING(10),
    ERROR(11),
    JUDGING(12),
    SCORE(13);

    int value;

    SubmitResultType(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isAc() {
        return this == SubmitResultType.AC;
    }

    public boolean isErr() {
        return this == SubmitResultType.WA ||
                this == SubmitResultType.CE ||
                this == SubmitResultType.RE ||
                this == SubmitResultType.TLE ||
                this == SubmitResultType.MLE ||
                this == SubmitResultType.OLE ||
                this == SubmitResultType.PE;
    }

    public boolean isPd() {
        return this == SubmitResultType.PENDING ||
                this == SubmitResultType.DANGER ||
                this == SubmitResultType.RUNNING ||
                this == SubmitResultType.ERROR ||
                this == SubmitResultType.JUDGING;
    }

    public static SubmitResultType getSubmitResultType(int id) {
        for (SubmitResultType item : SubmitResultType.values()) {
            if (item.value == id) {
                return item;
            }
        }
        return null;
    }

}
