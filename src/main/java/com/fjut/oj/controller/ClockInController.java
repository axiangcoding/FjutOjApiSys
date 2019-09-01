package com.fjut.oj.controller;

import com.fjut.oj.interceptor.CheckUserIsLogin;
import com.fjut.oj.interceptor.CheckUserPrivate;
import com.fjut.oj.pojo.ClockInPO;
import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.service.ClockInService;
import com.fjut.oj.util.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author axiang [20190704]
 */
@Controller
@CrossOrigin
@RequestMapping("/clockin")
@ResponseBody
public class ClockInController {

    @Autowired
    private ClockInService clockInService;

    @CheckUserIsLogin
    @GetMapping("/getUserClockIn")
    public JsonInfoVO queryAllClockInByUsername(@RequestParam(value = "username") String username,
                                                @RequestParam(value = "pagenum", required = false) String pageNumStr) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer pageNum;
        if (null == pageNumStr) {
            pageNum = null;
        } else {
            pageNum = Integer.parseInt(pageNumStr);
        }
        List<ClockInPO> clockIns = clockInService.queryAllClockInByUsername(username, pageNum);
        if (null != clockIns) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(clockIns);
        } else {
            JsonInfoVO.setFail("未查询到签到记录！");
        }
        return JsonInfoVO;

    }

    @CheckUserIsLogin
    @GetMapping("/getSomedayClockIn")
    public JsonInfoVO queryAllClockInByDate(@RequestParam("date") String dateStr) throws ParseException {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        List<ClockInPO> clockIns = clockInService.queryAllClockInByDate(date);
        if (null != clockIns) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(clockIns);
        } else {
            JsonInfoVO.setFail("未查询到签到记录！");
        }
        return JsonInfoVO;
    }

    @CheckUserPrivate
    @GetMapping("/getUserTodayClockIn")
    public JsonInfoVO queryClockInByUserAndDate(@RequestParam("username") String username) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Date date = new Date();
        List<ClockInPO> clockIns = clockInService.queryClockInByUsernameAndDate(username, date);
        if (0 != clockIns.size()) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(clockIns);
        } else {
            JsonInfoVO.setFail("未查询到签到记录！");
        }
        return JsonInfoVO;
    }

    @CheckUserPrivate
    @PostMapping("/setUserClockIn")
    public JsonInfoVO setClockInForNormalUser(HttpServletRequest req, @RequestParam("username") String username) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Date time = new Date();
        String sign = "日常";
        String ip = IpUtils.getClientIpAddress(req);
        Integer todayTimes = 1;
        ClockInPO clockIn = new ClockInPO();
        clockIn.setUsername(username);
        clockIn.setTime(time);
        clockIn.setSign(sign);
        clockIn.setIp(ip);
        clockIn.setTodytimes(todayTimes);
        boolean isClockIn = clockInService.insertClockIn(clockIn);
        if (isClockIn) {
            JsonInfoVO.setSuccess("用户签到成功！");
        } else {
            JsonInfoVO.setFail("用户签到失败！");
        }
        return JsonInfoVO;
    }


}
