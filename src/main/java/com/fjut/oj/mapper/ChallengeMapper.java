package com.fjut.oj.mapper;

import com.fjut.oj.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [20190628]
 */
public interface ChallengeMapper {

    /**
     * 插入一条开启模块记录
     *
     * @param username
     * @param blockId
     * @return
     */
    Integer insertOpenBlock(@Param("username") String username, @Param("blockId") Integer blockId);

    /**
     * 获取全部挑战模块
     *
     * @return ChallengeBlockBO
     */
    List<ChallengeBlockBO> queryAllChallengeBlocks();

    /**
     * 根据ID 获取挑战模块的ID,名称，总分值
     *
     * @param blockId
     * @return
     */
    Integer queryChallengeBlockTotalScoreByBlockId(@Param("blockId") Integer blockId);

    /**
     * 根据ID 获取挑战模块的表详情
     *
     * @param blockId
     * @return ChallengeBlockPO
     */
    ChallengeBlockPO queryChallengeBlockByBlockId(@Param("blockId") Integer blockId);

    /**
     * 获取用户显示的挑战模块ID
     *
     * @param username
     * @return
     */
    List<Integer> queryShowedChallengeBlocksByUsername(@Param("username") String username);

    /**
     * 获取全部挑战模块的解锁条件
     *
     * @return
     */
    List<ChallengeConditionPO> queryAllChallengeConditions();

    /**
     * 获取用户已开放的挑战模块ID
     *
     * @param username
     * @return
     */
    List<Integer> queryChallengeOpenBlocksByUsername(@Param("username") String username);


    /**
     * 根据模块ID 获取挑战模式中某个模块的解锁条件（前置模块）
     *
     * @param blockId
     * @return
     */
    List<ChallengeConditionForBlock> queryChallengeConditionByBlockId(@Param("blockId") Integer blockId);


    /**
     * 根据用户名 获取挑战模块已完成分数
     *
     * @param username
     * @return
     */
    List<ChallengeBlockBO> queryChallengeBlocksScoredByUsername(@Param("username") String username);

    /**
     * 根据模块ID和起始INDEX 获取15个题目
     *
     * @param blockId
     * @param startIndex
     * @return
     */
    List<ChallengeProblemForBlock> queryChallengeBlockProblemByBlockId(@Param("blockId") Integer blockId, @Param("startIndex") Integer startIndex);


    /**
     * 根据模块ID 获取挑战模块的题目数量
     *
     * @param blockId
     * @return
     */
    Integer queryChallengeBlockProblemCountByBlockId(@Param("blockId") Integer blockId);


    /**
     * 根据用户名 获取用户解决的题目和状态
     *
     * @param username
     * @return
     */
    List<Status> queryAllBlockSolvedProblemByUsername(@Param("username") String username);

    /**
     * 获取题号为pid的题目隶属于哪个模块
     *
     * @param pid
     * @return
     */
    Integer queryBlocksByPid(@Param("pid") Integer pid);

    /**
     * 获取题目隶属挑战模块的后置模块（所有可能会解锁的待解锁模块）
     *
     * @param blockId
     * @return
     */
    List<Integer> queryBelongBlocksByBlockId(@Param("blockId") Integer blockId);

    /**
     * 获取挑战模块的以获得分值情况
     *
     * @param username
     * @param blockId
     * @return
     */
    Integer queryBlockSolvedScore(@Param("username") String username, @Param("blockId") Integer blockId);


}
