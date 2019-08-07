package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.MessageMapper;
import com.fjut.oj.pojo.t_message;
import com.fjut.oj.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wyx
 * @Despriction:
 * @Date:Created in 11:10 2019/6/17
 * @Modify By:
 */
@Service("MessageService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;


    @Override
    public Integer insertMessage(t_message message) {
        return messageMapper.insertMessage(message);
    }

    @Override
    public Integer deleteMessageByMid(int mid) {
        return messageMapper.deleteMessageByMid(mid);
    }

    @Override
    public Integer deleteAllMessageByUser(String username) {
        return messageMapper.deleteAllMessageByUser(username);
    }

    @Override
    public Integer updateMessageStatuByMid(int mid, int status) {
        return messageMapper.updateMessageStatuByMid(mid, status);
    }

    @Override
    public Integer updateAllMessageReadByUser(String username) {
        return messageMapper.updateAllMessageReadByUser(username);
    }

    @Override
    public Integer queryAllMessageCountByUser(String username) {
        return messageMapper.queryMessageCountByUser(username);
    }

    @Override
    public Integer queryUnReadMessageCountByUser(String username) {
        return messageMapper.queryUnReadMessageCountByUser(username);
    }

    @Override
    public List<t_message> queryAllMessageByUser(String username, Integer startIndex) {
        return messageMapper.queryAllMessageByUser(username, startIndex);
    }

    @Override
    public List<t_message> queryUnReadMessageByUser(String username, int startIndex) {
        return messageMapper.queryUnReadMessageByUser(username, startIndex);
    }
}
