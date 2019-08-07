package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.ProblemSampleMapper;
import com.fjut.oj.pojo.Problemsample;
import com.fjut.oj.service.ProblemSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("problemSampleService")
public class ProblemSampleServiceImpl implements ProblemSampleService {

    @Autowired
    private ProblemSampleMapper problemSampleMapper;

    @Override
    public Integer insertProblemSample(Problemsample problemsample){
        return problemSampleMapper.insertProblemSample(problemsample);
    }

}
