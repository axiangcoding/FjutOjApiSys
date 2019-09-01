package com.fjut.oj.controller;

import com.fjut.oj.interceptor.CheckUserIsLogin;
import com.fjut.oj.interceptor.CheckUserPrivate;
import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.pojo.UserAuthPO;
import com.fjut.oj.pojo.UserPO;
import com.fjut.oj.service.StatusService;
import com.fjut.oj.service.UserRadarService;
import com.fjut.oj.service.UserService;
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
    public JsonInfoVO insertUser(HttpServletRequest req,
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
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        UserPO tmp = userService.getUserByUsername(req.getParameter("username"));
        if (null != tmp) {
            JsonInfoVO.setFail("用户名已经存在");
            return JsonInfoVO;
        }
        Date currentTime = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);

        UserPO user = new UserPO();
        UserAuthPO userAuth = new UserAuthPO();
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
            JsonInfoVO.setSuccess("添加用户成功！");
        } else {
            JsonInfoVO.setFail("添加用户失败！");
        }
        return JsonInfoVO;
    }

    /**
     * 修改用户信息
     *
     * @param req
     * @return
     */
    @CheckUserPrivate
    @PostMapping("/updateUser")
    public JsonInfoVO updateUser(HttpServletRequest req) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        UserPO tmp = userService.getUserByUsername(req.getParameter("username"));
        if (tmp == null) {
            JsonInfoVO.setFail("用户名不存在！");
            return JsonInfoVO;
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
            JsonInfoVO.setSuccess("修改用户信息成功！");

        } else {
            JsonInfoVO.setFail("修改用户信息失败！");
        }
        return JsonInfoVO;
    }

    /**
     * 获取用户的雷达图
     */
    @CheckUserIsLogin
    @GetMapping("/getUserRadar")
    public JsonInfoVO getUserRadar(@RequestParam("username") String username) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        String userRadar;
        userRadar = userRadarService.getUserRadar(username);
        JsonInfoVO.addInfo(userRadar);
        return JsonInfoVO;
    }

    /**
     * 获取一个用户提交所有题目的次数
     */
    @CheckUserIsLogin
    @GetMapping("/GSubmitCount")
    public JsonInfoVO querySubmitCountByUsername(@RequestParam("username") String username) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer num = statusService.querySubmitCountByUsername(username);
        if (null != num) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(num);
        } else {
            JsonInfoVO.setFail("未查询到该用户的提交信息！");
        }
        return JsonInfoVO;
    }

    /**
     * 获取一个用户个人信息界面的信息
     */
    @CheckUserIsLogin
    @RequestMapping("/getUserInfo")
    public JsonInfoVO queryUserInfoByUsername(@RequestParam("username") String username) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        UserPO user = userService.getUserByUsername(username);

        if (null != user) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(user);
        } else {
            JsonInfoVO.setFail("未查询到该用户的信息！");
        }
        return JsonInfoVO;
    }

    /**
     * 获取一个用户贴过题目标签的数量
     */
    @RequestMapping("/GPutTagNum")
    public JsonInfoVO queryPutTagNumByUsername(HttpServletRequest req, HttpServletResponse resp) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        String username = req.getParameter("username");
        Integer num = userService.queryPutTagNumByUsername(username);
        if (num != 0) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(num);
        } else {
            JsonInfoVO.setFail("未找到用户贴标签的信息");
        }
        return JsonInfoVO;
    }

    /**
     * 获取一个用户已经 AC 和未解决 题目的数量
     */
    @RequestMapping("/GStatusProblems")
    public JsonInfoVO queryStatusProblemsByUsername(HttpServletRequest req) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer status = Integer.parseInt(req.getParameter("status") == null ? "0" : req.getParameter("status"));
        String username = req.getParameter("username");
        List<Integer> list = userService.queryStatusProblemsByUsername(status, username);
        if (list != null) {
            JsonInfoVO.addInfo(list);
            JsonInfoVO.setSuccess();
        } else {
            JsonInfoVO.setFail("未查询到用户相关的题目信息");
        }
        return JsonInfoVO;
    }

    /**
     * 查询一个用户待贴标签的题目
     */
    @RequestMapping("/GNotPutTagProblems")
    public JsonInfoVO queryCanViewCodeProblemsByUsername(HttpServletRequest req, HttpServletResponse resp) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        String username = req.getParameter("username");
        List<Integer> list = userService.queryNotPutTagProblemsByUsername(username);
        JsonInfoVO.addInfo(list);
        JsonInfoVO.setSuccess();
        return JsonInfoVO;
    }


    @RequestMapping(value = "/awardinfo", method = RequestMethod.POST)
    public JsonInfoVO getAwardinfo(HttpServletResponse response, HttpServletRequest request) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        String username = request.getParameter("username");
        List<String> list = userService.queryAwardInfo(username);
        JsonInfoVO.setSuccess();
        JsonInfoVO.addInfo(list);
        return JsonInfoVO;
    }

    @RequestMapping("/getRatingGraph")
    public JsonInfoVO getRatingGraph(@RequestParam("username") String username) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Map<String, Integer> list = (Map<String, Integer>) userService.getRatingGraph(username);
        if (null != list) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(list);
        } else {
            JsonInfoVO.setFail("未查询到该用户的信息");
        }
        return JsonInfoVO;
    }

    @RequestMapping("/getAcGraph")
    public JsonInfoVO getAcGraph(@RequestParam("username") String username) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        //FIXME: 函数错误
        List<Object> list = (List<Object>) userService.getAcGraph(username);
        if (null != list) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(list);
        } else {
            JsonInfoVO.setFail("未查询到该用户的信息");
        }
        return JsonInfoVO;
    }
}
