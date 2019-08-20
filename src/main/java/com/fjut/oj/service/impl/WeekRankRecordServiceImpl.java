package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.SubmissionRecordMapper;
import com.fjut.oj.mapper.UserMapper;
import com.fjut.oj.pojo.SubmisssionRecord;
import com.fjut.oj.pojo.User;
import com.fjut.oj.pojo.WeekRankRecord;
import com.fjut.oj.service.WeekRankRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service("WeekRankRecordService")
public class WeekRankRecordServiceImpl implements WeekRankRecordService {

    @Autowired
    private SubmissionRecordMapper submissionRecordMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(WeekRankRecord weekRankRecord, int day, int value) {
        int[] acnum = weekRankRecord.getAcNum();
        int[] scoreEveryDay = weekRankRecord.getScoreEveryDay();
        int score = weekRankRecord.getScore();
        acnum[day] += 1;
        weekRankRecord.setAcNum(acnum);
        scoreEveryDay[day] += value;
        weekRankRecord.setScoreEveryDay(scoreEveryDay);
        score += value;
        weekRankRecord.setScore(score);
    }

    @Override
    public void addStatus(WeekRankRecord weekRankRecord, SubmisssionRecord submisssionRecord) {
        if (submisssionRecord.getSubmitTime().before(weekRankRecord.getFrom())) {
            return;
        }
        int day = (int) ((submisssionRecord.getSubmitTime().getTime() - weekRankRecord.getFrom().getTime()) / (1000 * 60 * 60 * 24));
        if (day < 0 || day >= 30) {
            return;
        }
        WeekRankRecord record = weekRankRecord.getResultsMap().get(submisssionRecord.getRuser());
        if (record == null) {
            record = new WeekRankRecord();
            record.setUsername(submisssionRecord.getRuser());
            Map<String, WeekRankRecord> resultsMap = weekRankRecord.getResultsMap();
            resultsMap.put(submisssionRecord.getRuser(), record);
            weekRankRecord.setResultsMap(resultsMap);
        }
        this.add(record, day, WeekRankRecord.getConfig(day));
    }

    @Override
    public void sort(WeekRankRecord weekRankRecord) {
        List<WeekRankRecord> resultsList = weekRankRecord.getResultsList();
        for (WeekRankRecord record : weekRankRecord.getResultsMap().values()) {
            resultsList.add(record);
            weekRankRecord.setResultsList(resultsList);
        }
        Collections.sort(resultsList);
        for (int i = 0; i < weekRankRecord.getResultsList().size(); i++) {
            WeekRankRecord record = weekRankRecord.getResultsList().get(i);
            if (i == 0) {
                record.setRank(1);
            } else if (record.getScore() == weekRankRecord.getResultsList().get(i - 1).getScore()) {
                record.setRank(resultsList.get(i - 1).getRank());
            } else {
                record.setRank(i + 1);
            }
        }
    }

