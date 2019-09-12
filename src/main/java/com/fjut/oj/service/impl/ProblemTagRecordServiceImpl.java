package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.ProblemTagRecordMapper;
import com.fjut.oj.pojo.ProblemTagRecordPO;
import com.fjut.oj.service.ProblemTagRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author axiang [20190909]
 */
@Service("ProblemTagRecord")
public class ProblemTagRecordServiceImpl implements ProblemTagRecordService {
    @Autowired
    ProblemTagRecordMapper problemTagRecordMapper;

    @Override
    public List<ProblemTagRecordPO> queryAllProblemTagRecord() {
        return problemTagRecordMapper.queryAllProblemTagRecord();
    }
}
