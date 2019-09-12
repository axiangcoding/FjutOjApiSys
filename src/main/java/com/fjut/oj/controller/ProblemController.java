package com.fjut.oj.controller;

import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.pojo.Problem;
import com.fjut.oj.pojo.Problems1;
import com.fjut.oj.pojo.ViewUserSolve;
import com.fjut.oj.service.ProblemDifficultService;
import com.fjut.oj.service.ProblemService;
import com.fjut.oj.service.StatusService;
import com.fjut.oj.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 把 JsonMsg 替换为 JsonInfoVOVO
 *
 * @author axiang [20190707]
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private ProblemDifficultService problemDifficultService;

    @Autowired
    private StatusService statusService;


    @Value("${oj.baseJudgeFilePath}")
    private String baseJudgeFilePath;

    /**
     * TODO: 还有条件 是否收藏 未用到
     * 根据多重条件 一次查询50个题目
     *
     * @param pageNumStr
     * @return
     */
    @GetMapping("/getProblems")
    public JsonInfoVO queryProblemsByConditions(@RequestParam(value = "pageNum") String pageNumStr,
                                                @RequestParam(value = "tagId", required = false) String tagIdStr,
                                                @RequestParam(value = "title", required = false) String title,
                                                @RequestParam(value = "username", required = false) String username,
                                                @RequestParam(value = "isStar", required = false) String isStarStr) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer pageNum = Integer.parseInt(pageNumStr);
        Integer tagId = null;
        if (tagIdStr != null && !"".equals(tagIdStr)) {
            tagId = Integer.parseInt(tagIdStr);
        }
        Integer startIndex = (pageNum - 1) * 50;
        List<Problem> problems = problemService.queryProblemsByConditions(startIndex, tagId, title);
        Integer problemCount = problemService.queryProblemCountByCondition(tagId, title);

        JsonInfoVO.addInfo(problems);
        JsonInfoVO.addInfo(problemCount);
        return JsonInfoVO;
    }

    /**
     * 获取用户答题状态
     *
     * @param username
     * @return
     */
    @GetMapping("/getProblemSolve")
    public JsonInfoVO queryProblemsByConditions(@RequestParam(value = "username", required = false) String username) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        List<ViewUserSolve> solves = new ArrayList<>();
        if ("".equals(username) || null == username) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(solves);
            return JsonInfoVO;
        }
        solves = statusService.queryUserSolveProblemByUsername(username);
        JsonInfoVO.addInfo(solves);
        return JsonInfoVO;
    }

    @GetMapping("/getRecommendProblems")
    public JsonInfoVO getRecommendProblems(@RequestParam("username") String username) {
        JsonInfoVO jsonInfoVO = new JsonInfoVO();
        // FIXME: 暂时设置为1
        Integer tagId = 1;
        List<Problem> problems = problemService.queryProblemByTagId(tagId, username);
        jsonInfoVO.addInfo(problems);
        return jsonInfoVO;
    }


    /**
     * 查询一个范围内的杭电的题目
     */
    @GetMapping("/getProblemsFromHDU")
    public JsonMsg queryProblemsFromHDU() {
        Integer from = 50;
        Integer to = 100;
        List<Problem> list = problemService.queryProblemsFromHDU(from, to);
        if (list.size() == 0) {
            return JsonMsg.fail().addInfo("未查找到该范围的题目");
        } else {
            return JsonMsg.success().addInfo(list);
        }
    }

    /**
     * 通过题目 ID 查找题目信息
     */
    @GetMapping("/getProblemById")
    public JsonMsg queryProblemById(@RequestParam("pid") String pidStr) {
        Integer pid = Integer.parseInt(pidStr);
        Problem problem = problemService.queryProblemById(pid);
        if (problem != null) {
            return JsonMsg.success().addInfo(problem);
        }
        return JsonMsg.fail().addInfo("未查找到该题目！");
    }

    /**
     * 通过题目 title 查找题目
     */
    @GetMapping("/getProblemByTitle")
    public JsonInfoVO queryProblemByTitle(@RequestParam("title") String title,
                                          @RequestParam("pagenum") String pageNumStr) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer pageNum = Integer.parseInt(pageNumStr == null ? "0" : pageNumStr);
        Integer totalPageNum, totalProblem;

        totalProblem = problemService.queryProblemsNumByTitle(title);
        if (totalProblem == 0) {
            totalPageNum = 1;
        } else {
            if (totalProblem % 50 == 0) {
                totalPageNum = totalProblem / 50;
            } else {
                totalPageNum = totalProblem / 50 + 1;
            }
        }
        Integer pid1 = (pageNum - 1) * 50;
        List<Problem> list = problemService.queryProblemByTitle(title, pid1);
        JsonInfoVO.setSuccess();
        JsonInfoVO.addInfo(totalPageNum);
        JsonInfoVO.addInfo(list);
        return JsonInfoVO;
    }

    /**
     * 查询题目数量
     */
    @GetMapping("/getProblemsNum")
    public JsonInfoVO queryProblemsNum() {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer num = problemService.queryProblemCount();
        JsonInfoVO.addInfo(num);
        return JsonInfoVO;
    }


    /**
     * 查询一个范围题目号范围内的题目 并可以选择是否查询隐藏题目和拥有者
     */
    @GetMapping("/GProblems1")
    public JsonMsg getProblems1(HttpServletRequest req, HttpServletResponse resp) {
        Integer pid1 = 1500;
        Integer pid2 = 2000;
        boolean showhide = false;
        String owner = "admin";

        List<Problems1> list = problemService.getProblems1(pid1, pid2, showhide, owner);
        return JsonMsg.success().addInfo(list);
    }

    /**
     * 根据题目名称查询一个范围内的题目
     */
    @GetMapping("/GProblems2")
    public JsonMsg getProblems2(HttpServletRequest req, HttpServletResponse resp) {
        Integer from = 1300;
        Integer num = 1500;
        String search = "";
        List<Problem> list;
        if (search == null || search == "") {
            list = problemService.getProblems2(from, num, search);
        } else {
            list = problemService.getProblems3(from, num, search);
        }
        return JsonMsg.success().addInfo(list);
    }


    /**
     * 查询题目页面数量,根据 num 的不同结果会有不同
     */
    @RequestMapping("/GPageNum")
    public JsonMsg getPageNum() {
        Integer num = 20;
        boolean showHide = false;
        Integer total = problemService.getPageNum(num, showHide);
        return JsonMsg.success().addInfo(total);
    }

    /**
     * FIXME: 无法正确得到返回JSON内容
     * 更新所有题目的类型
     *
     * @return
     */
    @PostMapping("/updateAllProblemType")
    public JsonInfoVO updateAllProblemType() {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        File baseDir = new File(baseJudgeFilePath);
        System.out.println(baseJudgeFilePath);
        if (baseDir.isDirectory()) {
            ArrayList<Integer> localIds = new ArrayList<>();
            ArrayList<Integer> notLocalIds = new ArrayList<>();
            File[] problemDir = baseDir.listFiles();
            for (File problemFile : problemDir) {
                String problemId = problemFile.getName();
                File[] problemDetailFile = problemFile.listFiles();
                if (0 != problemDetailFile.length) {
                    localIds.add(Integer.parseInt(problemId));
                } else {
                    notLocalIds.add(Integer.parseInt(problemId));
                }
            }
            Integer localProblemUpdateCount = problemService.updateSomeProblemType(localIds, 0);
            Integer notLocalProblemUpdateCount = problemService.updateSomeProblemType(notLocalIds, 1);
            System.out.println(localProblemUpdateCount + " " + notLocalProblemUpdateCount);
            JsonInfoVO.setSuccess("已修改成功 " + localProblemUpdateCount + " 条题目状态为本地题目， " +
                    notLocalProblemUpdateCount + " 条题目状态为第三方题目");
        } else {
            JsonInfoVO.setFail("找不到题库目录！");
        }
        return JsonInfoVO;
    }


    /**
     * FIXME: 假功能
     * 编辑题目信息
     */
    @RequestMapping("/EProblem")
    public JsonMsg editProblem(HttpServletRequest req, HttpServletResponse resp) {
        Integer pid = 1;
        Problem pro = null;

        Integer num = problemService.editProblem(pid, pro);
        return JsonMsg.success().addInfo(num);
    }

    /**
     * 新增题目
     */
    @RequestMapping("/AProblem")
    public JsonMsg addProblem(HttpServletRequest req, HttpServletResponse resp) {
        Integer pid = 1;
        Problem pro = null;

        Integer num = problemService.addProblem(pid, pro);
        return JsonMsg.success().addInfo(num);
    }

    /**
     * 更改一个题目的可见状态
     */
    @RequestMapping("/UProblemVisiablePid")
    public JsonMsg setProblemVisiablePid(HttpServletRequest req, HttpServletResponse resp) {
        Integer pid = 1;
        Integer num = problemService.setProblemVisiablePid(pid);

        return JsonMsg.success().addInfo(num);
    }

    /**
     * 将一个题目的可见状态改为想要的状态用 z 表示
     */
    @RequestMapping("/UProblemVisiablePidZ")
    public JsonMsg UProblemVisiablePidZ(HttpServletRequest req, HttpServletResponse resp) {
        Integer pid = 1;
        Integer z = 0;
        Integer num = problemService.setProblemVisiablePidZ(pid, z);

        return JsonMsg.success().addInfo(num);
    }

    /**
     * 获取某个oj 的一个题目
     */
    @RequestMapping("/GProblemsByOjPid")
    public JsonMsg getProblemsByOjPid(HttpServletRequest req, HttpServletResponse resp) {
        Integer oj = 1;
        String ojspid = "";

        List<Integer> list = problemService.getProblemsByOjPid(oj, ojspid);
        return JsonMsg.success().addInfo(list);
    }
}
