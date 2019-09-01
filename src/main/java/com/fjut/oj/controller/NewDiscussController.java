package com.fjut.oj.controller;

import com.fjut.oj.interceptor.CheckUserIsAdmin;
import com.fjut.oj.interceptor.CheckUserIsLogin;
import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.pojo.NewDiscuss;
import com.fjut.oj.service.NewDiscussService;
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
    public JsonInfoVO queryDiscussByPage(@RequestParam(value = "pagenum", required = false) String pageNumStr) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        if (null == pageNumStr) {
            pageNumStr = "1";
        }
        Integer pageNum = Integer.parseInt(pageNumStr);
        Integer start = (pageNum - 1) * 50;
        Integer totalNum = newDiscussService.queryDiscussCount();
        Integer totalPage = (totalNum % 50 == 0) ? totalNum / 50 : totalNum / 50 + 1;
        List<NewDiscuss> list = newDiscussService.queryDiscussByPage(start);
        JsonInfoVO.setSuccess();
        JsonInfoVO.addInfo(totalPage);
        JsonInfoVO.addInfo(list);
        return JsonInfoVO;
    }

    @CheckUserIsLogin
    @PostMapping("/putDiscuss")
    public JsonInfoVO insertDiscuss(HttpServletRequest req) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
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
            JsonInfoVO.setFail("新建讨论失败！");

        } else {
            JsonInfoVO.setSuccess("新建讨论成功！");
        }
        return JsonInfoVO;
    }

    @CheckUserIsAdmin
    @PostMapping("/updatePriority")
    public JsonInfoVO updatePriority(@RequestParam("idStr") String idStr, @RequestParam("priorityStr") String priorityStr) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer id = Integer.parseInt(idStr);
        Double priority = Double.parseDouble(priorityStr);

        Integer num = newDiscussService.updateDiscussPirority(id, priority);
        if (num == 0) {
            JsonInfoVO.setFail("修改失败！");
        } else {
            JsonInfoVO.setSuccess("修改成功！");
        }
        return JsonInfoVO;
    }
}
