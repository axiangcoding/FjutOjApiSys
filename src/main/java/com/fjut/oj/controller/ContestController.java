package com.fjut.oj.controller;

import com.fjut.oj.interceptor.CheckUserIsAdmin;
import com.fjut.oj.pojo.*;
import com.fjut.oj.service.ContestService;
import com.fjut.oj.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * TODO: 把 JsonMsg 替换为 JsonInfoVO
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


    @CheckUserIsAdmin
    @Transactional(rollbackFor = RuntimeException.class)
    @RequestMapping("/insertContest")
    public JsonInfoVO insertContest(@RequestParam("username") String username,
                                    @RequestParam("name") String contestTitle,
                                    @RequestParam("beginTime") String beginTimeStr,
                                    @RequestParam("endTime") String endTimeStr,
                                    @RequestParam("ctype") String cTypeStr,
                                    @RequestParam(value = "registerBeginTime", required = false) String registerBeginTimeStr,
                                    @RequestParam(value = "registerEndTime", required = false) String registerEndTimeStr,
                                    @RequestParam("info") String info,
                                    @RequestParam("kind") String kindStr,
                                    @RequestParam("pidList") String pidList) {
        JsonInfoVO jsonInfoVO = new JsonInfoVO();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginTime;
        Date endTime;
        Date registerBeginTime = new Date();
        Date registerEndTime = new Date();
        Integer cType;
        Integer kind;
        try {
            beginTime = simpleDateFormat.parse(beginTimeStr);
            endTime = simpleDateFormat.parse(endTimeStr);
            if (registerBeginTimeStr != null && registerEndTimeStr != null) {
                registerBeginTime = simpleDateFormat.parse(registerBeginTimeStr);
                registerEndTime = simpleDateFormat.parse(registerEndTimeStr);
            }
            cType = Integer.parseInt(cTypeStr);
            kind = Integer.parseInt(kindStr);
        } catch (Exception e) {
            return new JsonInfoVO("ERROR", "参数错误");
        }
        ContestPO contestPO = new ContestPO();
        contestPO.setName(contestTitle);
        contestPO.setBeginTime(beginTime);
        contestPO.setEndTime(endTime);
        // FIXME: 暂时未用上，设置为0
        contestPO.setRankType(0);
        contestPO.setCtype(cType);
        // 密码非空
        contestPO.setPassword("");

        contestPO.setRegisterstarttime(registerBeginTime);
        contestPO.setRegisterendtime(registerEndTime);
        contestPO.setInfo(info);
        contestPO.setKind(kind);
        contestPO.setCreateuser(username);
        Integer ans = contestService.insertContest(contestPO);
        if (0 == ans) {
            return new JsonInfoVO("ERROR", "比赛添加失败");
        }
        Integer ansCount = contestService.insertAllContestProblem(contestPO.getId(), pidList);
        if (0 < ansCount) {
            jsonInfoVO.setSuccess("比赛添加成功！请到比赛页面查看！");
        }
        return jsonInfoVO;
    }

    /**
     * TODO：修改比赛信息的逻辑还没做好
     * @param cidStr
     * @param username
     * @param contestTitle
     * @param beginTimeStr
     * @param endTimeStr
     * @param cTypeStr
     * @param registerBeginTimeStr
     * @param registerEndTimeStr
     * @param info
     * @param kindStr
     * @param pidList
     * @return
     */
    @CheckUserIsAdmin
    @Transactional(rollbackFor = RuntimeException.class)
    @RequestMapping("/updateContest")
    public JsonInfoVO updateContest(@RequestParam("cid") String cidStr,
                                    @RequestParam("username") String username,
                                    @RequestParam("name") String contestTitle,
                                    @RequestParam("beginTime") String beginTimeStr,
                                    @RequestParam("endTime") String endTimeStr,
                                    @RequestParam("ctype") String cTypeStr,
                                    @RequestParam(value = "registerBeginTime", required = false) String registerBeginTimeStr,
                                    @RequestParam(value = "registerEndTime", required = false) String registerEndTimeStr,
                                    @RequestParam("info") String info,
                                    @RequestParam("kind") String kindStr,
                                    @RequestParam("pidList") String pidList) {
        JsonInfoVO jsonInfoVO = new JsonInfoVO();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginTime;
        Date endTime;
        Date registerBeginTime = new Date();
        Date registerEndTime = new Date();
        Integer cid;
        Integer cType;
        Integer kind;
        try {
            cid = Integer.parseInt(cidStr);
            // 查找比赛是否存在，如果不存在则直接返回
            if (!contestService.queryContestIsExist(cid)) {
                return new JsonInfoVO("ERROR", "没有找到该比赛");
            }
            beginTime = simpleDateFormat.parse(beginTimeStr);
            endTime = simpleDateFormat.parse(endTimeStr);
            if (registerBeginTimeStr != null && registerEndTimeStr != null) {
                registerBeginTime = simpleDateFormat.parse(registerBeginTimeStr);
                registerEndTime = simpleDateFormat.parse(registerEndTimeStr);
            }
            cType = Integer.parseInt(cTypeStr);
            kind = Integer.parseInt(kindStr);
        } catch (Exception e) {
            return new JsonInfoVO("ERROR", "参数错误");
        }
        ContestPO contestPO = new ContestPO();
        contestPO.setId(cid);
        contestPO.setName(contestTitle);
        contestPO.setBeginTime(beginTime);
        contestPO.setEndTime(endTime);
        // FIXME: 暂时未用上，设置为0
        contestPO.setRankType(0);
        contestPO.setCtype(cType);
        // 密码非空
        contestPO.setPassword("");
        contestPO.setRegisterstarttime(registerBeginTime);
        contestPO.setRegisterendtime(registerEndTime);
        contestPO.setInfo(info);
        contestPO.setKind(kind);
        contestPO.setCreateuser(username);
        // TODO: 更新比赛信息
        int ans =  contestService.updateContestByCid(contestPO);
        if (0 == ans) {
            return new JsonInfoVO("ERROR", "比赛更新失败");
        }
        // TODO: 更新题目列表
        int ansDel = contestService.deleteAllContestProblemByCid(cid);
        int ansAdd = contestService.insertAllContestProblem(contestPO.getId(), pidList);
        jsonInfoVO.setSuccess("比赛更新成功！");
        return jsonInfoVO;
    }


    /**
     * TODO: 还有筛选条件未用上
     * 根据条件查询比赛列表
     *
     * @param pageNumStr
     * @param kindStr
     * @return
     */
    @GetMapping("/getContestByCondition")
    public JsonInfoVO getContestByCondition(@RequestParam("pageNum") String pageNumStr,
                                            @RequestParam("kind") String kindStr) {
        JsonInfoVO jsonInfoVO = new JsonInfoVO();
        Integer pageNum = Integer.parseInt(pageNumStr);
        Integer startIndex = (pageNum - 1) * 20;
        Integer kind = Integer.parseInt(kindStr);
        List<ContestPO> list = contestService.queryContestByCondition(startIndex, kind);
        Integer contestNum = contestService.queryContestCountByCondition(kind);
        if (list == null) {
            jsonInfoVO.setFail("没有比赛信息！");
            return jsonInfoVO;
        }
        jsonInfoVO.setSuccess();
        jsonInfoVO.addInfo(contestNum);
        jsonInfoVO.addInfo(list);
        return jsonInfoVO;
    }

    @GetMapping("/getContestStatusByPage")
    public JsonInfoVO getContestStatusByPage(@RequestParam("pageNum") String pageNumStr,
                                             @RequestParam("cid") String cidStr,
                                             @RequestParam(value = "nick", required = false) String nick,
                                             @RequestParam(value = "pid", required = false) String pidStr,
                                             @RequestParam(value = "result", required = false) String resultStr) {
        JsonInfoVO jsonInfoVO = new JsonInfoVO();
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
            jsonInfoVO.setFail("没有评测记录");
            return jsonInfoVO;
        }
        List<ViewUserStatus> viewUserStatuses = contestService.queryContestStatusByCondition(startIndex, cid, nick, pid, result);
        jsonInfoVO.setSuccess();
        jsonInfoVO.addInfo(viewUserStatuses);
        jsonInfoVO.addInfo(count);
        return jsonInfoVO;
    }

    @GetMapping("/getContestByCid")
    public JsonInfoVO getContestByCid(@RequestParam("cid") String cidStr) {
        JsonInfoVO jsonInfoVO = new JsonInfoVO();
        Integer cid = Integer.parseInt(cidStr);
        ContestPO contestPO = contestService.queryContestByCid(cid);
        if (null == contestPO) {
            jsonInfoVO.setFail("找不到比赛！");
            return jsonInfoVO;
        }

        List<ContestProblemInfoBO> contestProblemInfoBOS = contestService.queryContestProblem(cid);
        StringBuffer pidStr = new StringBuffer();
        for (ContestProblemInfoBO contestProblemInfoBO : contestProblemInfoBOS) {
            pidStr.append(contestProblemInfoBO.getTpid());
            pidStr.append(" ");
        }
        jsonInfoVO.setSuccess("找到比赛！");
        jsonInfoVO.addInfo(contestPO);
        jsonInfoVO.addInfo(pidStr);
        return jsonInfoVO;
    }

    @GetMapping("/getContestProblem")
    public JsonInfoVO getContestProblem(@RequestParam("cid") String cidStr) {
        JsonInfoVO jsonInfoVO = new JsonInfoVO();
        Integer cid = Integer.parseInt(cidStr);
        List<ContestProblemInfoBO> contestProblemInfoBOS = contestService.queryContestProblem(cid);
        if (contestProblemInfoBOS == null) {
            return new JsonInfoVO("FAIL", "该比赛内没有题目！");
        }
        jsonInfoVO.addInfo(contestProblemInfoBOS);
        jsonInfoVO.setSuccess();
        return jsonInfoVO;
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
