package com.fjut.oj.service;

import com.fjut.oj.pojo.User;

import java.util.List;

public interface AllUserRankService {
    List<User> allUsersRank(String order, String desc, Integer start);
    Integer queryUserCountByName(String username);
    List<User> queryUserByName(String username, Integer start);
}
