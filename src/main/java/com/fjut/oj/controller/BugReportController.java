package com.fjut.oj.controller;

import com.fjut.oj.pojo.BugReportPO;
import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.service.BugReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author axiang [20190826]
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/bug")
public class BugReportController {
    @Autowired
    BugReportService bugReportService;

    @PostMapping("/bugReport")
    public JsonInfoVO insertBugReport(@RequestParam("username") String username,
                                      @RequestParam("type") String typeStr,
                                      @RequestParam("title") String title,
                                      @RequestParam("text") String text) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer type = Integer.parseInt(typeStr);
        BugReportPO bugReport = new BugReportPO();
        bugReport.setUsername(username);
        bugReport.setType(type);
        bugReport.setTitle(title);
        bugReport.setText(text);
        bugReport.setIsFixed(0);
        bugReport.setTime(new Date());
        Integer ans = bugReportService.insertBugReport(bugReport);
        if (1 == ans) {
            JsonInfoVO.setSuccess();

        } else {
            JsonInfoVO.setFail("BUG反馈失败！");
        }

        return JsonInfoVO;
    }
}
