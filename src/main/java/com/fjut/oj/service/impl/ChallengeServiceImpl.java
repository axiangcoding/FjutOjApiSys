package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.ChallengeMapper;
import com.fjut.oj.pojo.*;
import com.fjut.oj.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeMBeanException;
import java.util.List;

@Service("ChallengeService")
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    ChallengeMapper challengeMapper;

    /**
     * 更新挑战模块开放模块
     * 逻辑如下：
     * 已知使用的前置条件：一道题目完成了判题，返回了结果
     * 先获取该题隶属挑战模块，如果不属于任何模块则直接退出，如果属于进入下一步骤
     * 获取题目隶属挑战模块的后置模块（所有可能会解锁的待解锁模块）
     * 依次对这些可能会解锁的待解锁模块计算解锁条件，条件全部满足则解锁
     *
     * @param username
     * @param pid
     * @return
     */
    @Override
    public boolean updateOpenBlock(String username, Integer pid) {
        // 获取该题隶属挑战模块
        Integer blockId = challengeMapper.queryBlocksByPid(pid);
        if (null == blockId) {
            return false;
        }
        // 获取题目隶属挑战模块的后置模块
        List<Integer> belongBlocks = challengeMapper.queryBelongBlocksByBlockId(blockId);
        // 遍历这些后置模块，依次取出他们的各个条件，逐一判断是否满足
        for (Integer belongBlock : belongBlocks) {
            boolean canOpen = true;
            // 取出解锁条件
            List<ChallengeConditionForBlock> conditionForBlocks = challengeMapper.queryChallengeConditionByBlockId(belongBlock);
            // 遍历条件看是否全部满足，如果有部分不满足则标识位 canOpen = false
            for (ChallengeConditionForBlock conditionBlock : conditionForBlocks) {
                // 取得模块获得分数
                Integer score = challengeMapper.queryBlockSolvedScore(username, conditionBlock.getBlockId());
                if(null == score || score < conditionBlock.getNum()){
                    canOpen = false;
                    break;
                }
            }
            if(canOpen)
            {
                Integer ans = challengeMapper.insertOpenBlock(username,belongBlock);
                // TODO：发送一条消息
            }
        }

        return true;
    }

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
