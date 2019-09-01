package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.AllUsersRankMapper;
import com.fjut.oj.pojo.UserPO;
import com.fjut.oj.service.AllUserRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("allUsersRankService")
public class AllUserRankServiceImpl implements AllUserRankService {

    @Autowired
    private AllUsersRankMapper allUsersRankMapper;

    @Override
    public List<UserPO> allUsersRank(String order, String desc, Integer start) {
        List<UserPO> list = allUsersRankMapper.queryAllUsersRank(order,desc,start);
        return list;
    }

    @Override
    public Integer queryUserCountByName(String username){
        return allUsersRankMapper.queryUserCountByName(username);
    }

    @Override
    public List<UserPO> queryUserByName(String username, Integer start){
        return allUsersRankMapper.queryUserByName(username,start);
    }
}
