package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.UserMapper;
import com.fjut.oj.pojo.User;
import com.fjut.oj.service.AllTopTenService;
import com.fjut.oj.service.WeekRankRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AllTopTenService")
public class AllTopTenServiceImpl implements AllTopTenService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeekRankRecordService weekRankRecordService;

    @Override
    public List<User> getRatingTOP() {
        return userMapper.getRatingTop(0,10);
    }

    @Override
    public List<User> getAcbTOP() {
        return userMapper.getRichTop();
    }

    @Override
    public List<User> getAcTOP() {
        return userMapper.getAcTop();
    }

    @Override
    public List<User> getActiveTop() {
        return weekRankRecordService.getActiveRank();
    }
}
