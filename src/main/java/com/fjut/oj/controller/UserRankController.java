package com.fjut.oj.controller;

import com.fjut.oj.pojo.User;
import com.fjut.oj.service.AllUserRankService;
import com.fjut.oj.service.UserService;
import com.fjut.oj.util.JsonInfo;
import com.fjut.oj.util.JsonMsg;
import net.sf.json.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @param pageNumStr
     * @param order
     * @param desc
     * @return
     */
    @GetMapping("/GUserRank")
    public JsonInfo getAllUsersRank(@RequestParam("pagenum") String pageNumStr,
                                    @RequestParam(value = "order",required = false) String order,
                                    @RequestParam(value = "desc",required = false) String desc) {
        JsonInfo jsonInfo = new JsonInfo();
        if(null == order)
        {
            order = "acnum";
        }
        Integer pageNum = Integer.parseInt(pageNumStr);
        Integer totalUser = userService.queryUserCount();
        Integer totalPageNum = totalUser % 50 == 0 ? totalUser / 50 : totalUser / 50 + 1;
        Integer start = (pageNum - 1) * 50;
        List<User> list = allUserRankService.allUsersRank(order, desc, start);
        jsonInfo.setSuccess();
        jsonInfo.addInfo(totalPageNum);
        jsonInfo.addInfo(list);
        return jsonInfo;
    }

    @RequestMapping("/GUserRankByName")
    public JsonInfo getUsersRankByName(@RequestParam("pagenum") String pageNumStr,
                                       @RequestParam("username") String username) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer pageNum = Integer.parseInt(pageNumStr);
        Integer totalUser = allUserRankService.queryUserCountByName(username);
        Integer totalPageNum = totalUser % 50 == 0 ? totalUser / 50 : totalUser / 50 + 1;
        Integer start = (pageNum - 1) * 50;

        List<User> list = allUserRankService.queryUserByName(username, start);
        jsonInfo.setSuccess();
        jsonInfo.addInfo(totalPageNum);
        jsonInfo.addInfo(list);
        return jsonInfo;
    }
}
