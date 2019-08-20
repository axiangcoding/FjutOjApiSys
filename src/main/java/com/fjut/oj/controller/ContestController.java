package com.fjut.oj.controller;

import com.fjut.oj.pojo.*;
import com.fjut.oj.service.ContestService;
import com.fjut.oj.util.JsonInfo;
import com.fjut.oj.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * TODO: 把 JsonMsg 替换为 JsonInfo
 *
 * @author cjt
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/contest")
public class ContestController {

    @Autowired
    private ContestService contestService;

    /**
     * TODO: 还有筛选条件未用上
     * 根据条件查询比赛列表
     *
     * @param pageNumStr
     * @param kindStr
     * @return
     */
    @GetMapping("/getContestByCondition")
    public JsonInfo getContestByCondition(@RequestParam("pageNum") String pageNumStr,
                                          @RequestParam("kind") String kindStr) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer pageNum = Integer.parseInt(pageNumStr);
        Integer startIndex = (pageNum - 1) * 20;
        Integer kind = Integer.parseInt(kindStr);
        List<Contest> list = contestService.queryContestByCondition(startIndex, kind);
        Integer contestNum = contestService.queryContestCountByCondition(kind);
        if (list == null) {
            jsonInfo.setFail("没有比赛信息！");
            return jsonInfo;
        }
        jsonInfo.setSuccess();
        jsonInfo.addInfo(contestNum);
        jsonInfo.addInfo(list);
        return jsonInfo;
    }

    @GetMapping("/getContestStatusByPage")
    public JsonInfo getContestStatusByPage(@RequestParam("pageNum") String pageNumStr,
                                           @RequestParam("cid") String cidStr,
                                           @RequestParam(value = "nick", required = false) String nick,
                                           @RequestParam(value = "pid", required = false) String pidStr,
                                           @RequestParam(value = "result", required = false) String resultStr) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer pageNum = Integer.parseInt(pageNumStr);
        Integer startIndex = (pageNum - 1) * 30;
        if (null != nick && !"".equals(nick)) {
            nick = "%" + nick + "%";
        } else {
            nick = null;
        }
        Integer cid = Integer.parseInt(cidStr);
        Integer pid = null;
        if (null != pidStr && !"".equals(pidStr)) {
            pid = Integer.parseInt(pidStr);
        }
        Integer result = null;
        if (!"-1".equals(resultStr) && null != resultStr && !"".equals(resultStr)) {
            result = Integer.parseInt(resultStr);
        }

        Integer count = contestService.queryContestStatusCountByCondition(cid, nick, pid, result);
        if (0 == count) {
            jsonInfo.setFail("没有评测记录");
            return jsonInfo;
        }
        List<ViewUserStatus> viewUserStatuses = contestService.queryContestStatusByCondition(startIndex, cid, nick, pid, result);
        jsonInfo.setSuccess();
        jsonInfo.addInfo(viewUserStatuses);
        jsonInfo.addInfo(count);
        return jsonInfo;
    }

    @GetMapping("/getContestByCid")
    public JsonInfo getContestByCid(@RequestParam("cid") String cidStr) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer cid = Integer.parseInt(cidStr);
        Contest contest = contestService.queryContestByCid(cid);
        if (null == contest) {
            jsonInfo.setFail("找不到比赛！");
            return jsonInfo;
        }
        jsonInfo.setSuccess();
        jsonInfo.addInfo(contest);
        return jsonInfo;
    }

    @GetMapping("/getContestProblem")
    public JsonInfo getContestProblem(@RequestParam("cid") String cidStr) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer cid = Integer.parseInt(cidStr);
        List<ContestProblemInfo> contestProblemInfos = contestService.queryContestProblem(cid);
        if (contestProblemInfos == null) {
            return new JsonInfo("FAIL", "该比赛内没有题目！");
        }
        jsonInfo.addInfo(contestProblemInfos);
        jsonInfo.setSuccess();
        return jsonInfo;
    }

    @RequestMapping("/IContest")
    @ResponseBody
    public JsonMsg IContest(HttpServletRequest request, HttpServletResponse response) {
//        Contest contest = new Contest();
//        Integer oldid = contestService.getMaxContestId();
//        Integer id = oldid + 1;
//        String name = request.getParameter("name");
//        String beginTime = request.getParameter("beginTime");
//        String endTime = request.getParameter("endTime");
//        String rankTypeStr = request.getParameter("rankType") == null ? "2" : request.getParameter("rankType");
//        Integer rankType = Integer.parseInt(rankTypeStr);
//        String ctypeStr = request.getParameter("ctype") == null ? "0" : request.getParameter("ctype");
//        Integer ctype = Integer.parseInt(ctypeStr);
//        String password = request.getParameter("password") == null ? "" : request.getParameter("password");
//        //String registerstarttime = request.getParameter("registerstarttime");
//        //String registerendtime = request.getParameter("registerendtime");
//        String kindStr = request.getParameter("kind") == null ? "0" : request.getParameter("kind");
//        Integer kind = Integer.parseInt(kindStr);
//
//        contest.setId(id);
//        contest.setName(name);
//        contest.setBeginTime(beginTime);
//        contest.setEndTime(endTime);
//        contest.setRankType(rankType);
//        contest.setCtype(ctype);
//        contest.setKind(kind);
//        contest.setPassword(password);
//        Integer num = contestService.insertContest(contest);
//        if (num == 0) {
//            return JsonMsg.fail().addInfo("新建比赛失败");
//        }
        return JsonMsg.success().addInfo("新建比赛成功");
    }

    @RequestMapping("/IContestProblems")
    @ResponseBody
    public JsonMsg IContestProblems(HttpServletRequest request, HttpServletResponse response) {
        String pidStr = request.getParameter("pid");
        String tpidStr = request.getParameter("tpid");
        if (pidStr == null || tpidStr == null) {
            return JsonMsg.fail().addInfo("信息不足");
        }

        Integer oldid = contestService.getMaxContestId();
        Integer cid = oldid;
        Integer pid = Integer.parseInt(pidStr);
        Integer tpid = Integer.parseInt(tpidStr);
        ContestProblem contestProblem = new ContestProblem();
        contestProblem.setCid(cid);
        contestProblem.setPid(pid);
        contestProblem.setTpid(tpid);
        Integer num = contestService.insertContestProblem(contestProblem);
        if (num == 0) {
            return JsonMsg.fail().addInfo("新增比赛题目失败");
        }
        return JsonMsg.success().addInfo("新增比赛题目成功");
    }

    @RequestMapping("/IContestuser")
    @ResponseBody
    public JsonMsg IContestuser(HttpServletRequest request, HttpServletResponse response) {
        String cidStr = request.getParameter("cid");
        String username = request.getParameter("nick");
        if (cidStr == null || username == null) {
            return JsonMsg.fail().addInfo("信息不足");
        }
        Integer cid = Integer.parseInt(cidStr);
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);

        Contestuser contestuser = new Contestuser();
        contestuser.setCid(cid);
        contestuser.setUsername(username);
        contestuser.setStatu(1);
        contestuser.setInfo("");
        contestuser.setTime(dateString);
        Integer num = contestService.insertContestuser(contestuser);
        if (num == 0) {
            return JsonMsg.fail().addInfo("新增比赛用户失败");
        }
        return JsonMsg.success().addInfo("新增比赛用户成功");
    }

    @RequestMapping("/CheckContestPassword")
    @ResponseBody
    public JsonMsg checkContestPassword(HttpServletRequest request, HttpServletResponse response) {
        String password = request.getParameter("password");
        String cidStr = request.getParameter("cid");
        if (password == null || cidStr == null) {
            return JsonMsg.fail().addInfo("未传入密码或比赛id");
        }
        Integer cid = Integer.parseInt(cidStr);
        String contestPassword = contestService.getContestPassword(cid);
        System.out.println(password + "  " + cid + " " + contestPassword + "   123213");
        if (contestPassword == null || contestPassword.isEmpty()) {
            return JsonMsg.success().addInfo("不需要密码");
        }
        if (password.equals(contestPassword)) {
            return JsonMsg.success().addInfo("密码输入正确");
        }
        return JsonMsg.fail().addInfo("密码输入错误");
    }

    @RequestMapping("/CheckIsApply")
    @ResponseBody
    public JsonMsg checkIsApply(HttpServletRequest request, HttpServletResponse response) {
        String cidStr = request.getParameter("cid");
        String username = request.getParameter("nick");
        if (cidStr == null || username == null) {
            return JsonMsg.fail().addInfo("未传入比赛id或者用户名");
        }
        Integer cid = Integer.parseInt(cidStr);
        Integer num = contestService.getContestUser(cid, username);
        if (num == 0) {
            return JsonMsg.fail().addInfo("该用户未报名");
        }
        return JsonMsg.success().addInfo("该用户已报名");
    }

    @RequestMapping("/GContestUsers")
    @ResponseBody
    public JsonMsg getContestUsers(HttpServletRequest request, HttpServletResponse response) {
        Integer cid = Integer.parseInt(request.getParameter("cid"));
        Integer pagenum = Integer.parseInt(request.getParameter("pagenum"));
        List<Contestuser> list = contestService.getContestUsers(cid, (pagenum - 1) * 50);
        Integer contestUsersNum = contestService.getContestUsersNum(cid);
        if (contestUsersNum % 50 == 0) {
            contestUsersNum /= 50;
        } else {
            contestUsersNum = contestUsersNum / 50 + 1;
        }
        if (list == null) {
            return JsonMsg.success().addInfo("没有数据");
        }
        return JsonMsg.success().addInfo(contestUsersNum).addInfo(list);
    }

    @RequestMapping("/GContestUsersNum")
    @ResponseBody
    public JsonMsg getContestUsersNum(HttpServletRequest request, HttpServletResponse response) {
        Integer cid = Integer.parseInt(request.getParameter("cid"));
        Integer ContestUsersNum = contestService.getContestUsersNum(cid);
        if (ContestUsersNum == null) {
            return JsonMsg.success().addInfo(0);
        }
        return JsonMsg.success().addInfo(ContestUsersNum);
    }


    @RequestMapping("/GContestStatus")
    @ResponseBody
    public JsonMsg getContestStatus(HttpServletRequest request, HttpServletResponse response) {
        Integer cid = Integer.parseInt(request.getParameter("cid"));
        Integer pagenum = Integer.parseInt(request.getParameter("pagenum"));
        List<Status> contestStatusList = contestService.getContestStatus(cid, (pagenum - 1) * 50);

        Integer contestStatusNum = contestService.getContestStatusNum(cid);
        if (contestStatusNum % 50 == 0) {
            contestStatusNum /= 50;
        } else {
            contestStatusNum = contestStatusNum / 50 + 1;
        }

        if (contestStatusList == null) {
            return JsonMsg.success().addInfo("数据为空");
        }

        return JsonMsg.success().addInfo(contestStatusNum).addInfo(contestStatusList);
    }

    @RequestMapping("/GContestRank")
    @ResponseBody
    public JsonMsg getContestRank(HttpServletRequest request, HttpServletResponse response) {
        Integer cid = Integer.parseInt(request.getParameter("cid"));
        List<ContestRank> contestRankList = contestService.getContestRank(cid);
        if (contestRankList == null) {
            return JsonMsg.success().addInfo("数据为空");
        }
        return JsonMsg.success().addInfo(contestRankList);
    }
}
