package com.fjut.oj.mapper;

import com.fjut.oj.pojo.TableBugReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [20190825]
 */
public interface BugReportMapper {
    /**
     * 插入一条bug报告记录
     *
     * @param tableBugReport
     * @return
     */
    Integer insertBugReport(@Param("tableBugReport") TableBugReport tableBugReport);

    /**
     * FIXME: 条件还没添加上去
     * 查询bug报告记录，一次20条
     *
     * @param startIndex
     * @return
     */
    List<TableBugReport> queryBugReportByCondition(@Param("startIndex") Integer startIndex);
}
