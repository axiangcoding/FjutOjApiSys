package com.fjut.oj.service;

import com.fjut.oj.pojo.Status;
import com.fjut.oj.pojo.ViewUserSolve;
import com.fjut.oj.pojo.ViewUserStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatusService {

    List<Status> ShowAllStatus(); // 获取所有的提交情况

    List<Status> getAllStatusByUsername(String username);

    Integer allStatusNum();

    List<ViewUserStatus> queryStatus(Integer start);

    /**
     * 按照id查询评测记录
     * @param id
     * @return
     */
    Status queryStatusById(Integer id);

    ViewUserStatus queryStatusViewById(Integer id);

    /**
     * 查询某一条件下评测的数量
     * @param ruser
     * @param pid
     * @param result
     * @param language
     * @param start
     * @return
     */
    Integer queryCountAllStatusByConditions(String ruser, Integer pid, Integer result, Integer language, Integer start);

    /**
     * 查询某一条件下的评测
      */
    List<Status> queryAllStatusByConditions(String ruser, Integer pid, Integer result, Integer language, Integer start);

    Integer querySubmitCountByUsername(String name);    // 获取一个用户所有提交题目的次数

    Integer queryMaxStatusId();  // 获取最大的评测表的 id

    boolean insertStatus(Status status); // 插入一条记录

    boolean updateStatus(Status status); // 更新一条评测记录

    boolean updateStatusAfterJudge(Status status);

    /**
     * 根据用户名获取AC的题目列表
     * @param username
     * @return
     */
    List<ViewUserSolve> queryUserSolveProblemByUsername(String username);
}
