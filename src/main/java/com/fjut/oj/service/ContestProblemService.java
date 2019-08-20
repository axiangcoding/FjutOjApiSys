package com.fjut.oj.service;

import com.fjut.oj.pojo.ContestProblem;

import java.util.List;

public interface ContestProblemService {

    List<ContestProblem> getContestProblemsByCid(Integer cid);

    Integer insertContestProblem(ContestProblem contestProblem);
}
