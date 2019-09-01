package com.fjut.oj.pojo.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cjt 用户权限表对应类
 */
public enum PermissionType {
    ADD_PROBLEM(1, "题目总管"),
    VIEW_CODE(2, "查看代码", false),
    REJUDGE(3, "重判"),
    ADD_CONTEST(4, "新增比赛"),
    COMPUTE_RATING(5, "计算rating", false),
    ADD_DISCUSS(6, "新增讨论"),
    ADD_TAG(7, "新增标签"),
    CLOCK_IN(8, "签到管理", false),
    PERMISSION_TYPE(9, "权限管理"),
    AWARD_ACB(10, "奖励ACB"),
    CONTEST_REGISTER_ADMIN(11, "审核比赛报名", false),
    ADD_LOCAL_PROBLEM(12, "增加本地题目"),
    CHALLENGE_ADMIN(13, "挑战模式管理"),
    RESET_PASSWORD(14, "密码重置"),
    USER_ADMIN(15, "用户管理"),
    VIEW_LOG(16, "查看log"),
    EXAM_ADMIN(17, "考试管理"),
    TEAM_MEMBER_ADMIN(18, "集训队员管理"),
    MALL_ADMIN(19, "商城管理"),
    APP_UPDATE(20, "APP更新"),
    VERIFY_ALL(21, "认证管理（全部）"),
    VERIFY_SCHOOL(22, "认证管理（校内人员）"),
    VERIFY_ASSOCIATION(23, "认证管理（协会成员）"),
    VERIFY_RETIRED(24, "认证管理（退役队员）"),
    VERIFY_TEAM(25, "认证管理（集训队员）"),
    TEAM_AUTO_REGISTER(26, "集训队员自动报名"),
    PART_ADD_PROBLEM(27, "添加题目"),
    TITLE_ADMIN(28, "称号管理", false),
    GROUP_ADMIN(29, "组队管理");

    private static Map<Integer, PermissionType> allPermission = null;
    private int code;
    private String name;
    private int type;
    private boolean showAdmin = true;

    PermissionType(int code, String name, boolean showAdmin, int type) {
        this.code = code;
        this.name = name;
        this.showAdmin = showAdmin;
        this.type = type;
    }

    PermissionType(int code, String name, boolean showAdmin) {
        this.code = code;
        this.name = name;
        this.showAdmin = showAdmin;
    }

    PermissionType(int code, String name) {
        this.code = code;
        this.name = name;
        this.type = 0;
    }

    public static PermissionType getPerByCode(int code) {
        if (allPermission == null) {
            allPermission = new HashMap<>();
            for (PermissionType permissionEnum : PermissionType.values()) {
                allPermission.put(permissionEnum.code, permissionEnum);
            }
        }
        return allPermission.get(code);
    }

    public boolean isShowAdmin() {
        return showAdmin;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
