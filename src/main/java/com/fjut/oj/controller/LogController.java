package com.fjut.oj.controller;

import com.fjut.oj.pojo.Log;
import com.fjut.oj.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

/**
 * TODO: 设置定时任务定期清理过时log
 *
 * @author axiang [20190621]
 *
 */
@Controller
public class LogController {
    @Autowired
    LogService logService;

    public Integer InsertLog(Log log) {
        Integer res = logService.insertLog(log);
        return res;
    }

    public List<Log> queryLogByTime(Date dateStart, Date dateEnd, int startIndex) {
        List<Log> logs = logService.queryLogsByTime(dateStart, dateEnd, startIndex);
        return logs;
    }

}
