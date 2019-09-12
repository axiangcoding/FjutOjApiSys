package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.ContestProblemMapper;
import com.fjut.oj.pojo.ContestProblemPO;
import com.fjut.oj.service.ContestProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contestProblemService")
public class ContestProblemServiceImpl implements ContestProblemService {

    @Autowired
    private ContestProblemMapper contestProblemMapper;

    @Override
    public List<ContestProblemPO> queryContestProblemsByCid(Integer cid){
        return contestProblemMapper.queryContestProblemsByCid(cid);
    }

    @Override
    public Integer insertContestProblem(ContestProblemPO contestProblemPO){
        return contestProblemMapper.insertContestProblem(contestProblemPO);
    }

    @Override
    public boolean deleteAllContestProblemByCid(Integer cid) {
        return contestProblemMapper.deleteAllContestProblemByCid(cid) > 0;
    }
}
