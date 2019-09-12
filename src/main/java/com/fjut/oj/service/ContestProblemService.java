package com.fjut.oj.service;

import com.fjut.oj.pojo.ContestProblemPO;

import java.util.List;

public interface ContestProblemService {



    Integer insertContestProblem(ContestProblemPO contestProblemPO);

    boolean deleteAllContestProblemByCid(Integer cid);

    /**
     * 根据cid查询比赛所有题目
     * @param cid
     * @return 返回比赛题目PO类
     */
    List<ContestProblemPO> queryContestProblemsByCid(Integer cid);
}
