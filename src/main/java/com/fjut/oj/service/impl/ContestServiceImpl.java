package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.ContestMapper;
import com.fjut.oj.pojo.*;
import com.fjut.oj.service.ContestService;
import com.fjut.oj.util.ResultString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("ContestService")
public class ContestServiceImpl implements ContestService {

    @Autowired
    private ContestMapper contestMapper;

    @Override
    public List<Contest> getAllContest(Integer pagenum) {
        List<Contest> list = contestMapper.getAllContest(pagenum);
        Calendar calendar = Calendar.getInstance();

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);

        System.out.println(dateString.toString() + "22");
        for (Contest element : list){
            if(element.getEndTime().compareTo(dateString)<0){
                element.setStatus(2);
            }else{
                if (element.getBeginTime().compareTo(dateString)>0){
                    element.setStatus(1);
                }else {
                    element.setStatus(0);
                }
            }
        }
        return list;
    }

    @Override
    public Contest getContestById(Integer cid){
        return contestMapper.getContestById(cid);
    }

    @Override
    public List<Contestuser> getContestUsers(int cid, int pagenum) {
        List<Contestuser> ContestUsers = contestMapper.getContestUsers(cid,pagenum);
        return ContestUsers;
    }

    @Override
    public Integer getContestUsersNum(int cid) {
        Integer contestUsersNum = contestMapper.getContestUsersNum(cid);
        return contestUsersNum;
    }

    @Override
    public Integer getAllContestNum() {
        Integer allContestNum = contestMapper.getAllContestNum();
        return allContestNum;
    }

    @Override
    public List<ContestProblemInfo> getContestProblem(Integer cid) {
        List<ContestProblemInfo> contestProblemList = contestMapper.getContestProblem(cid);
        return contestProblemList;
    }

    @Override
    public List<Status> getContestStatus(Integer cid,Integer pagenum) {
        List<Status> contestStatusList = contestMapper.getContestStatus(cid,pagenum);
        for (Status element : contestStatusList){
            element.setOtherinfo(ResultString.getResultString(element.getResult()));
            element.setSubmitlanguage(ResultString.getSubmitLanguage(element.getLang()));
        }
        return contestStatusList;
    }

    @Override
    public List<ContestRank> getContestRank(Integer cid) {
        List<ContestRank> contestRankList = contestMapper.getContestRank(cid);
        return contestRankList;
    }

    @Override
    public String getContestPassword(Integer cid){
        return contestMapper.getContestPassword(cid);
    }

    @Override
    public Integer getContestUser(Integer cid,String username){
        return contestMapper.getContestUser(cid,username);
    }

    @Override
    public Integer insertContest(Contest contest){
        return contestMapper.insertContest(contest);
    }

    @Override
    public Integer getMaxContestId(){
        return contestMapper.getMaxContestId();
    }

    @Override
    public Integer insertContestProblem(ContestProblem contestProblem){
        return contestMapper.insertContestProblem(contestProblem);
    }

    @Override
    public Integer insertContestuser(Contestuser contestuser){
        return contestMapper.insertContestuser(contestuser);
    }

    @Override
    public Integer getContestStatusNum(Integer cid) {
        Integer contestStatusNum = contestMapper.getContestStatusNum(cid);
        return contestStatusNum;
    }
}
