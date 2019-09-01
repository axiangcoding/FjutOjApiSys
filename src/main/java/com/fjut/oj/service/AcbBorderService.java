package com.fjut.oj.service;

import com.fjut.oj.pojo.AcbBorderPO;

/**
 * @author axiang
 */
public interface AcbBorderService {
    /**
     * 插入ACB流水单
     * @param acbBorderDO
     * @return
     */
    Integer insertAcbBorder(AcbBorderPO acbBorderDO);
}
