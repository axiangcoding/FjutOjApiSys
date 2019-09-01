package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.LogMapper;
import com.fjut.oj.pojo.LogPO;
import com.fjut.oj.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: axiang [20190621]
 */
@Service("LogService")
public class LogServiceImpl implements LogService {
    @Autowired
    LogMapper logMapper;
    
    @Override
    public Integer insertLog(LogPO log) {
        return logMapper.insertLog(log);
    }

    @Override
    public List<LogPO> queryLogsByTime(Date DateStart, Date DateEnd, int startIndex) {
        return logMapper.queryLogsByTime(DateStart, DateEnd, startIndex);
    }
}
