package com.fjut.oj.controller;

import com.fjut.oj.interceptor.CheckUserIsLogin;
import com.fjut.oj.interceptor.CheckUserPrivate;
import com.fjut.oj.pojo.TableUserAuth;
import com.fjut.oj.pojo.User;
import com.fjut.oj.service.StatusService;
import com.fjut.oj.service.UserRadarService;
import com.fjut.oj.service.UserService;
import com.fjut.oj.util.JsonInfo;
import com.fjut.oj.util.SHAUtils;
import com.fjut.oj.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO: 界定权限控制，改不合理的传参
 *
 * @author axiang [20190710]
 *
 */
@Controller
@CrossOrigin
@RequestMapping("/user")
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private UserRadarService userRadarService;

    /**
     * 注册一个用户
     *
     * @param req
     * @return
     */
    @PostMapping("/insertUser")
    public JsonInfo insertUser(HttpServletRequest req,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("nick") String nick,
                               @RequestParam(value = "gender", required = false) String genderStr,
                               @RequestParam(value = "school", required = false) String school,
                               @RequestParam(value = "Email", required = false) String Email,
                               @RequestParam(value = "motto", required = false) String motto,
                               @RequestParam(value = "type", required = false) String typeStr,
                               @RequestParam(value = "Mark", required = false) String Mark
    ) {
        JsonInfo jsonInfo = new JsonInfo();
        User tmp = userService.getUserByUsername(req.getParameter("username"));
        if (null != tmp) {
            jsonInfo.setFail("用户名已经存在");
            return jsonInfo;
        }
        Date currentTime = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);

        User user = new User();
        TableUserAuth userAuth = new TableUserAuth();
        String salt = UUIDUtils.getUUID32();
        // 加盐密码
        String newPassword = salt + password;
        // 对加盐密码使用SHA1加密
        String encryptedPwd = SHAUtils.SHA1(newPassword);
        userAuth.setUsername(username);
        userAuth.setSalt(salt);
        userAuth.setPassword(encryptedPwd);
        userAuth.setAttemptLoginFailCount(0);
        userAuth.setLocked(0);
        userAuth.setUnlockTime(currentTime);
        userAuth.setLastLoginTime(currentTime);

        user.setUsername(username);
        user.setPassword(password);
        user.setNick(nick);
        user.setGender(Integer.parseInt(genderStr == null ? "0" : genderStr));
        user.setSchool(school == null ? " " : school);
        user.setEmail(Email == null ? " " : Email);
        user.setMotto(motto == null ? " " : motto);
        user.setRegistertime(dateString);
        user.setType(Integer.parseInt(typeStr == null ? "0" : typeStr));
        user.setMark(Mark == null ? " " : Mark);
        user.setRating(0);
        user.setRatingnum(0);
        user.setAcb(0);
        user.setName(req.getParameter("name") == null ? " " : req.getParameter("name"));
        user.setFaculty(req.getParameter("faculty") == null ? " " : req.getParameter("faculty"));
        user.setMajor(req.getParameter("major") == null ? " " : req.getParameter("major"));
        user.setCla(req.getParameter("cla") == null ? " " : req.getParameter("cla"));
        user.setNo(req.getParameter("no") == null ? " " : req.getParameter("no"));
        user.setPhone(req.getParameter("phone") == null ? " " : req.getParameter("phone"));
        user.setAcnum(0);
        user.setInTeamStatus(Integer.parseInt(req.getParameter("inTeamStatus") == null ? "0" : req.getParameter("inTeamStatus")));
        user.setInTeamLv(Integer.parseInt(req.getParameter("inTeamLv") == null ? "0" : req.getParameter("inTeamLv")));
        user.setRank(Integer.parseInt(req.getParameter("rank") == null ? "2223" : req.getParameter("rank")));
        user.setGraduationTime(req.getParameter("graduationTime") == null ? "2022-07-01 00:00:00" : req.getParameter("graduationTime"));
        boolean flag = userService.insertUser(user, userAuth);
        if (flag) {
            jsonInfo.setSuccess("添加用户成功！");
        } else {
            jsonInfo.setFail("添加用户失败！");
        }
        return jsonInfo;
    }

    /**
     * 修改用户信息
     *
     * @param req
     * @return
     */
    @CheckUserPrivate
    @PostMapping("/updateUser")
    public JsonInfo updateUser(HttpServletRequest req) {
        JsonInfo jsonInfo = new JsonInfo();
        User tmp = userService.getUserByUsername(req.getParameter("username"));
        if (tmp == null) {
            jsonInfo.setFail("用户名不存在！");
            return jsonInfo;
        }
        tmp.setPassword(req.getParameter("password") == null ? tmp.getPassword() : req.getParameter("password"));
        tmp.setNick(req.getParameter("nick") == null ? tmp.getNick() : req.getParameter("nick"));
        tmp.setGender(Integer.parseInt(req.getParameter("gender") == null ? tmp.getGender().toString() : req.getParameter("gender")));
        tmp.setSchool(req.getParameter("school") == null ? tmp.getSchool() : req.getParameter("school"));
        tmp.setEmail(req.getParameter("Email") == null ? tmp.getEmail() : req.getParameter("Email"));
        tmp.setMotto(req.getParameter("motto") == null ? tmp.getMotto() : req.getParameter("motto"));
        tmp.setType(Integer.parseInt(req.getParameter("type") == null ? tmp.getType().toString() : req.getParameter("type")));
        tmp.setMark(req.getParameter("Mark") == null ? tmp.getMark() : req.getParameter("Mark"));

        tmp.setRating(Integer.parseInt(req.getParameter("rating") == null ? tmp.getRating().toString() : req.getParameter("rating")));
        tmp.setRatingnum(Integer.parseInt(req.getParameter("ratingnum") == null ? tmp.getRatingnum().toString() : req.getParameter("ratingnum")));
        tmp.setAcb(Integer.parseInt(req.getParameter("acb") == null ? tmp.getAcb().toString() : req.getParameter("acb")));

        tmp.setName(req.getParameter("name") == null ? tmp.getName() : req.getParameter("name"));
        tmp.setFaculty(req.getParameter("faculty") == null ? tmp.getFaculty() : req.getParameter("faculty"));
        tmp.setMajor(req.getParameter("major") == null ? tmp.getMajor() : req.getParameter("major"));
        tmp.setCla(req.getParameter("cla") == null ? tmp.getCla() : req.getParameter("cla"));
        tmp.setNo(req.getParameter("no") == null ? tmp.getNo() : req.getParameter("no"));
        tmp.setPhone(req.getParameter("phone") == null ? tmp.getPhone() : req.getParameter("phone"));
        tmp.setInTeamStatus(Integer.parseInt(req.getParameter("inTeamStatus") == null ? tmp.getInTeamStatus().toString() : req.getParameter("inTeamStatus")));
        tmp.setInTeamLv(Integer.parseInt(req.getParameter("inTeamLv") == null ? tmp.getInTeamLv().toString() : req.getParameter("inTeamLv")));
        tmp.setRank(Integer.parseInt(req.getParameter("rank") == null ? tmp.getRank().toString() : req.getParameter("rank")));
        tmp.setGraduationTime(req.getParameter("graduationTime") == null ? tmp.getGraduationTime() : req.getParameter("graduationTime"));

        Integer num = userService.updateUserByUsername(tmp);
        if (1 == num) {
            jsonInfo.setSuccess("修改用户信息成功！");

        } else {
            jsonInfo.setFail("修改用户信息失败！");
        }
        return jsonInfo;
    }

    /**
     * 获取用户的雷达图
     */
    @CheckUserIsLogin
    @GetMapping("/getUserRadar")
    public JsonInfo getUserRadar(@RequestParam("username") String username) {
        JsonInfo jsonInfo = new JsonInfo();
        String userRadar;
        userRadar = userRadarService.getUserRadar(username);
        jsonInfo.addInfo(userRadar);
        return jsonInfo;
    }

    /**
     * 获取一个用户提交所有题目的次数
     */
    @CheckUserIsLogin
    @GetMapping("/GSubmitCount")
    public JsonInfo querySubmitCountByUsername(@RequestParam("username") String username) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer num = statusService.querySubmitCountByUsername(username);
        if (null != num) {
            jsonInfo.setSuccess();
            jsonInfo.addInfo(num);
        } else {
            jsonInfo.setFail("未查询到该用户的提交信息！");
        }
        return jsonInfo;
    }

    /**
     * 获取一个用户个人信息界面的信息
     */
    @CheckUserIsLogin
    @RequestMapping("/getUserInfo")
    public JsonInfo queryUserInfoByUsername(@RequestParam("username") String username) {
        JsonInfo jsonInfo = new JsonInfo();
        User user = userService.getUserByUsername(username);

        if (null != user) {
            jsonInfo.setSuccess();
            jsonInfo.addInfo(user);
        } else {
            jsonInfo.setFail("未查询到该用户的信息！");
        }
        return jsonInfo;
    }

    /**
     * 获取一个用户贴过题目标签的数量
     */
    @RequestMapping("/GPutTagNum")
    public JsonInfo queryPutTagNumByUsername(HttpServletRequest req, HttpServletResponse resp) {
        JsonInfo jsonInfo = new JsonInfo();
        String username = req.getParameter("username");
        Integer num = userService.queryPutTagNumByUsername(username);
        if (num != 0) {
            jsonInfo.setSuccess();
            jsonInfo.addInfo(num);
        } else {
            jsonInfo.setFail("未找到用户贴标签的信息");
        }
        return jsonInfo;
    }

    /**
     * 获取一个用户已经 AC 和未解决 题目的数量
     */
    @RequestMapping("/GStatusProblems")
    public JsonInfo queryStatusProblemsByUsername(HttpServletRequest req) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer status = Integer.parseInt(req.getParameter("status") == null ? "0" : req.getParameter("status"));
        String username = req.getParameter("username");
        List<Integer> list = userService.queryStatusProblemsByUsername(status, username);
        if (list != null) {
            jsonInfo.addInfo(list);
            jsonInfo.setSuccess();
        } else {
            jsonInfo.setFail("未查询到用户相关的题目信息");
        }
        return jsonInfo;
    }

    /**
     * 查询一个用户待贴标签的题目
     */
    @RequestMapping("/GNotPutTagProblems")
    public JsonInfo queryCanViewCodeProblemsByUsername(HttpServletRequest req, HttpServletResponse resp) {
        JsonInfo jsonInfo = new JsonInfo();
        String username = req.getParameter("username");
        List<Integer> list = userService.queryNotPutTagProblemsByUsername(username);
        jsonInfo.addInfo(list);
        jsonInfo.setSuccess();
        return jsonInfo;
    }


    @RequestMapping(value = "/awardinfo", method = RequestMethod.POST)
    public JsonInfo getAwardinfo(HttpServletResponse response, HttpServletRequest request) {
        JsonInfo jsonInfo = new JsonInfo();
        String username = request.getParameter("username");
        List<String> list = userService.queryAwardInfo(username);
        jsonInfo.setSuccess();
        jsonInfo.addInfo(list);
        return jsonInfo;
    }

    @RequestMapping("/getRatingGraph")
    public JsonInfo getRatingGraph(@RequestParam("username") String username) {
        JsonInfo jsonInfo = new JsonInfo();
        Map<String, Integer> list = (Map<String, Integer>) userService.getRatingGraph(username);
        if (null != list) {
            jsonInfo.setSuccess();
            jsonInfo.addInfo(list);
        } else {
            jsonInfo.setFail("未查询到该用户的信息");
        }
        return jsonInfo;
    }

    @RequestMapping("/getAcGraph")
    public JsonInfo getAcGraph(@RequestParam("username") String username) {
        JsonInfo jsonInfo = new JsonInfo();
        //FIXME: 函数错误
        List<Object> list = (List<Object>) userService.getAcGraph(username);
        if (null != list) {
            jsonInfo.setSuccess();
            jsonInfo.addInfo(list);
        } else {
            jsonInfo.setFail("未查询到该用户的信息");
        }
        return jsonInfo;
    }
}
