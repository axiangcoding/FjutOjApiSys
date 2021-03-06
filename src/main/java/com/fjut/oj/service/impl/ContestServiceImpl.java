package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.ContestMapper;
import com.fjut.oj.mapper.ContestProblemMapper;
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

    @Autowired
    private ContestProblemMapper contestProblemMapper;

    @Override
    public Integer insertContest(ContestPO contestPO) {
        return contestMapper.insertContest(contestPO);
    }

    @Override
    public Integer insertAllContestProblem(Integer cid, String pidStr) {
        if("".equals(pidStr.trim()) || null == pidStr)
        {
            return 0;
        }
        ArrayList<ContestProblemPO> contestProblemPOS = new ArrayList<>();
        String[] pIds = pidStr.split("\\s+");
        int index = 0;
        for (String pid : pIds) {
            ContestProblemPO contestProblemPO = new ContestProblemPO();
            contestProblemPO.setCid(cid);
            contestProblemPO.setPid(index);
            contestProblemPO.setTpid(Integer.parseInt(pid));
            contestProblemPOS.add(contestProblemPO);
            index++;
        }
        return contestProblemMapper.insertAllContestProblem(contestProblemPOS);
    }

    @Override
    public Integer deleteAllContestProblemByCid(Integer cid) {
        return contestProblemMapper.deleteAllContestProblemByCid(cid);
    }

    @Override
    public Integer updateContestByCid(ContestPO contestPO) {
        return contestMapper.updateContestByCid(contestPO);
    }

    @Override
    public List<ContestPO> queryContestByCondition(Integer startIndex, Integer kind) {
        List<ContestPO> list = contestMapper.queryContestByCondition(startIndex, kind);
        if (0 == list.size()) {
            return null;
        }
        Date currentTime = new Date();
        for (ContestPO element : list) {
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
    public ContestPO queryContestByCid(Integer cid) {
        ContestPO contestPO = contestMapper.queryContestByCid(cid);
        if (null == contestPO) {
            return null;
        }
        Date currentTime = new Date();
        if (contestPO.getEndTime().compareTo(currentTime) < 0) {
            contestPO.setStatus(2);
        } else if (contestPO.getBeginTime().compareTo(currentTime) > 0) {
            contestPO.setStatus(1);
        } else {
            contestPO.setStatus(0);
        }
        return contestPO;
    }

    @Override
    public Boolean queryContestIsExist(Integer cid) {
        ContestPO contestPO = contestMapper.queryContestByCid(cid);
        return null != contestPO;
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
    public List<ContestProblemInfoBO> queryContestProblem(Integer cid) {
        List<ContestProblemInfoBO> contestProblemList = contestMapper.queryContestProblem(cid);
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
