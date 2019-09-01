package com.fjut.oj.controller;

import com.fjut.oj.interceptor.CheckUserPrivate;
import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.pojo.MessagePO;
import com.fjut.oj.service.MessageService;
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
    public JsonInfoVO delMessageByMid(@RequestParam("username") String username, @RequestParam("mid") String midStr) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer mid = Integer.parseInt(midStr);
        int res = messageService.deleteMessageByMid(mid);
        if (1 == res) {
            JsonInfoVO.setSuccess();
        } else {
            JsonInfoVO.setFail();
        }
        return JsonInfoVO;
    }

    @CheckUserPrivate
    @PostMapping("/setReaded")
    public JsonInfoVO setReadedByMid(@RequestParam("username") String username,
                                   @RequestParam("mid") String midStr) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer mid = Integer.parseInt(midStr);
        Integer res = messageService.updateMessageStatuByMid(mid, 1);
        if (1 == res) {
            JsonInfoVO.setSuccess();
        } else {
            JsonInfoVO.setFail();
        }
        return JsonInfoVO;
    }

    @CheckUserPrivate
    @PostMapping("/setAllMessageRead")
    public JsonInfoVO setAllMessageReadByUser(@RequestParam("username") String username) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        if ("" != username) {
            int res = messageService.updateAllMessageReadByUser(username);
            if (0 != res) {
                JsonInfoVO.setSuccess();
                JsonInfoVO.addInfo(res);
            } else {
                JsonInfoVO.setFail();
            }

        } else {
            JsonInfoVO.setFail();
        }
        return JsonInfoVO;
    }

    @CheckUserPrivate
    @GetMapping("/getUserMessage")
    public JsonInfoVO getUserMessage(@RequestParam("username") String username, @RequestParam("pagenum") String pageNumStr) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer pageNum = Integer.parseInt(pageNumStr);
        Integer startIndex;
        startIndex = (pageNum - 1) * 10;
        List<MessagePO> messages = messageService.queryAllMessageByUser(username, startIndex);
        int countMessage = messageService.queryAllMessageCountByUser(username);
        if (0 != messages.size()) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(messages);
            JsonInfoVO.addInfo(countMessage);
        } else {
            JsonInfoVO.setFail();
        }
        return JsonInfoVO;
    }

    @CheckUserPrivate
    @GetMapping("/getUnReadMessageCount")
    public JsonInfoVO getUnReadMessageCountByUser(HttpServletRequest request, HttpServletResponse response) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        String username = request.getParameter("username");
        if (null != username) {
            Integer res = messageService.queryUnReadMessageCountByUser(username);
            if (0 == res) {
                JsonInfoVO.setFail();
            } else {
                JsonInfoVO.setSuccess();
            }
            JsonInfoVO.addInfo(res);
        }
        return JsonInfoVO;
    }

    @CheckUserPrivate
    @GetMapping("/getUnReadMessage")
    public JsonInfoVO getUnReadMessageByUser(HttpServletRequest request, HttpServletResponse response) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        String username = request.getParameter("username");
        String pageNumStr = request.getParameter("pagenum");
        Integer pageNum = Integer.parseInt(pageNumStr);
        Integer startIndex;
        startIndex = (pageNum - 1) * 10;
        List<MessagePO> messages = messageService.queryUnReadMessageByUser(username, startIndex);
        Integer unReadCount = messageService.queryUnReadMessageCountByUser(username);
        if (0 != messages.size()) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(messages);
            JsonInfoVO.addInfo(unReadCount);
        } else {
            JsonInfoVO.setFail();
        }
        return JsonInfoVO;
    }


}
