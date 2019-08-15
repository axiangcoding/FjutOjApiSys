package com.fjut.oj.mapper;

import com.fjut.oj.pojo.Ceinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [20190815]
 */
public interface CeInfoMapper {

    /**
     * 查询全部评测信息
     *
     * @return
     */
    List<Ceinfo> queryAllCeInfo();

    /**
     * 根据 rid 查询 评测信息
     *
     * @param rid
     * @return
     */
    Ceinfo queryCeInfo(@Param("rid") Integer rid);

    /**
     * 插入评测信息
     *
     * @param ce
     * @return
     */
    Integer insertCeInfo(@Param("ce") Ceinfo ce);
}
