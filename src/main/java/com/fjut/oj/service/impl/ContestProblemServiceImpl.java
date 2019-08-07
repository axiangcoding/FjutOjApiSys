package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.ContestProblemMapper;
import com.fjut.oj.pojo.ContestProblem;
import com.fjut.oj.service.ContestProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contestProblemService")
public class ContestProblemServiceImpl implements ContestProblemService {

    @Autowired
    private ContestProblemMapper contestProblemMapper;

    @Override
    public List<ContestProblem> getContestProblemsByCid(Integer cid){
        return contestProblemMapper.getContestProblemsByCid(cid);
    }

    @Override
    public Integer insertContestProblem(ContestProblem contestProblem){
        return contestProblemMapper.insertContestProblem(contestProblem);
    }
}
