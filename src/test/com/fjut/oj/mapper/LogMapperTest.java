package com.fjut.oj.mapper;

import com.fjut.oj.pojo.LogPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class LogMapperTest {
    @Autowired
    LogMapper logMapper;


    @Test
    public void insertLog() {
        LogPO log =new LogPO();
        log.setText("this is exception");
        log.setTime(new Date());
        log.setIpAddress("admin");
        logMapper.insertLog(log);
    }

    @Test
    public void queryLogsByTime() {
    }
}