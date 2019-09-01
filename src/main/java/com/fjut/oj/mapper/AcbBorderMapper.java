package com.fjut.oj.mapper;

import com.fjut.oj.pojo.AcbBorderPO;
import org.apache.ibatis.annotations.Param;

/**
 * @author axiang [20190807]
 */
public interface AcbBorderMapper {
    /**
     * 插入ACB流水单
     *
     * @param acbBorderDO
     * @return
     */
    Integer insertAcbBorder(@Param("acbBorderDO") AcbBorderPO acbBorderDO);

}
