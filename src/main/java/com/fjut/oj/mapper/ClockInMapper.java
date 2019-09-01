package com.fjut.oj.mapper;

import com.fjut.oj.pojo.ClockInPO;
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
    Integer insertClockIn(@Param("clockin") ClockInPO clockIn);

    /**
     * 查询全部签到记录
     *
     * @return
     */
    List<ClockInPO> queryAllClockIn();

    /**
     * 通过日期查询所有签到记录
     *
     * @param time
     * @return
     */
    List<ClockInPO> queryAllClockInByDate(@Param("time") Date time);

    /**
     * 通过用户名查询全部签到记录
     *
     * @param username
     * @param pageNum
     * @return
     */
    List<ClockInPO> queryAllClockInByUsername(@Param("username") String username, @Param("pageNum") Integer pageNum);

    /**
     * 通过用户名和日期查询签到记录
     *
     * @param username
     * @param date
     * @return
     */
    List<ClockInPO> queryClockInByUsernameAndDate(@Param("username") String username, @Param("date") Date date);


}
