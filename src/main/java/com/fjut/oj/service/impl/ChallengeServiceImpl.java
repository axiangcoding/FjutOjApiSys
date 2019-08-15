package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.ChallengeMapper;
import com.fjut.oj.pojo.*;
import com.fjut.oj.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ChallengeService")
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    ChallengeMapper challengeMapper;

    @Override
    public List<ChallengeBlockForUser> queryAllChallengeBlocks() {
        return challengeMapper.queryAllChallengeBlocks();
    }

    @Override
    public Integer queryChallengeBlockTotalScoreByBlockId(Integer blockId) {
        return challengeMapper.queryChallengeBlockTotalScoreByBlockId(blockId);
    }

    @Override
    public ChallengeBlock queryChallengeBlockByBlockId(Integer blockId) {
        return challengeMapper.queryChallengeBlockByBlockId(blockId);
    }

    @Override
    public List<Integer> queryShowedChallengeBlocksByUsername(String username) {
        return challengeMapper.queryShowedChallengeBlocksByUsername(username);
    }

    @Override
    public List<t_challenge_condition> queryAllChallengeConditions() {
        return challengeMapper.queryAllChallengeConditions();
    }

    @Override
    public List<Integer> queryChallengeOpenBlocksByUsername(String username) {
        return challengeMapper.queryChallengeOpenBlocksByUsername(username);
    }

    @Override
    public List<ChallengeBlockForUser> queryChallengeBlocksScoredByUsername(String username) {
        return challengeMapper.queryChallengeBlocksScoredByUsername(username);
    }

    @Override
    public List<ChallengeConditionForBlock> queryChallengeConditionByBlockId(Integer blockId) {
        return challengeMapper.queryChallengeConditionByBlockId(blockId);
    }

    @Override
    public List<ChallengeProblemForBlock> queryChallengeBlockProblemByBlockId(Integer blockId, Integer startIndex) {
        return challengeMapper.queryChallengeBlockProblemByBlockId(blockId, startIndex);
    }

    @Override
    public Integer queryChallengeBlockProblemCountByBlockId(Integer blockId) {
        return challengeMapper.queryChallengeBlockProblemCountByBlockId(blockId);
    }

    @Override
    public List<Status> queryAllBlockSolvedProblemByUsername(String username) {
        return challengeMapper.queryAllBlockSolvedProblemByUsername(username);
    }
}
