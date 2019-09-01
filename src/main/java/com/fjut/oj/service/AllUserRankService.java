package com.fjut.oj.service;

import com.fjut.oj.pojo.UserPO;

import java.util.List;

public interface AllUserRankService {
    List<UserPO> allUsersRank(String order, String desc, Integer start);
    Integer queryUserCountByName(String username);
    List<UserPO> queryUserByName(String username, Integer start);
}
