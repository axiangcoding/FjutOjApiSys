package com.fjut.oj.service;

import com.fjut.oj.pojo.t_message;
import org.springframework.stereotype.Service;


import java.util.List;


public interface MessageService {
    Integer insertMessage(t_message message);

    Integer deleteMessageByMid(int mid);

    Integer deleteAllMessageByUser(String username);

    Integer updateMessageStatuByMid(int mid, int status);

    Integer updateAllMessageReadByUser(String username);

    Integer queryAllMessageCountByUser(String username);

    Integer queryUnReadMessageCountByUser(String username);

    List<t_message> queryAllMessageByUser(String username, Integer startIndex);

    List<t_message> queryUnReadMessageByUser(String username, int startIndex);


}
