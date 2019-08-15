package com.fjut.oj.mapper;

import com.fjut.oj.pojo.Problemsample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cjt
 */
public interface ProblemSampleMapper {

    List<Problemsample> queryProblemsampleById(@Param("pid") Integer pid);

    Integer insertProblemSample(@Param("problemsample") Problemsample problemsample);
}
