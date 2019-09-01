package com.fjut.oj.mapper;

import com.fjut.oj.pojo.MessagePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [20190815]
 */
public interface MessageMapper {
    /**
     * 插入一条消息
     * @param message
     * @return
     */
    Integer insertMessage(@Param("message") MessagePO message);

    /**
     * 删除一条消息
     * @param mid
     * @return
     */
    Integer deleteMessageByMid(@Param("mid")int mid);

    /**
     * 删除某个用户的全部消息
     * @param username
     * @return
     */
    Integer deleteAllMessageByUser(@Param("username") String username);

    /**
     * 更新消息状态
     * @param mid
     * @param status
     * @return
     */
    Integer updateMessageStatuByMid(@Param("mid") int mid, @Param("status") int status);

    /**
     * 更新用户消息为全部已读
     * @param username
     * @return
     */
    Integer updateAllMessageReadByUser(@Param("username") String username);

    /**
     * 查询全部消息数量
     * @param username
     * @return
     */
    Integer queryMessageCountByUser(@Param("username") String username);

    /**
     * 查询未读消息数量
     * @param username
     * @return
     */
    Integer queryUnReadMessageCountByUser(@Param("username") String username);

    /**
     * 查询用户未读消息，返回10条
     * @param username
     * @param startIndex
     * @return
     */
    List<MessagePO> queryUnReadMessageByUser(@Param("username") String username, @Param("startIndex") int startIndex);

    /**
     * 查询用户全部消息，返回10条
     * @param username
     * @param startIndex
     * @return
     */
    List<MessagePO> queryAllMessageByUser(@Param("username") String username, @Param("startIndex") int startIndex);

}
