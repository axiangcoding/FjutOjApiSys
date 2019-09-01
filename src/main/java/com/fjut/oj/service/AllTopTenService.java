package com.fjut.oj.service;

import com.fjut.oj.pojo.UserPO;

import java.util.List;

/**
 * @author axiang [20190810]
 */
public interface AllTopTenService {
    /**
     * 获得积分榜前十
     *
     * @return
     */
    List<UserPO> getRatingTop();

    /**
     * 获得ACB榜前十
     * @return
     */
    List<UserPO> getAcbTOP();

    /**
     * 获得AC榜前十
     * @return
     */
    List<UserPO> getAcTOP();

    /**
     * 获得活跃榜前十
     * @return
     */
    List<UserPO> getActiveTop();
}
