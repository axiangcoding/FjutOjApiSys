package com.fjut.oj.service;

import com.fjut.oj.pojo.TableClockIn;

import java.util.Date;
import java.util.List;


public interface ClockInService {
    List<TableClockIn> queryAllClockInByDate(Date time);

    List<TableClockIn> queryAllClockIn();

    List<TableClockIn> queryAllClockInByUsername(String username, Integer pageNum);

    List<TableClockIn> queryClockInByUsernameAndDate(String username, Date date);

    boolean insertClockIn(TableClockIn clockin);
}
