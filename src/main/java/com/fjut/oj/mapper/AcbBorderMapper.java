package com.fjut.oj.mapper;

import com.fjut.oj.pojo.AcbBorder;
import org.apache.ibatis.annotations.Param;

/**
 * @author axiang [20190807]
 */
public interface AcbBorderMapper {
    /**
     * 插入ACB流水单
     *
     * @param acbBorder
     * @return
     */
    Integer insertAcbBorder(@Param("acbBorder") AcbBorder acbBorder);

}
