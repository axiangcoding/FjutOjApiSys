package com.fjut.oj.mapper;

import com.fjut.oj.pojo.ProblemSample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cjt
 */
public interface ProblemSampleMapper {

    List<ProblemSample> queryProblemsampleById(@Param("pid") Integer pid);

    Integer insertProblemSample(@Param("problemsample") ProblemSample problemsample);
}
