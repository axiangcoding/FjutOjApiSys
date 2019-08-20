package com.fjut.oj.service;

import com.fjut.oj.pojo.*;

import java.util.List;

public interface ContestService {

    /**
     * 根据条件获取比赛列表，一次20条
     *
     * @param startIndex
     * @param kind
     * @return
     */
    List<Contest> queryContestByCondition(Integer startIndex, Integer kind);

    /**
     * 根据条件获取比赛列表数量
     *
     * @param kind
     * @return
     */
    Integer queryContestCountByCondition(Integer kind);

    /**
     * 根据 比赛ID 获取比赛详情
     *
     * @param cid
     * @return
     */
    Contest queryContestByCid(Integer cid);

    /**
     * 根据多重条件查询比赛的提交记录，一次30条
     * @param startIndex
     * @param cid
     * @param nick
     * @param pid
     * @param result
     * @return
     */
    List<ViewUserStatus> queryContestStatusByCondition(Integer startIndex, Integer cid, String nick, Integer pid, Integer result);

    /**
     * 根据多个条件 查询比赛的提交数量
     *
     * @param cid
     * @param nick
     * @param pid
     * @param result
     * @return
     */
    Integer queryContestStatusCountByCondition(Integer cid, String nick, Integer pid, Integer result);

    List<Contestuser> getContestUsers(int cid, int pagenum);

    Integer getContestUsersNum(int cid);

    /**
     * 根据 cid 查询比赛内的题目
     *
     * @param cid
     * @return
     */
    List<ContestProblemInfo> queryContestProblem(Integer cid);

    List<Status> getContestStatus(Integer cid, Integer pagenum);

    List<ContestRank> getContestRank(Integer cid);

    String getContestPassword(Integer cid);

    Integer getContestUser(Integer cid, String username);

    Integer insertContest(Contest contest);

    Integer getMaxContestId();

    Integer insertContestProblem(ContestProblem contestProblem);

    Integer insertContestuser(Contestuser contestuser);

    Integer getContestStatusNum(Integer cid);
}
