package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.TeamMemberInfoMapper;
import com.fjut.oj.pojo.TeamMemberInfo;
import com.fjut.oj.service.TeamMemberInfoService;
import com.fjut.oj.util.ResultString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teamMemberInfoService")
public class TeamMemberInfoServiceImpl implements TeamMemberInfoService {

    @Autowired
    private TeamMemberInfoMapper teamMemberInfoMapper;

    @Override
    public List<TeamMemberInfo> queryAllTeamMemberInfo(Integer start){
        List<TeamMemberInfo> list = teamMemberInfoMapper.queryAllTeamMemberInfo(start);
        for (TeamMemberInfo t : list){
            t.setContestLevelStr(ResultString.contestLevelToStr(t.getContest_level()));
            t.setAwardsLevelStr(ResultString.awardLevelToStr(t.getAwards_level()));
        }
        return list;
    }


    @Override
    public Integer queryAllCountTeamMemberInfo(){
        return teamMemberInfoMapper.queryAllCountTeamMemberInfo();
    }

    @Override
    public TeamMemberInfo queryTeamMemberInfoById(Integer id){
        return teamMemberInfoMapper.queryTeamMemberInfoById(id);
    }

    @Override
    public Integer insertTeamMemberInfo(TeamMemberInfo teamMemberInfo) {
        return teamMemberInfoMapper.insertTeamMemberInfo(teamMemberInfo);
    }

    @Override
    public Integer replaceTeamMemberInfo(TeamMemberInfo teamMemberInfo){
        return  teamMemberInfoMapper.replaceTeamMemberInfo(teamMemberInfo);
    }
}
