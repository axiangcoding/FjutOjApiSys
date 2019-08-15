package com.fjut.oj.mapper;

import com.fjut.oj.pojo.ContestProblem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cjt
 */
public interface ContestProblemMapper {

    List<ContestProblem> getContestProblemsByCid(@Param("cid") Integer cid);

    Integer insertContestProblem(@Param("contestProblem") ContestProblem contestProblem);
}
