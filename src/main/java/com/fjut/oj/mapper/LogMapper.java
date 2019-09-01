package com.fjut.oj.mapper;

import java.util.Date;
import java.util.List;

import com.fjut.oj.pojo.LogPO;
import org.apache.ibatis.annotations.Param;

/**
 * @author axiang [20190620]
 */
public interface LogMapper {
    /**
     * 插入日志
     *
     * @param log
     * @return
     */
    Integer insertLog(@Param("log") LogPO log);

    /**
     * 根据一个时间段查询日志
     *
     * @param DateStart
     * @param DateEnd
     * @param startIndex
     * @return
     */
    List<LogPO> queryLogsByTime(@Param("timeStart") Date DateStart, @Param("timeEnd") Date DateEnd, @Param("startIndex") int startIndex);

}