package com.fjut.oj.controller;

import com.fjut.oj.pojo.TableBugReport;
import com.fjut.oj.pojo.enums.BugType;
import com.fjut.oj.service.BugReportService;
import com.fjut.oj.util.JsonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author: axiang [20190826]
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/bug")
public class BugReportController {
    @Autowired
    BugReportService bugReportService;

    @PostMapping("/bugReport")
    public JsonInfo insertBugReport(@RequestParam("username") String username,
                                    @RequestParam("type") String typeStr,
                                    @RequestParam("title") String title,
                                    @RequestParam("text") String text) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer type = Integer.parseInt(typeStr);
        TableBugReport bugReport = new TableBugReport();
        bugReport.setUsername(username);
        bugReport.setType(type);
        bugReport.setTitle(title);
        bugReport.setText(text);
        bugReport.setIsFixed(0);
        bugReport.setTime(new Date());
        Integer ans = bugReportService.insertBugReport(bugReport);
        if (1 == ans) {
            jsonInfo.setSuccess();

        } else {
            jsonInfo.setFail("BUG反馈失败！");
        }

        return jsonInfo;
    }
}
