package com.fjut.oj.controller;

import com.fjut.oj.exception.NotOwnerException;
import com.fjut.oj.pojo.*;
import com.fjut.oj.pojo.enums.ChallengeBlockType;
import com.fjut.oj.service.ChallengeService;
import com.fjut.oj.util.JsonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: axiang [20190625]
 */
@Controller
@CrossOrigin
@RequestMapping("/challenge")
@ResponseBody
public class ChallengeController {
    @Autowired
    ChallengeService challengeService;

    /**
     * 获取用户的挑战模块
     *
     * @param username
     * @return
     */
    @GetMapping("/getAllChallengeBlocks")
    public JsonInfo queryAllChallengeBlocks(@RequestParam("username") String username) {
        JsonInfo jsonInfo = new JsonInfo();
        List<ChallengeBlockForUser> res = new ArrayList<>();
        List<ChallengeBlockForUser> allBlocks = challengeService.queryAllChallengeBlocks();
        if (null != username && !("").equals(username)) {
            Map<Integer, ChallengeBlockForUser> map = new TreeMap<>();
            List<Integer> showedIds = challengeService.queryShowedChallengeBlocksByUsername(username);
            for (ChallengeBlockForUser challengeBlock : allBlocks) {
                challengeBlock.setLocked(true);
                challengeBlock.setGetScore(0);
                map.put(challengeBlock.getId(), challengeBlock);
            }
            List<Integer> openBlocks = challengeService.queryChallengeOpenBlocksByUsername(username);
            for (Integer openBlockId : openBlocks) {
                ChallengeBlockForUser temp = map.get(openBlockId);
                temp.setLocked(false);
                map.put(temp.getId(), temp);
            }
            List<ChallengeBlockForUser> challengeBlockScored = challengeService.queryChallengeBlocksScoredByUsername(username);
            for (ChallengeBlockForUser challengeBlock : challengeBlockScored) {
                ChallengeBlockForUser temp = map.get(challengeBlock.getId());
                temp.setGetScore(challengeBlock.getGetScore());
                map.put(temp.getId(), temp);
            }
            for (Integer key : map.keySet()) {
                if (showedIds.contains(key)) {
                    res.add(map.get(key));
                }
            }
            List<t_challenge_condition> allConditions = challengeService.queryAllChallengeConditions();
            jsonInfo.setSuccess();
            jsonInfo.addInfo(res);
            jsonInfo.addInfo(allConditions);
        } else {
            jsonInfo.setError("参数不正确！");
        }
        return jsonInfo;
    }

    /**
     * 获取解锁条件
     *
     * @param blockIdStr
     * @return
     */
    @GetMapping("/getCondition")
    public JsonInfo getConditionByBlockId(@RequestParam("blockId") String blockIdStr) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer blockId = Integer.parseInt(blockIdStr);
        List<ChallengeConditionForBlock> conditions = challengeService.queryChallengeConditionByBlockId(blockId);
        if (0 < conditions.size()) {
            jsonInfo.setSuccess("有解锁条件");
            jsonInfo.addInfo(conditions);
        } else {
            jsonInfo.setSuccess("没有解锁条件");
        }
        return jsonInfo;
    }

    /**
     * 获取模块详情
     *
     * @param blockIdStr
     * @param username
     * @return
     */
    @GetMapping("/getBlockDetail")
    public JsonInfo getBlockDetail(
            @RequestParam("blockId") String blockIdStr,
            @RequestParam("username") String username) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer blockId = Integer.parseInt(blockIdStr);
        ChallengeBlock block = challengeService.queryChallengeBlockByBlockId(blockId);
        // 获取该模块的全部得到分值
        List<ChallengeBlockForUser> getScores = challengeService.queryChallengeBlocksScoredByUsername(username);
        Integer getScore = 0;
        for (ChallengeBlockForUser get : getScores) {
            if (get.getId().equals(blockId)) {
                getScore = get.getGetScore();
            }
        }
        Integer totalScore = challengeService.queryChallengeBlockTotalScoreByBlockId(blockId);
        ChallengeBlockDetail challengeBlockDetail = new ChallengeBlockDetail();
        if (null != block) {
            challengeBlockDetail.setId(block.getId());
            challengeBlockDetail.setName(block.getName());
            challengeBlockDetail.setType(ChallengeBlockType.getNameByID(block.getGro()));
            challengeBlockDetail.setDes(block.getText());
            challengeBlockDetail.setTotalScore(totalScore);
            jsonInfo.setSuccess();
            jsonInfo.addInfo(challengeBlockDetail);
            jsonInfo.addInfo(getScore);
        } else {
            jsonInfo.setFail("未找到该挑战模块");
        }
        return jsonInfo;
    }

    /**
     * 获取模块的题目
     *
     * @param username
     * @param blockIdStr
     * @param pageNumStr
     * @return
     */
    @GetMapping("/getBlockProblems")
    public JsonInfo getBlockProblems(
            @RequestParam("username") String username,
            @RequestParam("blockId") String blockIdStr,
            @RequestParam("pageNum") String pageNumStr) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer blockId = Integer.parseInt(blockIdStr);
        Integer pageNum = Integer.parseInt(pageNumStr);
        Integer startIndex = (pageNum - 1) * 15;
        List<ChallengeProblemForBlock> challengeProblems =
                challengeService.queryChallengeBlockProblemByBlockId(blockId, startIndex);
        Integer count = challengeService.queryChallengeBlockProblemCountByBlockId(blockId);
        if (null == username) {
            throw new NotOwnerException();
        }
        // FIXME: 已有数据库表保存解题状态，这里是多余了，虽然功能正常，但是是冗余，必须修改！！！
        if (0 < count) {
            List<Status> statuses = challengeService.queryAllBlockSolvedProblemByUsername(username);
            Map<Integer, Integer> statusMap = new TreeMap<>();
            for (Status status : statuses) {
                // 提交状态有多个并且包含AC时，只保留AC的一个或者其他的最新状态
                if (null == statusMap.get(status.getPid())) {
                    statusMap.put(status.getPid(), status.getResult());
                } else if (1 != statusMap.get(status.getPid())) {
                    statusMap.put(status.getPid(), status.getResult());
                }
            }
            for (ChallengeProblemForBlock problem : challengeProblems) {
                if (null == statusMap.get(problem.getTrueProblemId())) {
                    problem.setSolved(0);
                    // 未设置 即表示未做过该题
                } else if (1 == statusMap.get(problem.getTrueProblemId())) {
                    problem.setSolved(1);
                } else {
                    problem.setSolved(2);
                }
            }
            jsonInfo.setSuccess();
            jsonInfo.addInfo(challengeProblems);
            jsonInfo.addInfo(count);
        } else {
            jsonInfo.setFail("模块内没有题目");
        }
        return jsonInfo;
    }

    /**
     * TODO: 挑战模块解锁验证
     *
     * @param username
     * @param pid
     * @return
     */
    @PostMapping("/updateOpenBlock")
    public JsonInfo updateOpenBlock(@RequestParam("username") String username,
                                    @RequestParam("pid") Integer pid) {
        JsonInfo jsonInfo = new JsonInfo();


        return jsonInfo;
    }


}
