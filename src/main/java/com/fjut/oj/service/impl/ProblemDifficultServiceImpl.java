package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.ProblemDifficultMapper;
import com.fjut.oj.pojo.ProblemDifficultPO;
import com.fjut.oj.service.ProblemDifficultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author axiang [20190909]
 */
@Service("ProblemDifficultService")
public class ProblemDifficultServiceImpl implements ProblemDifficultService {
    @Autowired
    ProblemDifficultMapper problemDifficultMapper;

    @Override
    public List<ProblemDifficultPO> queryAllProblemDiff() {
        return problemDifficultMapper.queryAllProblemDiff();
    }
}
