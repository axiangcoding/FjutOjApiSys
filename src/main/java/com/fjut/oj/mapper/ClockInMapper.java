package com.fjut.oj.mapper;

import com.fjut.oj.pojo.t_clock_in;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author axiang [20190815]
 */
public interface ClockInMapper {

    /**
     * 通过日期查询所有签到记录
     *
     * @param time
     * @return
     */
    List<t_clock_in> queryAllClockInByDate(@Param("time") Date time);

    /**
     * 查询全部签到记录
     *
     * @return
     */
    List<t_clock_in> queryAllClockIn();

    /**
     * 通过用户名查询全部签到记录
     *
     * @param username
     * @param pageNum
     * @return
     */
    List<t_clock_in> queryAllClockInByUsername(@Param("username") String username, @Param("pageNum") Integer pageNum);

    /**
     * 通过用户名和日期查询签到记录
     *
     * @param username
     * @param date
     * @return
     */
    List<t_clock_in> queryClockInByUsernameAndDate(@Param("username") String username, @Param("date") Date date);

    /**
     * 插入签到记录
     *
     * @param clockIn
     * @return
     */
    Integer insertClockIn(@Param("clockin") t_clock_in clockIn);

}
