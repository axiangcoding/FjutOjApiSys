package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.NewDiscussMapper;
import com.fjut.oj.pojo.NewDiscuss;
import com.fjut.oj.service.NewDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("newDiscussService")
public class NewDiscussServiceImpl implements NewDiscussService {

    @Autowired
    private NewDiscussMapper newDiscussMapper;

    @Override
    public List<NewDiscuss> queryDiscussByPage(Integer start) {
        return newDiscussMapper.queryDiscussByPage(start);
    }

    @Override
    public Integer queryDiscussCount(){
        return newDiscussMapper.queryDiscussCount();
    }

    @Override
    public Integer insertDiscuss(NewDiscuss newDiscuss) {
        return newDiscussMapper.insertDiscuss(newDiscuss);
    }

    @Override
    public Integer queryMaxCountDiscussId(){
        return newDiscussMapper.queryMaxCountDiscussId();
    }

    @Override
    public Integer queryDiscussCountById(Integer id){
        return newDiscussMapper.queryDiscussCountById(id);
    }

    @Override
    public Integer updateDisscussPirority(Integer id, Double priority){
        return newDiscussMapper.updateDisscussPirority(id,priority);
    }
}
