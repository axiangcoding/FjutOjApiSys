package com.fjut.oj.mapper;

import com.fjut.oj.pojo.ProblemView;
import org.apache.ibatis.annotations.Param;

/**
 * @author cjt
 */
public interface ProblemViewMapper {

    ProblemView queryProblemView(@Param("pid") Integer pid);

    Integer insertProblemView(@Param("problemView") ProblemView problemView);
}
