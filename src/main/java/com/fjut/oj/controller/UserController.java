package com.fjut.oj.controller;

import com.fjut.oj.interceptor.CheckUserPrivate;
import com.fjut.oj.pojo.TokenModel;
import com.fjut.oj.pojo.User;
import com.fjut.oj.service.StatusService;
import com.fjut.oj.service.UserRadarService;
import com.fjut.oj.service.UserService;
import com.fjut.oj.interceptor.CheckUserIsLogin;
import com.fjut.oj.redis.TokenManager;
import com.fjut.oj.util.JsonInfo;
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
 * @author axiang [20190710]
 * TODO: 界定权限控制，改不合理的传参
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

    @Autowired
    private TokenManager tokenManager;

    /**
     * 登录认证，redis做鉴权
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public JsonInfo doLogin(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password) {
        JsonInfo jsonInfo = new JsonInfo();
        if (null == username || null == password) {
            jsonInfo.setFail("用户名或者密码为空！");
            return jsonInfo;
        }
        User user = userService.getUserByUsername(username);
        Integer count = userService.getUserByUsernameAndPassword(username, password);
        if (null == user) {
            // 用户名不存在
            jsonInfo.setFail("用户名不存在");

        } else if (count == 0) {
            // 用户名和密码不匹配
            jsonInfo.setFail("用户名或密码不匹配");
        } else {
            // 用户名和密码匹配
            jsonInfo.setSuccess("用户名和密码正确");
            TokenModel tokenModel = tokenManager.createToken(username);
            // TODO:做的简单字符串拼接，后期需要做一定的加密
            String auth = tokenModel.getUsername() + "_" + tokenModel.getToken();
            jsonInfo.addInfo(username);
            jsonInfo.addInfo(auth);
        }
        return jsonInfo;
    }

    /**
     * 注册一个用户
     *
     * @param req
     * @return
     */
    @PostMapping("/insertUser")
    public JsonInfo insertUser(HttpServletRequest req) {
        JsonInfo jsonInfo = new JsonInfo();
        User tmp = userService.getUserByUsername(req.getParameter("username"));
        if (null != tmp) {
            jsonInfo.setFail("用户名已经存在");
            return jsonInfo;
        }
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setNick(req.getParameter("nick") == null ? " " : req.getParameter("nick"));
        user.setGender(Integer.parseInt(req.getParameter("gender") == null ? "0" : req.getParameter("gender")));
        user.setSchool(req.getParameter("school") == null ? " " : req.getParameter("school"));
        user.setEmail(req.getParameter("Email") == null ? " " : req.getParameter("Email"));
        user.setMotto(req.getParameter("motto") == null ? " " : req.getParameter("motto"));

        Date currentTime = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
        user.setRegistertime(dateString);

        user.setType(Integer.parseInt(req.getParameter("type") == null ? "0" : req.getParameter("type")));
        user.setMark(req.getParameter("Mark") == null ? " " : req.getParameter("Mark"));
        user.setRating(-100000);
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
        // Date graduationTime = new Date();
        user.setGraduationTime(req.getParameter("graduationTime") == null ? "2022-07-01 00:00:00" : req.getParameter("graduationTime"));

        boolean flag = userService.insertUser(user);
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
    @RequestMapping("/GUserInfo")
    public JsonInfo queryUserInfoByUsername(HttpServletRequest req) {
        JsonInfo jsonInfo = new JsonInfo();
        String username = req.getParameter("username");
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

    @RequestMapping(value = "/GRatingGraph")
    public JsonInfo getRatingGraph(HttpServletRequest request, HttpServletResponse response) {
        JsonInfo jsonInfo = new JsonInfo();
        String username = request.getParameter("username");
        Map<String, Integer> list = (Map<String, Integer>) userService.getRatingGraph(username);
        if (null != list) {
            jsonInfo.setSuccess();
            jsonInfo.addInfo(list);
        } else {
            jsonInfo.setFail("未查询到该用户的信息");
        }
        return jsonInfo;
    }

    @RequestMapping(value = "/GAcGraph")
    public JsonInfo getAcGraph(HttpServletRequest request, HttpServletResponse response) {
        JsonInfo jsonInfo = new JsonInfo();
        String username = request.getParameter("username");
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