    @Override
    public List<User> getActiveRank() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Timestamp getListTo = new Timestamp(calendar.getTimeInMillis());
        calendar.add(Calendar.DATE, -7);
        Timestamp getListFrom = new Timestamp(calendar.getTimeInMillis());
        List<SubmisssionRecord> list = submissionRecordMapper.getAreaSubmissionRecord(getListFrom, getListTo);
        System.out.println(list);
        List<SubmisssionRecord> list_1 = submissionRecordMapper.getFailAreaSubmissionRecord(getListFrom, getListTo);
        Map<String, Integer> hash_score = new HashMap<>();
        Map<String, Set<Integer>> hash_day = new HashMap<>();
        int score = 0;
        for (SubmisssionRecord status : list) {
            Set<Integer> set = new HashSet<>();
            Set<Integer> checkInTime = new HashSet<>();
            for (SubmisssionRecord sr : list) {
                if (sr.getRuser().equals(status.getRuser())) {
                    set.add(sr.getPid());
                    int day = (int) ((sr.getSubmitTime().getTime() - getListFrom.getTime()) / (1000 * 60 * 60 * 24));
                    checkInTime.add(day);
                }
            }
            if (set.size() >= 35) {
                score = score + 140;
            } else {
                score = set.size() * 4 + score;
            }
            hash_score.put(status.getRuser(), score);
            hash_day.put(status.getRuser(), checkInTime);
            score = 0;
        }
        for (SubmisssionRecord status : list_1) {
            Set<Integer> set = new HashSet<>();
            for (SubmisssionRecord sr : list_1) {
                if (sr.getRuser().equals(status.getRuser())) {
                    set.add(sr.getPid());
                    int day = (int) ((sr.getSubmitTime().getTime() - getListFrom.getTime()) / (1000 * 60 * 60 * 24));
                    hash_day.get(sr.getRuser()).add(day);
                }
            }
            if (set.size() >= 60) {
                hash_score.put(status.getRuser(), hash_score.get(status.getRuser()) + 120);
            } else {
                hash_score.put(status.getRuser(), hash_score.get(status.getRuser()) + set.size() * 2);
            }
        }
        for (String map : hash_day.keySet()) {
            int j = 0;
            for (int i = 0; i < 6; i++) {
                if (hash_day.get(map).contains(i)) {
                    j += 1;
                    hash_score.put(map, hash_score.get(map) + j * 3);
                } else {
                    j = 0;
                }
            }
        }
        List<String> rank = new ArrayList<>();
        for (int j = 0; j < hash_score.size(); j++) {
            String max_name = "";
            int max_score = 0;
            for (String username : hash_score.keySet()) {
                if (hash_score.get(username) > max_score) {
                    max_name = username;
                    max_score = hash_score.get(username);
                }
            }
            hash_score.put(max_name, 0);
            rank.add(max_name);
        }
        List<User> rank_user = new ArrayList<>();
        for (int i = 0; i < rank.size(); i++) {
            User user = userMapper.queryUserByName(rank.get(i));
            rank_user.add(user);
        }
        return rank_user;
    }

//    public synchronized void compute(WeekRankRecord weekRankRecord,boolean init){
//        if (init && isInit) return ;
//        isInit = true;
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY,0);
//        calendar.set(Calendar.MINUTE,0);
//        calendar.set(Calendar.SECOND,0);
//        calendar.set(Calendar.MILLISECOND,0);
//        Timestamp getListTo = new Timestamp(calendar.getTimeInMillis());
//        for(int i=0;i<8;i++){
//            Timestamp to = new Timestamp(calendar.getTimeInMillis());
//            calendar.add(Calendar.DATE,-30);
//            Timestamp from = new Timestamp(calendar.getTimeInMillis());
//            WeekRankRecord weekRankRecord_1 = weekRankRecord.getWeekRank(i);
//            weekRankRecord_1= new WeekRankRecord();
//            weekRankRecord_1.setFrom(from);
//            weekRankRecord_1.setTo(to);
//            weekRankRecord.setWeekRank(weekRankRecord_1,i);
//            calendar.add(Calendar.DATE,30-1);
//        }
//        calendar.add(Calendar.DATE,-23);
//        Timestamp getListFrom = new Timestamp(calendar.getTimeInMillis());
//
//        List<SubmisssionRecord> list = submissionRecordMapper.getAreaSubmissionRecord(getListFrom,getListTo);
//        Map<String,Set<Integer>> hash = new HashMap<>();
//        for (SubmisssionRecord status:list) {
//            Set<Integer> set = hash.get(status.getRuser());
//            if(set == null){
//                set = new HashSet<>();
//                hash.put(status.getRuser(),set);
//            }
//            if(set.contains(status.getPid())) continue;
//            set.add(status.getPid());
//            for (WeekRankRecord rank:weekRankRecord.getWeekRank()) {
//                this.addStatus(rank,status);
//            }
//        }
//        for (WeekRankRecord rank:weekRankRecord.getWeekRank()) this.sort(rank);
//        for(int i=1;i<8-1;i++){
//            for(WeekRankRecord record: weekRankRecord.getWeekRank(i).getResultsList()){
//                if(!weekRankRecord.getWeekRank(0).getResultsMap().containsKey(record.getUsername())){
//                    Map<String, WeekRankRecord> resultsMap = weekRankRecord.getWeekRank(0).getResultsMap();
//                    resultsMap.put(record.getUsername(),null);
//                    weekRankRecord.setResultsMap(resultsMap);
//                    WeekRankRecord newRecord = new WeekRankRecord();
//                    newRecord.setUsername(record.getUsername());
//                    newRecord.setRank(-1);
//                    List<WeekRankRecord> resultsList = weekRankRecord.getWeekRank(0).getResultsList();
//                    resultsList.add(newRecord);
//                    weekRankRecord.setResultsList(resultsList);
//                }
//            }
//        }
//    }
}
