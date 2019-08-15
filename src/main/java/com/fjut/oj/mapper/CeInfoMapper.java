package com.fjut.oj.mapper;

import com.fjut.oj.pojo.CeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [20190815]
 */
public interface CeInfoMapper {
    /**
     * 插入评测信息
     *
     * @param ce
     * @return
     */
    Integer insertCeInfo(@Param("ce") CeInfo ce);

    /**
     * 查询全部评测信息
     *
     * @return
     */
    List<CeInfo> queryAllCeInfo();

    /**
     * 查询评测信息
     *
     * @param rid
     * @return
     */
    CeInfo queryCeInfo(@Param("rid") Integer rid);


}
