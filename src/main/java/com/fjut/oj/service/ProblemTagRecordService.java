package com.fjut.oj.service;

import com.fjut.oj.pojo.ProblemTagRecordPO;

import java.util.List;

/**
 * @author axiang [20190911]
 */
public interface ProblemTagRecordService {
    /**
     * 查询全部题目标签记录
     *
     * @return
     */
    List<ProblemTagRecordPO> queryAllProblemTagRecord();

}
