package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.ProblemViewMapper;
import com.fjut.oj.pojo.ProblemView;
import com.fjut.oj.service.ProblemViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("problemViewService")
public class ProblemViewServiceImpl implements ProblemViewService {

    @Autowired
    private ProblemViewMapper problemViewMapper;

    @Override
    public ProblemView queryProblemView(Integer pid) {
        return problemViewMapper.queryProblemView(pid);
    }

    @Override
    public Integer insertProblemView(ProblemView problemView){
        return problemViewMapper.insertProblemView(problemView);
    }

}
