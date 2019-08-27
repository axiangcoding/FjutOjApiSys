package com.fjut.oj.service;

import com.fjut.oj.pojo.Problem;
import com.fjut.oj.pojo.Problems1;
import com.fjut.oj.pojo.ProblemSample;
import com.fjut.oj.util.ProblemHTML;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjt
 */
public interface ProblemService {

    List<Problem> queryAllProblems();        //查询所有的题目

    Integer insertProblem(Problem problem); // 插入题目

    List<Problem> queryProblemsByPage(Integer startIndex);   // 一页一页的查询题目信息

    List<Problem> queryProblemsByConditions(Integer startIndex, Integer tagId, String title);

    Integer queryProblemCountByCondition(Integer tagId, String title);

    Integer queryProblemsNumByTitle(String title); // 查询某一题目的题的数量

    Integer updateProblemtotalSubmit(Integer pid);

    Integer updateProblemtotalSubmitUser(Integer pid);

    Integer updateProblemTotalAc(Integer pid);

    Integer updateProblemtotalAcUser(Integer pid);

    List<Problem> queryProblemsFromHDU(Integer from, Integer to); // 查找一个范围内的杭电的题目

    Problem queryProblemById(Integer pid);   // 通过题目 ID 查找题目

    List<Problem> queryProblemByTitle(String title, Integer pid1);  // 通过题目名称查找题目

    Integer queryProblemCount();  // 查找题目的数量

    List<Problems1> getProblems1(int pid1, int pid2, boolean showhide, String owner);

    List<Problem> getProblems2(int from, int num, String search);

    List<Problem> getProblems3(int from, int num, String search);

    Integer getPageNum(int num, boolean showHide);

    Integer editProblem(Integer pid, Problem pro);

    Integer addProblem(Integer pid, Problem pro);

    /**
     * 设置题目类型 ptype = 0 为本地， ptype = 1为第三方
     * @param pid
     * @param type
     * @return
     */
    Integer updateProblemType(Integer pid, Integer type);

    /**
     * 设置题目类型 ptype = 0 为本地， ptype = 1为第三方
     *
     * @param pIds
     * @param type
     * @return
     */
    Integer updateSomeProblemType(ArrayList<Integer> pIds, Integer type);


    Integer setProblemVisiablePid(Integer pid);

    Integer setProblemVisiablePidZ(Integer pid, Integer z);

    List<Integer> getProblemsByOjPid(int oj, String ojspid);

    Integer saveProblemHTML(Integer pid, ProblemHTML ph);

    ProblemSample getProblemHTMLProblemSample(Integer pid);

    Problem queryProblemByOjidAndOjspid(Integer ojid, String ojspid);

    Integer queryMaxProblemId();


}
