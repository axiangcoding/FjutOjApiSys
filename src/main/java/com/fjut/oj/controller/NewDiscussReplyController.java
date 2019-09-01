package com.fjut.oj.controller;

import com.fjut.oj.pojo.NewDiscussReply;
import com.fjut.oj.service.NewdiscussreplyService;
import com.fjut.oj.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * TODO: 把 JsonMsg 替换为 JsonInfo
 *
 * @author cjt
 */
@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("/newdiscussreply")
public class NewDiscussReplyController {

    @Autowired
    private NewdiscussreplyService newdiscussreplyService;

    @RequestMapping("/GDiscussReplyById")
    public JsonMsg queryDiscussReplyById(HttpServletRequest req, HttpServletResponse resp) {
        String pagenumStr = req.getParameter("pagenum") == null ? "1" : req.getParameter("pagenum");
        Integer pagenum = Integer.parseInt(pagenumStr);
        Integer start = (pagenum - 1) * 50;
        if (req.getParameter("discussid") == null) {
            return JsonMsg.fail().addInfo("未传入discussid");
        }
        Integer id = Integer.parseInt(req.getParameter("discussid"));
        Integer totalNum = newdiscussreplyService.quertCountReplies(id);
        Integer totalPage = (totalNum % 50 == 0 ? totalNum / 50 : totalNum / 50 + 1);
        List<NewDiscussReply> list = newdiscussreplyService.queryDiscussReplyById(start, id);
        return JsonMsg.success().addInfo(totalPage).addInfo(list);
    }

    @RequestMapping("/insertDiscussReply")
    public JsonMsg insertDiscussReply(HttpServletRequest req, HttpServletResponse resp) {
        Integer discussid = Integer.parseInt(req.getParameter("discussid"));
        if (discussid == null) {
            return JsonMsg.fail().addInfo("未传入讨论id");
        }
        Integer maxreplyid = newdiscussreplyService.quertCountReplies(discussid);

        if (maxreplyid == null) {
            maxreplyid = 0;
        }
        maxreplyid = maxreplyid + 1;

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateString = formatter.format(currentTime);

        String author = req.getParameter("author");
        String text = req.getParameter("text");

        NewDiscussReply newDiscussReply = new NewDiscussReply();
        newDiscussReply.setDiscussid(discussid);
        newDiscussReply.setReplyid(maxreplyid);
        newDiscussReply.setTime(dateString);
        newDiscussReply.setAuthor(author);
        newDiscussReply.setText(text);
        newdiscussreplyService.insertDiscussReply(newDiscussReply);

        return JsonMsg.success().addInfo(newDiscussReply);
    }
}
