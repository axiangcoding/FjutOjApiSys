package com.fjut.oj.service;

import com.fjut.oj.pojo.TableMessage;


import java.util.List;

/**
 * @author axiang [20190620]
 */
public interface MessageService {
    Integer insertMessage(TableMessage message);

    Integer deleteMessageByMid(int mid);

    Integer deleteAllMessageByUser(String username);

    Integer updateMessageStatuByMid(int mid, int status);

    Integer updateAllMessageReadByUser(String username);

    Integer queryAllMessageCountByUser(String username);

    Integer queryUnReadMessageCountByUser(String username);

    List<TableMessage> queryAllMessageByUser(String username, Integer startIndex);

    List<TableMessage> queryUnReadMessageByUser(String username, int startIndex);


}
