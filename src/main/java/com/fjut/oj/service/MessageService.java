package com.fjut.oj.service;

import com.fjut.oj.pojo.MessagePO;

import java.util.List;

/**
 * @author axiang [20190620]
 */
public interface MessageService {
    /**
     * 插入一条消息
     *
     * @param message
     * @return
     */
    Integer insertMessage(MessagePO message);

    Integer deleteMessageByMid(int mid);

    Integer deleteAllMessageByUser(String username);

    Integer updateMessageStatuByMid(int mid, int status);

    /**
     * FIXME: 疑似失效
     * 设置全部消息已读
     *
     * @param username
     * @return
     */
    Integer updateAllMessageReadByUser(String username);

    Integer queryAllMessageCountByUser(String username);

    Integer queryUnReadMessageCountByUser(String username);

    List<MessagePO> queryAllMessageByUser(String username, Integer startIndex);

    List<MessagePO> queryUnReadMessageByUser(String username, int startIndex);


}
