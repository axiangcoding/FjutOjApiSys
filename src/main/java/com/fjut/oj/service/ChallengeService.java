package com.fjut.oj.service;

import com.fjut.oj.pojo.*;
import com.fjut.oj.util.JsonInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/***
 * @author axiang
 */
public interface ChallengeService {

    /**
     * 更新挑战模块开放模块
     *
     * @param username
     * @param pid
     * @return
     */
    boolean updateOpenBlock(String username, Integer pid);

    List<ChallengeBlockForUser> queryAllChallengeBlocks();

    Integer queryChallengeBlockTotalScoreByBlockId(Integer blockId);

    ChallengeBlock queryChallengeBlockByBlockId(Integer blockId);

    List<Integer> queryShowedChallengeBlocksByUsername(String username);

    List<t_challenge_condition> queryAllChallengeConditions();

    List<Integer> queryChallengeOpenBlocksByUsername(String username);

    List<ChallengeBlockForUser> queryChallengeBlocksScoredByUsername(String username);

    List<ChallengeConditionForBlock> queryChallengeConditionByBlockId(Integer blockId);

    List<ChallengeProblemForBlock> queryChallengeBlockProblemByBlockId(Integer blockId, Integer startIndex);

    Integer queryChallengeBlockProblemCountByBlockId(Integer blockId);

    List<Status> queryAllBlockSolvedProblemByUsername(String username);
}
