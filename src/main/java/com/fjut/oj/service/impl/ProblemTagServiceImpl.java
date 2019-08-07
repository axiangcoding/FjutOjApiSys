package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.ProblemTagMapper;
import com.fjut.oj.mapper.UserMapper;
import com.fjut.oj.pojo.*;
import com.fjut.oj.service.ProblemTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProblemTagService")
public class ProblemTagServiceImpl implements ProblemTagService {

    @Autowired
    private ProblemTagMapper problemTagMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<t_problem_tag> queryAllProblemTag() {
        List<t_problem_tag> list = problemTagMapper.queryAllProblemTag();
        return list;
    }

    @Override
    public List<t_problem_tag_record> problemTagRecord(Integer pid, String username) {
        List<t_problem_tag_record> list = problemTagMapper.problemTagRecord(pid, username);
        return list;
    }

    @Override
    public List<t_problem_tag_record> problemTagRecordLimitNum(Integer pid, Integer from, Integer num) {
        List<t_problem_tag_record> list = problemTagMapper.problemTagRecordLimitNum(pid, from, num);
        return list;
    }

    // 贴标签奖励 ACB
    @Override
    public int addTag(Integer pid, String username, Integer tagid, Integer rating) {
        User user = userMapper.queryUserByName(username);
        if (user == null)
            return 0;

        if (user.getRating() == -100000)
            rating = 700;
        int num = problemTagMapper.addTag(pid, username, tagid, rating);
        /**
         * 判断是否贴过标签 奖励 ACB
         */
        return num;
    }

    @Override
    public int delTag(Integer pid, String username, Integer tagid) {
        int num = problemTagMapper.delTag(pid, username, tagid);
        return num;
    }

    @Override
    public int addProblemTag(String tagName) {
        int num = problemTagMapper.addProblemTag(tagName);
        return num;
    }

    @Override
    public int renameProblemTag(Integer id, Integer name) {
        int num = problemTagMapper.renameProblemTag(name, id);
        return num;
    }

    @Override
    public String queryUserTag(String username) {
        List<UserTag1> list1 = problemTagMapper.queryUserTag1(username);
        List<UserTag2> list2 = problemTagMapper.queryUserTag2();
        int[] a = new int[7];
        int[] b = new int[7];

        try {
            for (UserTag1 userTag1 : list1) {
                int x = userTag1.getTtype();
                if (x >= 0 && x <= 6) {
                    a[x] = userTag1.getNum();
                }
            }
            for (UserTag2 userTag2 : list2) {
                int x = userTag2.getTtype();
                if (x >= 0 && x <= 6) {
                    b[x] = userTag2.getNum();
                }
            }
        } catch (Exception e) {

        }
        String ret = "[";
        for (int i = 0; i <= 6; ++i) {
            if (i != 0) ret += ",";
            ret += (b[i] != 0 ? a[i] * 100.0 / b[i] : "0");
        }
        return ret + "]";
    }
}

