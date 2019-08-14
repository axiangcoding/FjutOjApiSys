package com.fjut.oj.service;

import com.fjut.oj.pojo.Ceinfo;

import java.util.List;

public interface CeinfoService {

    List<Ceinfo> queryAllCeinfo();

    Ceinfo queryCeinfo(Integer rid);

    boolean insertCeinfo(Ceinfo ce);
}
