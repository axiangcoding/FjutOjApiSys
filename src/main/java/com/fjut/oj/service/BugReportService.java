package com.fjut.oj.service;

import com.fjut.oj.pojo.BugReportPO;

import java.util.List;

/**
 * @author axiang [20190825]
 */
public interface BugReportService {
    /**
     * 插入一条bug报告记录
     *
     * @param bugReportPO
     * @return
     */
    Integer insertBugReport(BugReportPO bugReportPO);

    /**
     * FIXME: 条件还没添加上去
     * 查询bug报告记录，一次20条
     *
     * @param startIndex
     * @return
     */
    List<BugReportPO> queryBugReportByCondition(Integer startIndex);

}
