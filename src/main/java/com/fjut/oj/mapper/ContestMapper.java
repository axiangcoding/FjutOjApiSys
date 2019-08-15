package com.fjut.oj.mapper;

import com.fjut.oj.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cjt
 */
public interface ContestMapper {
    List<Contest> getAllContest(@Param("pagenum") Integer pagenum);

    Contest getContestById(@Param("cid") Integer cid);

    List<Contestuser> getContestUsers(@Param("cid") int cid, @Param("pagenum") int pagenum);

    Integer getContestUsersNum(@Param("cid") int cid);

    Integer getAllContestNum();

    List<ContestProblemInfo> getContestProblem(@Param("cid") int cid);

    List<Status> getContestStatus(@Param("cid") int cid, @Param("pagenum") int pagenum);

    List<ContestRank> getContestRank(@Param("cid") int cid);

    String getContestPassword(@Param("cid") Integer cid);

    Integer getContestUser(@Param("cid") Integer cid, @Param("username") String username);

    Integer insertContest(@Param("contest") Contest contest);

    Integer getMaxContestId();

    Integer insertContestProblem(@Param("contestProblem") ContestProblem contestProblem);

    Integer insertContestuser(@Param("contestuser") Contestuser contestuser);

    Integer getContestStatusNum(@Param("cid") Integer cid);
}
