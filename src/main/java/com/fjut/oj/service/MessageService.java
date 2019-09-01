package com.fjut.oj.service;

import com.fjut.oj.pojo.MessagePO;


import java.util.List;

/**
 * @author axiang [20190620]
 */
public interface MessageService {
    Integer insertMessage(MessagePO message);

    Integer deleteMessageByMid(int mid);

    Integer deleteAllMessageByUser(String username);

    Integer updateMessageStatuByMid(int mid, int status);

    Integer updateAllMessageReadByUser(String username);

    Integer queryAllMessageCountByUser(String username);

    Integer queryUnReadMessageCountByUser(String username);

    List<MessagePO> queryAllMessageByUser(String username, Integer startIndex);

    List<MessagePO> queryUnReadMessageByUser(String username, int startIndex);


}
