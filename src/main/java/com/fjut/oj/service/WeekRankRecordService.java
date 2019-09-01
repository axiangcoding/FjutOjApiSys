package com.fjut.oj.service;

import com.fjut.oj.pojo.SubmisssionRecord;
import com.fjut.oj.pojo.UserPO;
import com.fjut.oj.pojo.WeekRankRecord;

import java.util.List;

public interface WeekRankRecordService {
    void add(WeekRankRecord weekRankRecord, int day, int value);

    void addStatus(WeekRankRecord weekRankRecord, SubmisssionRecord submisssionRecord);

    void sort(WeekRankRecord weekRankRecord);

    List<UserPO> getActiveRank();
}
