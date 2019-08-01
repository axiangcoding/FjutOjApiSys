package com.fjut.oj.mapper;

import com.fjut.oj.pojo.Status;
import com.fjut.oj.pojo.ViewUserSolve;
import com.fjut.oj.pojo.ViewUserStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cjt
 */
public interface StatusMapper {

    List<Status> ShowAllStatus();

    List<Status> getAllStatusByUsername(@Param("username") String username);

    Integer allStatusNum();

    List<ViewUserStatus> queryStatus(@Param("start") Integer start);

    Status queryStatusById(@Param("id") Integer id);

    List<Status> queryAllStatusByUser(@Param("ruser") String ruser);

    List<Status> queryAllStatusByPid(@Param("pid") String pid);

    List<Status> queryAllStatusByConditions(@Param("ruser") String ruser, @Param("pid") Integer pid, @Param("result")Integer result, @Param("language")Integer language, @Param("start")Integer start);

    Integer queryCountAllStatusByConditions(@Param("ruser") String ruser, @Param("pid") Integer pid, @Param("result")Integer result, @Param("language")Integer language, @Param("start")Integer start);

    Integer querySubmitCountByUsername(@Param("ruser") String ruser);

    Integer queryMaxStatusId();

    /**
     * 插入评测记录
     * @param status
     * @return
     */
    Integer insertStatus(@Param("status") Status status);

    Integer updateStatus(@Param("status") Status status);

    Integer updateStatusAfterJudge(@Param("status")Status status);

    /**
     * 获取用户的解决题目列表
     * @param username
     * @return
     */
    List<ViewUserSolve> queryUserSolveProblemByUsername(@Param("username") String username);

}
