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

    List<UserPO> getAcbTOP();

    List<UserPO> getAcTOP();

    List<UserPO> getActiveTop();
}
