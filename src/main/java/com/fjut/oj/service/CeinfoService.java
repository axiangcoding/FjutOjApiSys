package com.fjut.oj.service;

import com.fjut.oj.pojo.CeInfoPO;

import java.util.List;

public interface CeinfoService {

    List<CeInfoPO> queryAllCeinfo();

    CeInfoPO queryCeinfo(Integer rid);

    boolean insertCeinfo(CeInfoPO ce);
}
