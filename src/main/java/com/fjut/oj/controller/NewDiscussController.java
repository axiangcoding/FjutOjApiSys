package com.fjut.oj.controller;

import com.fjut.oj.interceptor.CheckUserIsAdmin;
import com.fjut.oj.interceptor.CheckUserIsLogin;
import com.fjut.oj.pojo.NewDiscuss;
import com.fjut.oj.service.NewDiscussService;
import com.fjut.oj.util.JsonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cjt
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/discuss")
public class NewDiscussController {

    @Autowired
    private NewDiscussService newDiscussService;

    @CheckUserIsLogin
    @GetMapping("/getDiscuss")
    public JsonInfo queryDiscussByPage(@RequestParam(value = "pagenum", required = false) String pageNumStr) {
        JsonInfo jsonInfo = new JsonInfo();
        if (null == pageNumStr) {
            pageNumStr = "1";
        }
        Integer pageNum = Integer.parseInt(pageNumStr);
        Integer start = (pageNum - 1) * 50;
        Integer totalNum = newDiscussService.queryDiscussCount();
        Integer totalPage = (totalNum % 50 == 0) ? totalNum / 50 : totalNum / 50 + 1;
        List<NewDiscuss> list = newDiscussService.queryDiscussByPage(start);
        jsonInfo.setSuccess();
        jsonInfo.addInfo(totalPage);
        jsonInfo.addInfo(list);
        return jsonInfo;
    }

    @CheckUserIsLogin
    @PostMapping("/putDiscuss")
    public JsonInfo insertDiscuss(HttpServletRequest req) {
        JsonInfo jsonInfo = new JsonInfo();
        NewDiscuss newDiscuss = new NewDiscuss();
        String title = req.getParameter("title");

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String author = req.getParameter("author");
        Double priority = Double.parseDouble(req.getParameter("priority") == null ? "-1" : req.getParameter("priority"));

        newDiscuss.setTitle(title);
        newDiscuss.setTime(dateString);
        newDiscuss.setAuthor(author);
        newDiscuss.setPriority(priority);

        Integer num = newDiscussService.insertDiscuss(newDiscuss);
        if (num == 0) {
            jsonInfo.setFail("新建讨论失败！");

        } else {
            jsonInfo.setSuccess("新建讨论成功！");
        }
        return jsonInfo;
    }

    @CheckUserIsAdmin
    @PostMapping("/updatePriority")
    public JsonInfo updatePriority(@RequestParam("idStr") String idStr, @RequestParam("priorityStr") String priorityStr) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer id = Integer.parseInt(idStr);
        Double priority = Double.parseDouble(priorityStr);

        Integer num = newDiscussService.updateDiscussPirority(id, priority);
        if (num == 0) {
            jsonInfo.setFail("修改失败！");
        } else {
            jsonInfo.setSuccess("修改成功！");
        }
        return jsonInfo;
    }
}
