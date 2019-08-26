package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.ClockInMapper;
import com.fjut.oj.pojo.TableClockIn;
import com.fjut.oj.service.ClockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author axiang [20190610]
 */

@Service("ClockInService")
public class ClockInServiceImpl implements ClockInService {
    @Autowired
    private ClockInMapper clockInMapper;


    @Override
    public List<TableClockIn> queryAllClockInByDate(Date time) {
        return clockInMapper.queryAllClockInByDate(time);
    }

    @Override
    public List<TableClockIn> queryAllClockIn() {
        return clockInMapper.queryAllClockIn();
    }

    @Override
    public List<TableClockIn> queryAllClockInByUsername(String username, Integer pageNum) {
        return clockInMapper.queryAllClockInByUsername(username, pageNum);
    }

    @Override
    public List<TableClockIn> queryClockInByUsernameAndDate(String username, Date date) {
        return clockInMapper.queryClockInByUsernameAndDate(username, date);
    }

    @Override
    public boolean insertClockIn(TableClockIn clockin) {
        int ret = clockInMapper.insertClockIn(clockin);
        return ret == 1;
    }
}
