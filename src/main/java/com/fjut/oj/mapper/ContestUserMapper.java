package com.fjut.oj.mapper;

import com.fjut.oj.pojo.Contestuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cjt
 */
public interface ContestUserMapper {

    List<Contestuser> getContestUserById(@Param("cid") Integer cid);

    Integer insertContestUser(@Param("contestuser") Contestuser contestuser);
}
