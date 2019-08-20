package com.fjut.oj.service;

import com.fjut.oj.pojo.TeamMemberInfo;

import java.util.List;

public interface TeamMemberInfoService {

    List<TeamMemberInfo> queryAllTeamMemberInfo(Integer start);

    Integer queryAllCountTeamMemberInfo();

    TeamMemberInfo queryTeamMemberInfoById(Integer id);

    Integer insertTeamMemberInfo(TeamMemberInfo teamMemberInfo);

    Integer replaceTeamMemberInfo(TeamMemberInfo teamMemberInfo);
}
