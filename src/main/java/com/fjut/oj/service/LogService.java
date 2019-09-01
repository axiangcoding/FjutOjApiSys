package com.fjut.oj.service;

import com.fjut.oj.pojo.LogPO;

import java.util.Date;
import java.util.List;

/**
 * @author axiang [20190621]
 */
public interface LogService {
    /**
     * 插入日志
     *
     * @param log
     * @return
     */
    Integer insertLog(LogPO log);

    /**
     * 根据时间段查询日志
     *
     * @param DateStart
     * @param DateEnd
     * @param startIndex
     * @return
     */
    List<LogPO> queryLogsByTime(Date DateStart, Date DateEnd, int startIndex);

}
