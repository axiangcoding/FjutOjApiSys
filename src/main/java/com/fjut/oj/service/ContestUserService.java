package com.fjut.oj.service;

import com.fjut.oj.pojo.Contestuser;

import java.util.List;

public interface ContestUserService {

    List<Contestuser> getContestUserById(Integer cid);

    Integer insertContestUser(Contestuser contestuser);
}
