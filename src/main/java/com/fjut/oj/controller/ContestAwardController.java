package com.fjut.oj.controller;

import com.fjut.oj.pojo.TeamMemberInfo;
import com.fjut.oj.service.TeamMemberInfoService;
import com.fjut.oj.util.JsonMsg;
import com.fjut.oj.util.ResultString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * TODO: 把 JsonMsg 替换为 JsonInfo
 * @author axiang
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/addContestAward")
public class ContestAwardController {

    @Autowired
    private TeamMemberInfoService teamMemberInfoService;

    @PostMapping("/IContestAward")
    public JsonMsg IContestAward(HttpServletRequest req) {
        String time = req.getParameter("time") == null ? "2000-01-01" : req.getParameter("time");
        String username1 = req.getParameter("username1") == null ? "" : req.getParameter("username1");
        String username2 = req.getParameter("username2") == null ? "" : req.getParameter("username2");
        String username3 = req.getParameter("username3") == null ? "" : req.getParameter("username3");
        String name1 = req.getParameter("name1") == null ? "" : req.getParameter("name1");
        String name2 = req.getParameter("name2") == null ? "" : req.getParameter("name2");
        String name3 = req.getParameter("name3") == null ? "" : req.getParameter("name3");
        String contestLevelStr = req.getParameter("contestLevel");
        Integer contestLevel = ResultString.contestLevelToint(contestLevelStr);
        String awardsLevelStr = req.getParameter("awardsLevel");
        Integer awardsLevel = ResultString.awardLevelToint(awardsLevelStr);
        String text = req.getParameter("text") == null ? "" : req.getParameter("text");
        System.out.println(contestLevelStr + " " + awardsLevelStr);
        TeamMemberInfo teamMemberInfo = new TeamMemberInfo();
        teamMemberInfo.setTime(time);
        teamMemberInfo.setUsername1(username1);
        teamMemberInfo.setUsername2(username2);
        teamMemberInfo.setUsername3(username3);
        teamMemberInfo.setName1(name1);
        teamMemberInfo.setName2(name2);
        teamMemberInfo.setName3(name3);
        teamMemberInfo.setContest_level(contestLevel);
        teamMemberInfo.setAwards_level(awardsLevel);
        teamMemberInfo.setText(text);

        Integer num = teamMemberInfoService.insertTeamMemberInfo(teamMemberInfo);
        if (num != 0) {
            return JsonMsg.success().addInfo("添加成功");
        }
        return JsonMsg.fail().addInfo("添加失败");
    }

    @RequestMapping("/UContestAward")
    public JsonMsg UContestAward(HttpServletRequest req) {
        String strId = req.getParameter("id");
        if (strId==null){
            return JsonMsg.fail().addInfo("未传入id");
        }
        Integer id = Integer.parseInt(strId);
        TeamMemberInfo t = teamMemberInfoService.queryTeamMemberInfoById(id);
        if (t == null){
            return JsonMsg.fail().addInfo("无该id信息");
        }
        String time = req.getParameter("time");
        String username1 = req.getParameter("username1");
        String username2 = req.getParameter("username2");
        String username3 = req.getParameter("username3");
        String name1 = req.getParameter("name1");
        String name2 = req.getParameter("name2");
        String name3 = req.getParameter("name3");
        String contestLevelStr = req.getParameter("contestLevel");
        Integer contestLevel = ResultString.contestLevelToint(contestLevelStr);
        String awardsLevelStr = req.getParameter("awardsLevel");
        Integer awardsLevel = ResultString.awardLevelToint(awardsLevelStr);
        String text = req.getParameter("text");

        t.setTime(time == null ? t.getTime() : time);
        t.setUsername1(username1 == null ? t.getUsername1() : username1);
        t.setUsername2(username2 == null ? t.getUsername2() : username2);
        t.setUsername3(username3 == null ? t.getUsername3() : username3);
        t.setName1(name1 == null ? t.getName1() : name1);
        t.setName2(name2 == null ? t.getName2() : name2);
        t.setName3(name3 == null ? t.getName3() : name3);
        t.setContest_level(contestLevel == null ? t.getContest_level() : contestLevel);
        t.setAwards_level(awardsLevel == null ? t.getAwards_level() : awardsLevel);
        t.setText(text == null ? t.getText() : text);
        Integer num = teamMemberInfoService.replaceTeamMemberInfo(t);
        if (num == 0){
            return JsonMsg.fail().addInfo("修改失败");
        }
        return JsonMsg.success().addInfo("修改成功");
    }

    @RequestMapping("/GAllTeamMemberInfo")
    @ResponseBody
    public JsonMsg GAllTeamMemberInfo(HttpServletRequest req) {
        Integer pagenum = Integer.parseInt(req.getParameter("pagenum") == null ? "1" : req.getParameter("pagenum"));
        Integer totalnum = teamMemberInfoService.queryAllCountTeamMemberInfo();
        Integer totalpage = (totalnum % 50) == 0 ? totalnum / 50 : totalnum / 50 + 1;
        Integer start = (pagenum - 1) * 50;

        List<TeamMemberInfo> list = teamMemberInfoService.queryAllTeamMemberInfo(start);
        return JsonMsg.success().addInfo(totalpage).addInfo(list);
    }
}
