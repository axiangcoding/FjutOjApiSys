package com.fjut.oj.pojo.enums;

/**
 * @author QAQ [20160630]
 */
public enum AwardLevel {
    /* 无奖项 */
    NONE(-2),
    /* 顽强拼搏奖 */
    TENACIOUSLY(-1),
    /* 优秀奖/鼓励奖 */
    ENCOURAGING(0),
    /* 铜奖 */
    BRAZE(1),
    /* 银奖 */
    SILVER(2),
    /* 金奖 */
    GOLD(3),
    /* 一等奖 */
    LV1(4),
    /* 二等奖 */
    LV2(5),
    /* 三等奖 */
    LV3(6);

    AwardLevel(int code) {
        this.code = code;
    }

    private int code;

    public static AwardLevel getByCode(int code) {
        for (AwardLevel it : AwardLevel.values()) {
            if (it.code == code) {
                return it;
            }
        }
        return AwardLevel.NONE;
    }

    @Override
    public String toString() {
        if (this == AwardLevel.TENACIOUSLY) {
            return "顽强拼搏奖";
        }
        if (this == AwardLevel.ENCOURAGING) {
            return "鼓励奖";
        }
        if (this == AwardLevel.BRAZE) {
            return "铜奖";
        }
        if (this == AwardLevel.SILVER) {
            return "银奖";
        }
        if (this == AwardLevel.GOLD) {
            return "金奖";
        }
        if (this == AwardLevel.LV1) {
            return "一等奖";
        }
        if (this == AwardLevel.LV2) {
            return "二等奖";
        }
        if (this == AwardLevel.LV3) {
            return "三等奖";
        }
        return "无奖项";
    }

    public int getCode() {
        return code;
    }
}
