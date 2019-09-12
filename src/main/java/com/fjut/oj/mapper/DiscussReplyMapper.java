package com.fjut.oj.mapper;

import com.fjut.oj.pojo.DiscussReplyPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [20190909]
 */
public interface DiscussReplyMapper {
    /**
     * 查询某条讨论帖子下的10条讨论回帖信息
     * @param discussId
     * @param startIndex
     * @return
     */
    List<DiscussReplyPO> queryDiscussReplyByDiscussId(@Param("discussId") Integer discussId,
                                                      @Param("startIndex") Integer startIndex);

    /**
     * 根据讨论ID查询最后回复记录
     * @param discussId
     * @return
     */
    DiscussReplyPO queryLastReplyByDiscussId(@Param("discussId") Integer discussId);
}
