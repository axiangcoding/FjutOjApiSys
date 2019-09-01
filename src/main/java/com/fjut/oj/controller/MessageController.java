package com.fjut.oj.controller;

import com.fjut.oj.interceptor.CheckUserPrivate;
import com.fjut.oj.pojo.TableMessage;
import com.fjut.oj.service.MessageService;
import com.fjut.oj.util.JsonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author axiang
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @CheckUserPrivate
    @PostMapping("/delMessage")
    public JsonInfo delMessageByMid(@RequestParam("username") String username, @RequestParam("mid") String midStr) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer mid = Integer.parseInt(midStr);
        int res = messageService.deleteMessageByMid(mid);
        if (1 == res) {
            jsonInfo.setSuccess();
        } else {
            jsonInfo.setFail();
        }
        return jsonInfo;
    }

    @CheckUserPrivate
    @PostMapping("/setReaded")
    public JsonInfo setReadedByMid(@RequestParam("username") String username,
                                   @RequestParam("mid") String midStr) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer mid = Integer.parseInt(midStr);
        Integer res = messageService.updateMessageStatuByMid(mid, 1);
        if (1 == res) {
            jsonInfo.setSuccess();
        } else {
            jsonInfo.setFail();
        }
        return jsonInfo;
    }

    @CheckUserPrivate
    @PostMapping("/setAllMessageRead")
    public JsonInfo setAllMessageReadByUser(@RequestParam("username") String username) {
        JsonInfo jsonInfo = new JsonInfo();
        if ("" != username) {
            int res = messageService.updateAllMessageReadByUser(username);
            if (0 != res) {
                jsonInfo.setSuccess();
                jsonInfo.addInfo(res);
            } else {
                jsonInfo.setFail();
            }

        } else {
            jsonInfo.setFail();
        }
        return jsonInfo;
    }

    @CheckUserPrivate
    @GetMapping("/getUserMessage")
    public JsonInfo getUserMessage(@RequestParam("username") String username, @RequestParam("pagenum") String pageNumStr) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer pageNum = Integer.parseInt(pageNumStr);
        Integer startIndex;
        startIndex = (pageNum - 1) * 10;
        List<TableMessage> messages = messageService.queryAllMessageByUser(username, startIndex);
        int countMessage = messageService.queryAllMessageCountByUser(username);
        if (0 != messages.size()) {
            jsonInfo.setSuccess();
            jsonInfo.addInfo(messages);
            jsonInfo.addInfo(countMessage);
        } else {
            jsonInfo.setFail();
        }
        return jsonInfo;
    }

    @CheckUserPrivate
    @GetMapping("/getUnReadMessageCount")
    public JsonInfo getUnReadMessageCountByUser(HttpServletRequest request, HttpServletResponse response) {
        JsonInfo jsonInfo = new JsonInfo();
        String username = request.getParameter("username");
        if (null != username) {
            Integer res = messageService.queryUnReadMessageCountByUser(username);
            if (0 == res) {
                jsonInfo.setFail();
            } else {
                jsonInfo.setSuccess();
            }
            jsonInfo.addInfo(res);
        }
        return jsonInfo;
    }

    @CheckUserPrivate
    @GetMapping("/getUnReadMessage")
    public JsonInfo getUnReadMessageByUser(HttpServletRequest request, HttpServletResponse response) {
        JsonInfo jsonInfo = new JsonInfo();
        String username = request.getParameter("username");
        String pageNumStr = request.getParameter("pagenum");
        Integer pageNum = Integer.parseInt(pageNumStr);
        Integer startIndex;
        startIndex = (pageNum - 1) * 10;
        List<TableMessage> messages = messageService.queryUnReadMessageByUser(username, startIndex);
        Integer unReadCount = messageService.queryUnReadMessageCountByUser(username);
        if (0 != messages.size()) {
            jsonInfo.setSuccess();
            jsonInfo.addInfo(messages);
            jsonInfo.addInfo(unReadCount);
        } else {
            jsonInfo.setFail();
        }
        return jsonInfo;
    }


}
