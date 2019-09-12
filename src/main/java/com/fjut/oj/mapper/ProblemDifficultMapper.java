package com.fjut.oj.mapper;

import com.fjut.oj.pojo.ProblemDifficultPO;

import java.util.List;

/**
 * @author axiang [20190909]
 */
public interface ProblemDifficultMapper {
    /**
     * 查询全部的题目难度
     * @return
     */
    List<ProblemDifficultPO> queryAllProblemDiff();

}
