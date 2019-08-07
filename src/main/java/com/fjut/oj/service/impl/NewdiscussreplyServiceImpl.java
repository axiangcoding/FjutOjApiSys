package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.NewdiscussreplyMapper;
import com.fjut.oj.pojo.NewDiscussReply;
import com.fjut.oj.service.NewdiscussreplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("newdiscussreplyService")
public class NewdiscussreplyServiceImpl implements NewdiscussreplyService {

    @Autowired
    private NewdiscussreplyMapper newdiscussreplyMapper;


    @Override
    public List<NewDiscussReply> queryDiscussReplyById(Integer start, Integer id) {
        return newdiscussreplyMapper.queryDiscussReplyById(start, id);
    }

    @Override
    public Integer insertDiscussReply(NewDiscussReply newdiscussreply) {
        return newdiscussreplyMapper.insertDiscussReply(newdiscussreply);
    }

    @Override
    public Integer quertCountReplies(Integer discussid) {
        return newdiscussreplyMapper.quertCountReplies(discussid);
    }
}
