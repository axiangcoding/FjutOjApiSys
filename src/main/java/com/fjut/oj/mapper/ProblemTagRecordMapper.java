package com.fjut.oj.mapper;

import com.fjut.oj.pojo.ProblemTagRecordPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [20190909]
 */
public interface ProblemTagRecordMapper {
    /**
     * 查询全部题目标签记录
     *
     * @return
     */
    List<ProblemTagRecordPO> queryAllProblemTagRecord();

    /**
     * 根据tagId 获取相关未贴标签的题目
     * @param tagId
     * @param username
     * @return
     */
    List<ProblemTagRecordPO> queryProblemTagRecordByTagId(@Param("tagId") Integer tagId,
                                                          @Param("username") String username);
}
