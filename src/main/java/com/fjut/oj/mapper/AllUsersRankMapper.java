package com.fjut.oj.mapper;

import com.fjut.oj.pojo.UserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cjt
 */
public interface AllUsersRankMapper {

    /**
     * 查询用户的排名榜
     *
     * @param order
     * @param desc
     * @param start
     * @return
     */
    List<UserPO> queryAllUsersRank(@Param("order") String order, @Param("desc") String desc, @Param("start") Integer start);

    /**
     * 查询名字相似用户
     *
     * @param username
     * @param start
     * @return
     */
    List<UserPO> queryUserByName(@Param("username") String username, @Param("start") Integer start);

    /**
     * 查询名字相似用户的数量
     *
     * @param username
     * @return
     */
    Integer queryUserCountByName(@Param("username") String username);
}
