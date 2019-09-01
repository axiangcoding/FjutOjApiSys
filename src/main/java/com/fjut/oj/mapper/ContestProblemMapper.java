package com.fjut.oj.mapper;

import com.fjut.oj.pojo.ContestProblem;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjt
 */
public interface ContestProblemMapper {

    /**
     * 根据 cid 获取全部比赛题目
     *
     * @param cid
     * @return
     */
    List<ContestProblem> getContestProblemsByCid(@Param("cid") Integer cid);


    /**
     * 插入多条比赛题目
     *
     * @param problems
     * @return
     */
    Integer insertContestProblems(@Param("contestProblems") ArrayList<ContestProblem> problems);

    /**
     * 插入比赛题目
     * @param contestProblem
     * @return
     */
    Integer insertContestProblem(@Param("contestProblem") ContestProblem contestProblem);
}
