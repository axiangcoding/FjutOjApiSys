package com.fjut.oj.mapper;

import com.fjut.oj.pojo.NewDiscussReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cjt
 */
public interface NewDiscussReplyMapper {
    
    List<NewDiscussReply> queryDiscussReplyById(@Param("start") Integer start, @Param("id") Integer id);
    
    Integer insertDiscussReply(@Param("newdiscussreply") NewDiscussReply newdiscussreply);

    Integer quertCountReplies(@Param("discussid") Integer discussid);
}
