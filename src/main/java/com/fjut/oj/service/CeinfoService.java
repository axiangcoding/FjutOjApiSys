package com.fjut.oj.service;

import com.fjut.oj.pojo.CeInfo;

import java.util.List;

public interface CeinfoService {

    List<CeInfo> queryAllCeinfo();

    CeInfo queryCeinfo(Integer rid);

    boolean insertCeinfo(CeInfo ce);
}
