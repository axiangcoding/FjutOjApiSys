package com.fjut.oj.service;

import com.fjut.oj.pojo.ClockInPO;

import java.util.Date;
import java.util.List;


public interface ClockInService {
    List<ClockInPO> queryAllClockInByDate(Date time);

    List<ClockInPO> queryAllClockIn();

    List<ClockInPO> queryAllClockInByUsername(String username, Integer pageNum);

    List<ClockInPO> queryClockInByUsernameAndDate(String username, Date date);

    boolean insertClockIn(ClockInPO clockin);
}
