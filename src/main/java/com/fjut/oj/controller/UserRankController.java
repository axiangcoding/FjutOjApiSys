package com.fjut.oj.controller;

import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.pojo.UserPO;
import com.fjut.oj.service.AllUserRankService;
import com.fjut.oj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author axiang [20190808]
 */
@Controller
@RequestMapping("/rank")
@CrossOrigin
@ResponseBody
public class UserRankController {
    @Autowired
    private AllUserRankService allUserRankService;

    @Autowired
    private UserService userService;

    /**
     * 获得用户排行榜
     *
     * @param pageNumStr
     * @param order
     * @param desc
     * @return
     */
    @GetMapping("/GUserRank")
    public JsonInfoVO getAllUsersRank(@RequestParam("pagenum") String pageNumStr,
                                      @RequestParam(value = "order", required = false) String order,
                                      @RequestParam(value = "desc", required = false) String desc) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        if (null == order) {
            order = "acnum";
        }
        Integer pageNum = Integer.parseInt(pageNumStr);
        Integer totalUser = userService.queryUserCount();
        Integer totalPageNum = totalUser % 50 == 0 ? totalUser / 50 : totalUser / 50 + 1;
        Integer start = (pageNum - 1) * 50;
        List<UserPO> list = allUserRankService.allUsersRank(order, desc, start);
        JsonInfoVO.setSuccess();
        JsonInfoVO.addInfo(totalPageNum);
        JsonInfoVO.addInfo(list);
        return JsonInfoVO;
    }

    @RequestMapping("/GUserRankByName")
    public JsonInfoVO getUsersRankByName(@RequestParam("pagenum") String pageNumStr,
                                       @RequestParam("username") String username) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer pageNum = Integer.parseInt(pageNumStr);
        Integer totalUser = allUserRankService.queryUserCountByName(username);
        Integer totalPageNum = totalUser % 50 == 0 ? totalUser / 50 : totalUser / 50 + 1;
        Integer start = (pageNum - 1) * 50;

        List<UserPO> list = allUserRankService.queryUserByName(username, start);
        JsonInfoVO.setSuccess();
        JsonInfoVO.addInfo(totalPageNum);
        JsonInfoVO.addInfo(list);
        return JsonInfoVO;
    }
}
