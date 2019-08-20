package com.fjut.oj.pojo.enums;

/**
 * @author QAQ [20160630]
 */
public enum ContestLevel {
    /* 其他、不显示在奖项列表中 */
    NONE(-1),
    /* ACM省赛 */
    PROVINCE(0),
    /* ACM/ICPC区域赛 */
    REGIONAL(1),
    /* EC-Final */
    EC_FINAL(2),
    /* 世界总决赛 */
    WORLD_FINAL(3),
    /* 全国蓝桥杯大赛 */
    LANQIAOBEI(4),
    /* ACM全国邀请赛 */
    YAOQINGSAI(5),
    /* 全国大学生程序设计竞赛 */
    CCPC(6);

    ContestLevel(int code){
        this.code = code;
    }
    private int code;

    public static ContestLevel getByCode(int code){
        for(ContestLevel it : ContestLevel.values()){
            if(it.code == code){
                return it;
            }
        }
        return ContestLevel.NONE;
    }

    @Override
    public String toString(){
        if(this == ContestLevel.PROVINCE) {
            return "ACM福建省省赛";
        }
        if(this == ContestLevel.REGIONAL) {
            return "ACM/ICPC亚洲区域赛";
        }
        if(this == ContestLevel.EC_FINAL) {
            return "ACM/ICPC东亚赛区总决赛(EC-Final)";
        }
        if(this == ContestLevel.LANQIAOBEI) {
            return "全国蓝桥杯软件设计大赛";
        }
        if(this == ContestLevel.WORLD_FINAL) {
            return "ACM/ICPC世界总决赛";
        }
        if(this == ContestLevel.YAOQINGSAI) {
            return "ACM/ICPC全国邀请赛";
        }
        if(this == ContestLevel.CCPC) {
            return "中国大学生程序设计竞赛";
        }
        return "其他";
    }

    public int getCode() {
        return code;
    }
}
