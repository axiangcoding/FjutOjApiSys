package com.fjut.oj.service;

import com.fjut.oj.pojo.ProblemTagPO;
import com.fjut.oj.pojo.ProblemTagRecordPO;

import java.util.List;

/**
 * @author axiang [20190911]
 */
public interface ProblemTagService {

    List<ProblemTagPO> queryAllProblemTag();  // 查询所有标签

    List<ProblemTagRecordPO> problemTagRecord(Integer pid, String username);   // 按照题目 id 或者加上用户名查找一个题目的标签记录

    List<ProblemTagRecordPO> problemTagRecordLimitNum(Integer pid, Integer from, Integer num);  // 查找一个范围的题目标签记录

    int addTag(Integer pid, String username, Integer tagid, Integer rating);      // 用户贴标签时新增一条贴标签记录

    int delTag(Integer pid, String username, Integer tagid);                      // 删除一个用户贴的某个标签记录

    int addProblemTag(String tagName);                                           // 新增标签名称

    int renameProblemTag(Integer id, Integer name);                              // 重命名标签名称

    String queryUserTag(String username);                                     // 查询某个用户贴的总标签记录的各种类型数量
}
