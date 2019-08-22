package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.ContestMapper;
import com.fjut.oj.pojo.*;
import com.fjut.oj.service.ContestService;
import com.fjut.oj.util.ResultString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author cjt
 */
@Service("ContestService")
public class ContestServiceImpl implements ContestService {

    @Autowired
    private ContestMapper contestMapper;

    @Override
    public Integer insertContest(Contest contest) {
        return contestMapper.insertContest(contest);
    }

    @Override
    public Integer insertContestProblem(Integer cid, String pidStr) {
        ArrayList<ContestProblem> contestProblems = new ArrayList<>();
        String[] pids = pidStr.split(" ");
        int index = 0;
        for(String pid:pids)
        {
            ContestProblem contestProblem = new ContestProblem();
            contestProblem.setCid(cid);
            contestProblem.setPid(index);
            contestProblem.setTpid(Integer.parseInt(pid));
            contestProblems.add(contestProblem);
            index++;
        }
        return contestMapper.insertContestProblem(contestProblems);
    }

    @Override
    public List<Contest> queryContestByCondition(Integer startIndex, Integer kind) {
        List<Contest> list = contestMapper.queryContestByCondition(startIndex, kind);
        Date currentTime = new Date();
        for (Contest element : list) {
            if (element.getEndTime().compareTo(currentTime) < 0) {
                element.setStatus(2);
            } else if (element.getBeginTime().compareTo(currentTime) > 0) {
                element.setStatus(1);
            } else {
                element.setStatus(0);
            }
        }
        return list;
    }

    @Override
    public Contest queryContestByCid(Integer cid) {
        Contest contest = contestMapper.queryContestByCid(cid);
        Date currentTime = new Date();
        if (contest.getEndTime().compareTo(currentTime) < 0) {
            contest.setStatus(2);
        } else if (contest.getBeginTime().compareTo(currentTime) > 0) {
            contest.setStatus(1);
        } else {
            contest.setStatus(0);
        }
        return contest;
    }

    @Override
    public List<ViewUserStatus> queryContestStatusByCondition(Integer startIndex, Integer cid, String nick, Integer pid, Integer result) {
        List<ViewUserStatus> list = contestMapper.queryContestStatusByCondition(startIndex, cid, nick, pid, result);
        for (ViewUserStatus element : list) {
            element.setOtherinfo(ResultString.getResultString(element.getResult()));
            element.setSubmitlanguage(ResultString.getSubmitLanguage(element.getLang()));
        }
        return list;
    }

    @Override
    public Integer queryContestStatusCountByCondition(Integer cid, String nick, Integer pid, Integer result) {
        return contestMapper.queryContestStatusCountByCondition(cid, nick, pid, result);
    }

    @Override
    public List<Contestuser> getContestUsers(int cid, int pagenum) {
        List<Contestuser> ContestUsers = contestMapper.getContestUsers(cid, pagenum);
        return ContestUsers;
    }

    @Override
    public Integer getContestUsersNum(int cid) {
        Integer contestUsersNum = contestMapper.getContestUsersNum(cid);
        return contestUsersNum;
    }

    @Override
    public Integer queryContestCountByCondition(Integer kind) {
        Integer allContestNum = contestMapper.queryContestCountByCondition(kind);
        return allContestNum;
    }

    @Override
    public List<ContestProblemInfo> queryContestProblem(Integer cid) {
        List<ContestProblemInfo> contestProblemList = contestMapper.queryContestProblem(cid);
        return contestProblemList;
    }

    @Override
    public List<Status> getContestStatus(Integer cid, Integer pagenum) {
        List<Status> contestStatusList = contestMapper.getContestStatus(cid, pagenum);
        for (Status element : contestStatusList) {
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
    public String getContestPassword(Integer cid) {
        return contestMapper.getContestPassword(cid);
    }

    @Override
    public Integer getContestUser(Integer cid, String username) {
        return contestMapper.getContestUser(cid, username);
    }



    @Override
    public Integer getMaxContestId() {
        return contestMapper.getMaxContestId();
    }



    @Override
    public Integer insertContestuser(Contestuser contestuser) {
        return contestMapper.insertContestuser(contestuser);
    }

    @Override
    public Integer getContestStatusNum(Integer cid) {
        Integer contestStatusNum = contestMapper.getContestStatusNum(cid);
        return contestStatusNum;
    }
}
