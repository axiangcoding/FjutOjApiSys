package com.fjut.oj.mapper;

import com.fjut.oj.pojo.ContestProblemPO;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjt
 */
public interface ContestProblemMapper {
    /**
     * 插入多条比赛题目
     *
     * @param problems
     * @return
     */
    Integer insertAllContestProblem(@Param("contestProblems") ArrayList<ContestProblemPO> problems);

    /**
     * 插入比赛题目
     * @param contestProblemPO
     * @return
     */
    Integer insertContestProblem(@Param("contestProblemPO") ContestProblemPO contestProblemPO);

    /**
     * 删除cid下的所有题目
     * @param cid
     * @return
     */
    Integer deleteAllContestProblemByCid(@Param("cid") Integer cid);

    /**
     * 根据 cid 获取全部比赛题目
     *
     * @param cid
     * @return 返回比赛题目类
     */
    List<ContestProblemPO> queryContestProblemsByCid(@Param("cid") Integer cid);

}
