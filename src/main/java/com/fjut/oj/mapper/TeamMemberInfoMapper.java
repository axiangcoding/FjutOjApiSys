package com.fjut.oj.mapper;

import com.fjut.oj.pojo.TeamMemberInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cjt
 */
public interface TeamMemberInfoMapper {

    List<TeamMemberInfo> queryAllTeamMemberInfo(@Param("start") Integer start);

    Integer queryAllCountTeamMemberInfo();

    TeamMemberInfo queryTeamMemberInfoById(@Param("id") Integer id);

    Integer insertTeamMemberInfo(@Param("teamMemberInfo") TeamMemberInfo teamMemberInfo);

    Integer replaceTeamMemberInfo(@Param("teamMemberInfo") TeamMemberInfo teamMemberInfo);
}
