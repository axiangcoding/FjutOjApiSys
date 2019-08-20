package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.ClockInMapper;
import com.fjut.oj.pojo.t_clock_in;
import com.fjut.oj.service.ClockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: wyx
 * @Despriction:
 * @Date:Created in 10:33 2019/6/10
 * @Modify By:
 */

@Service("ClockInService")
public class ClockInServiceImpl implements ClockInService {
    @Autowired
    private ClockInMapper clockInMapper;


    @Override
    public List<t_clock_in> queryAllClockInByDate(Date time) {
        return clockInMapper.queryAllClockInByDate(time);
    }

    @Override
    public List<t_clock_in> queryAllClockIn() {
        return clockInMapper.queryAllClockIn();
    }

    @Override
    public List<t_clock_in> queryAllClockInByUsername(String username, Integer pageNum) {
        return clockInMapper.queryAllClockInByUsername(username, pageNum);
    }

    @Override
    public List<t_clock_in> queryClockInByUsernameAndDate(String username, Date date) {
        return clockInMapper.queryClockInByUsernameAndDate(username, date);
    }

    @Override
    public boolean insertClockIn(t_clock_in clockin) {
        int ret = clockInMapper.insertClockIn(clockin);
        return ret == 1;
    }
}
