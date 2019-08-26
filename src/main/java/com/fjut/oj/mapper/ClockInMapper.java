package com.fjut.oj.mapper;

import com.fjut.oj.pojo.TableClockIn;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author axiang [20190815]
 */
public interface ClockInMapper {
    /**
     * 插入签到记录
     *
     * @param clockIn
     * @return
     */
    Integer insertClockIn(@Param("clockin") TableClockIn clockIn);

    /**
     * 查询全部签到记录
     *
     * @return
     */
    List<TableClockIn> queryAllClockIn();

    /**
     * 通过日期查询所有签到记录
     *
     * @param time
     * @return
     */
    List<TableClockIn> queryAllClockInByDate(@Param("time") Date time);

    /**
     * 通过用户名查询全部签到记录
     *
     * @param username
     * @param pageNum
     * @return
     */
    List<TableClockIn> queryAllClockInByUsername(@Param("username") String username, @Param("pageNum") Integer pageNum);

    /**
     * 通过用户名和日期查询签到记录
     *
     * @param username
     * @param date
     * @return
     */
    List<TableClockIn> queryClockInByUsernameAndDate(@Param("username") String username, @Param("date") Date date);


}
