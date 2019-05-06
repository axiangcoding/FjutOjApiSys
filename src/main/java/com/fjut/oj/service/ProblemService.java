package com.fjut.oj.service;

import com.fjut.oj.pojo.Problem;
import com.fjut.oj.pojo.Problems1;
import com.fjut.oj.util.problemHTML;

import java.util.List;

public interface ProblemService {

    public List<Problem> queryAllProblems();        //查询所有的题目

    public List<Problems1> queryProblemsByPage(Integer pid1, Integer pid2);   // 一页一页的查询题目信息

    public List<Problem> queryProblemsFromHDU(Integer from, Integer to); // 查找一个范围内的杭电的题目

    public Problem queryProblemById(Integer pid);   // 通过题目 ID 查找题目

    public List<Problem> queryProblemByTitle(String title);  // 通过题目名称查找题目

    public Integer queryProblemsNum(String search);  // 查找题目的数量

    public List<Problems1> getProblems1(int pid1, int pid2, boolean showhide, String owner);

    public List<Problem> getProblems2(int from, int num, String search);

    public List<Problem> getProblems3(int from, int num, String search);

    public Integer getPageNum(int num, boolean showHide);

    public Integer editProblem(Integer pid, Problem pro);

    public Integer addProblem(Integer pid, Problem pro);

    public Integer setProblemVisiablePid(Integer pid);

    public Integer setProblemVisiablePidZ(Integer pid, Integer z);

    public List<Integer> getProblemsByOjPid(int oj, String ojspid);

    public Integer saveProblemHTML(Integer pid, problemHTML ph);
}
