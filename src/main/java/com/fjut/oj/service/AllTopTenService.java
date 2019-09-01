package com.fjut.oj.service;

import com.fjut.oj.pojo.User;

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
    List<User> getRatingTop();

    List<User> getAcbTOP();

    List<User> getAcTOP();

    List<User> getActiveTop();
}
