package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.BugReportMapper;
import com.fjut.oj.pojo.TableBugReport;
import com.fjut.oj.service.BugReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author axiang [20190825]
 */
@Service("BugReportService")
public class BugReportServiceImpl implements BugReportService {
   @Autowired
    BugReportMapper bugReportMapper;

    @Override
    public Integer insertBugReport(TableBugReport tableBugReport) {
        return bugReportMapper.insertBugReport(tableBugReport);
    }

    @Override
    public List<TableBugReport> queryBugReportByCondition(Integer startIndex) {
        return queryBugReportByCondition(startIndex);
    }
}
