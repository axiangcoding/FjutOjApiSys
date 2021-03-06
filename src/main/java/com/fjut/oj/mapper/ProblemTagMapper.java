package com.fjut.oj.mapper;

import com.fjut.oj.pojo.ProblemTagPO;
import com.fjut.oj.pojo.UserTag1;
import com.fjut.oj.pojo.UserTag2;
import com.fjut.oj.pojo.ProblemTagRecordPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cjt
 */
public interface ProblemTagMapper {

    /**
     * 查询全部题目标签
     *
     * @return
     */
    List<ProblemTagPO> queryAllProblemTag();

    List<ProblemTagRecordPO> problemTagRecord(@Param("pid") Integer pid, @Param("username") String username);

    List<ProblemTagRecordPO> problemTagRecordLimitNum(@Param("pid") Integer pid, @Param("from") Integer from, @Param("num") Integer num);

    int addTag(@Param("pid") Integer pid, @Param("username") String username, @Param("tagid") Integer tagid, @Param("rating") Integer rating);

    int delTag(@Param("pid") Integer pid, @Param("username") String username, @Param("pid") Integer tagid);

    int addProblemTag(@Param("username") String tagName);

    int renameProblemTag(@Param("id") Integer id, @Param("name") Integer name);

    List<UserTag1> queryUserTag1(@Param("username") String username);

    List<UserTag2> queryUserTag2();
}
