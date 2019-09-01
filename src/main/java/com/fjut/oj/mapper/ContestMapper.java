package com.fjut.oj.mapper;

import com.fjut.oj.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cjt
 */
public interface ContestMapper {
    /**
     * 新建一个比赛
     *
     * @param contest
     * @return
     */
    Integer insertContest(@Param("contest") Contest contest);

    /**
     * 根据条件一次性查询20条比赛列表
     *
     * @param startIndex
     * @param kind
     * @return
     */
    List<Contest> queryContestByCondition(@Param("startIndex") Integer startIndex, @Param("kind") Integer kind);

    /**
     * 根据条件查询比赛的数量
     *
     * @param kind
     * @return
     */
    Integer queryContestCountByCondition(@Param("kind") Integer kind);

    /**
     * 根据多重条件查询比赛的提交记录，一次30条
     *
     * @param startIndex
     * @param cid
     * @param nick
     * @param pid
     * @param result
     * @return
     */
    List<ViewUserStatus> queryContestStatusByCondition(@Param("startIndex") Integer startIndex,
                                                       @Param("cid") Integer cid,
                                                       @Param("nick") String nick,
                                                       @Param("pid") Integer pid,
                                                       @Param("result") Integer result);

    /**
     * 根据多重条件查询比赛的提交数量，一次30条
     *
     * @param cid
     * @param nick
     * @param pid
     * @param result
     * @return
     */
    Integer queryContestStatusCountByCondition(@Param("cid") Integer cid,
                                               @Param("nick") String nick,
                                               @Param("pid") Integer pid,
                                               @Param("result") Integer result);

    /**
     * 根据 Cid 获取比赛内容
     *
     * @param cid
     * @return
     */
    Contest queryContestByCid(@Param("cid") Integer cid);

    List<Contestuser> getContestUsers(@Param("cid") int cid, @Param("pagenum") int pagenum);

    Integer getContestUsersNum(@Param("cid") int cid);

    /**
     * 根据 cid 查询比赛内的题目
     *
     * @param cid
     * @return
     */
    List<ContestProblemInfo> queryContestProblem(@Param("cid") int cid);

    List<Status> getContestStatus(@Param("cid") int cid, @Param("pagenum") int pagenum);

    List<ContestRank> getContestRank(@Param("cid") int cid);

    String getContestPassword(@Param("cid") Integer cid);

    Integer getContestUser(@Param("cid") Integer cid, @Param("username") String username);


    Integer getMaxContestId();


    Integer insertContestuser(@Param("contestuser") Contestuser contestuser);

    Integer getContestStatusNum(@Param("cid") Integer cid);
}
