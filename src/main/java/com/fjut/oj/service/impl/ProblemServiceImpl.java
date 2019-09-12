package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.ProblemMapper;
import com.fjut.oj.mapper.ProblemTagRecordMapper;
import com.fjut.oj.pojo.*;
import com.fjut.oj.service.ProblemService;
import com.fjut.oj.util.ProblemHTML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cjt
 */
@Service("problemService")
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private ProblemTagRecordMapper problemTagRecordMapper;

    @Override
    public Integer insertProblem(Problem problem) {
        Integer num = problemMapper.insertProblem(problem);
        return num;
    }

    @Override
    public List<Problem> queryProblemsByPage(Integer startIndex) {
        List<Problem> list = problemMapper.queryProblemsByPage(startIndex);
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        for (Problem p : list) {
            if (p.getTotalAcUser() == 0) {
                p.radio = 0.0;
                p.strRadio = "0%";
            } else {
                p.radio = (p.getTotalAcUser() * 1.0) / p.getTotalSubmit() * 100;
                p.strRadio = numberFormat.format(p.radio) + "%";
            }
        }
        return list;
    }

    @Override
    public List<Problem> queryProblemsByConditions(Integer startIndex, Integer tagId, String title) {
        List<Problem> list = problemMapper.queryProblemsByConditions(startIndex, tagId, title);
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        for (Problem p : list) {
            if (p.getTotalAcUser() == 0) {
                p.radio = 0.0;
                p.strRadio = "0%";
            } else {
                p.radio = (p.getTotalAcUser() * 1.0) / p.getTotalSubmit() * 100;
                p.strRadio = numberFormat.format(p.radio) + "%";
            }
        }
        return list;
    }

    @Override
    public Integer queryProblemCountByCondition(Integer tagId, String title) {
        return problemMapper.queryProblemCountByCondition(tagId, title);
    }

    @Override
    public Integer queryProblemsNumByTitle(String title) {
        return problemMapper.queryProblemsNumByTitle(title);
    }


    @Override
    public List<Problem> queryProblemByTagId(Integer tagId, String username) {
        // 拿到该标签对应的题目
        List<ProblemTagRecordPO> tagProblems = problemTagRecordMapper.queryProblemTagRecordByTagId(tagId, username);
        // 转换为 problem 类
        List<Problem> problems = new ArrayList<>();
        int rand1, rand2, rand3;
        int size = tagProblems.size();
        // FIXME: 随机选取三个题目作为推荐，这里以后需要引入算法
        rand1 = (int)(Math.random()*size);
        rand2 = (int)(Math.random()*size);
        rand3 = (int)(Math.random()*size);
        for (int i = 0; i < tagProblems.size(); i++) {
            if (i == rand1 || i == rand2 || i == rand3) {
              Problem problem =  problemMapper.queryProblemById(tagProblems.get(i).getPid());
              problems.add(problem);
            }
        }
        return problems;
    }

    @Override
    public Integer updateProblemtotalSubmit(Integer pid) {
        return problemMapper.updateProblemTotalSubmit(pid);
    }

    @Override
    public Integer updateProblemtotalSubmitUser(Integer pid) {
        return problemMapper.updateProblemTotalSubmitUser(pid);
    }

    @Override
    public Integer updateProblemTotalAc(Integer pid) {
        return problemMapper.updateProblemTotalAc(pid);
    }

    @Override
    public Integer updateProblemtotalAcUser(Integer pid) {
        return problemMapper.updateProblemTotalAcUser(pid);
    }

    @Override
    public ProblemSample getProblemHTMLProblemSample(Integer pid) {
        return problemMapper.getProblemHTMLProblemSample(pid);
    }

    @Override
    public List<Problem> queryProblemsFromHDU(Integer from, Integer to) {
        List<Problem> list = problemMapper.queryProblemsFromHDU(from, to);
        return list;
    }

    @Override
    public Problem queryProblemById(Integer pid) {
        Problem problem = problemMapper.queryProblemById(pid);
        if (problem == null) {
            return null;
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        if (problem.getTotalAcUser() == 0) {
            problem.radio = 0.0;
            problem.strRadio = "0%";
            return problem;
        }
        problem.radio = (problem.getTotalAcUser() * 1.0) / problem.getTotalSubmit() * 100;
        problem.strRadio = numberFormat.format(problem.radio) + "%";
        return problem;
    }

    @Override
    public List<Problem> queryProblemByTitle(String title, Integer pid1) {
        List<Problem> list = problemMapper.queryProblemByTitle(title, pid1);
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        for (Problem p : list) {
            if (p.getTotalAcUser() == 0) {
                p.radio = 0.0;
                p.strRadio = "0%";
            } else {
                p.radio = (p.getTotalAcUser() * 1.0) / p.getTotalSubmit() * 100;
                p.strRadio = numberFormat.format(p.radio) + "%";
            }
        }
        return list;
    }

    @Override
    public Integer queryProblemCount() {
        Integer num = problemMapper.queryProblemCount();
        return num;
    }

    @Override
    public List<Problems1> getProblems1(int pid1, int pid2, boolean showhide, String owner) {
        List<Problems1> list = problemMapper.getProblems1(pid1, pid2, showhide, owner);
        return list;
    }

    @Override
    public List<Problem> getProblems2(int from, int num, String search) {
        List<Problem> list = problemMapper.getProblems2(from, num, search);
        return list;
    }

    @Override
    public List<Problem> getProblems3(int from, int num, String search) {
        List<Problem> list = problemMapper.getProblems2(from, num, search);
        return list;
    }

    @Override
    public Integer getPageNum(int num, boolean showHide) {
        Integer maxpid = problemMapper.getMaxProblemId(num, showHide);
        if (maxpid == 0) {
            return 1;
        }
        return (maxpid - 1000) / num + 1;
    }

    @Override
    public Integer editProblem(Integer pid, Problem pro) {
        Integer num = problemMapper.editProblem(pid, pro);
        return num;
    }

    @Override
    public Integer addProblem(Integer pid, Problem pro) {
        int newpid;
        if (pid == -1) {
            newpid = problemMapper.getMaxProblemIdAddOne();
            newpid = newpid == 0 ? 1000 : newpid;
        } else {
            editProblem(pid, pro);
            return 0;
        }
        int num = problemMapper.addProblem(pid, pro);
        return 1;
    }

    @Override
    public Integer updateProblemType(Integer pid, Integer type) {
        return problemMapper.updateProblemType(pid, type);
    }

    @Override
    public Integer updateSomeProblemType(ArrayList<Integer> pIds, Integer type) {
        return problemMapper.updateSomeProblemType(pIds, type);
    }


    @Override
    public Integer setProblemVisiablePid(Integer pid) {
        Integer num = 0;
        if (pid != -1) {
            num = problemMapper.setProblemVisiablePid(pid);
        }
        return num;
    }

    @Override
    public Integer setProblemVisiablePidZ(Integer pid, Integer z) {
        Integer num = 0;
        if (pid != -1) {
            num = problemMapper.setProblemVisiablePidZ(pid, z);
        }
        return num;
    }

    @Override
    public List<Integer> getProblemsByOjPid(int oj, String ojspid) {
        List<Integer> list = problemMapper.getProblemsByOjPid(oj, ojspid);
        return list;
    }

    @Override
    public Integer saveProblemHTML(Integer pid, ProblemHTML ph) {
        if (ph == null) {
            return 0;
        }
        Integer num1 = problemMapper.saveProblemHTMLProblemView(pid, ph);
        List<String> in = ph.getSampleInput();
        List<String> out = ph.getSampleOutput();
        for (int i = 0; i < in.size(); i++) {
            Integer num2 = problemMapper.saveProblemHTMLProblemSample(pid, ph);
        }
        return 1;
    }

    public String getP(int pid, String edit, int num) {
        ProblemHTML p = getProblemHTML(pid);
        if (edit.equals("dis")) {
            return p.getDis();
        }
        if (edit.equals("input")) {
            return p.getInput();
        }
        if (edit.equals("output")) {
            return p.getOutput();
        }
        if (edit.equals("sampleinput")) {
            List<String> s = p.getSampleInput();
            if (num < s.size()) {
                return s.get(num);
            } else {
                return "";
            }
        }
        if (edit.equals("sampleoutput")) {
            List<String> s = p.getSampleOutput();
            if (num < s.size()) {
                return s.get(num);
            } else {
                return "";
            }
        }
        return "";
    }

    public boolean delProblemDis(Integer pid) {
        problemMapper.delProblemDisProblemView(pid);
        problemMapper.delProblemDisProblemSample(pid);
        return true;
    }

    public ProblemHTML getProblemHTML(int pid) {
        Problem p = problemMapper.queryProblemById(pid);
        ProblemHTML ph = new ProblemHTML((pid));
        Problem problem = problemMapper.queryProblemById(pid);
        List<t_problemview> problemview = problemMapper.getProblemHTMLProblemView(pid);
        try {
            for (t_problemview pv : problemview) {
                ph.setTitle(problem.getTitle());
                ph.setTimeLimit(pv.getTimelimit());
                ph.setMenoryLimit(pv.getMenoryLimit());
                ph.setInt64(pv.getInt64());
                if (p.isLocal() || p.getOjid() == 7) {
                    ph.setSpj(p.isSpj() ? 1 : 0);
                } else {
                    ph.setSpj(pv.getSpj());
                }
                ph.setDis(pv.getDis());
                ph.setInput(pv.getInput());
                ph.setOutput(pv.getOutput());
                ProblemSample problemsample = problemMapper.getProblemHTMLProblemSample(pid);
                ph.addSample(problemsample.getInput(), problemsample.getOutput());

                return ph;
            }
        } catch (Exception e) {
            return null;
        }

        return ph;
    }

    @Override
    public Problem queryProblemByOjidAndOjspid(Integer ojid, String ojspid) {
        return problemMapper.queryProblemByOjidAndOjspid(ojid, ojspid);
    }

    @Override
    public Integer queryMaxProblemId() {
        return problemMapper.queryMaxProblemId();
    }
}
