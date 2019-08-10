package com.fjut.oj.controller;

import com.fjut.oj.pojo.Status;
import com.fjut.oj.pojo.UserSolve;
import com.fjut.oj.pojo.ViewUserStatus;
import com.fjut.oj.pojo.enums.PermissionType;
import com.fjut.oj.service.CodeViewService;
import com.fjut.oj.service.StatusService;
import com.fjut.oj.service.UserPermissionService;
import com.fjut.oj.service.UserSolveService;
import com.fjut.oj.util.JsonInfo;
import com.fjut.oj.util.MapSort;
import com.fjut.oj.util.ResultString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author axiang
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @Autowired
    private UserSolveService userSolveService;

    @Autowired
    private UserPermissionService permissionService;

    @Autowired
    private CodeViewService codeViewService;


    @RequestMapping("/GAllStatus")
    public JsonInfo queryAllStatus(@RequestParam("pagenum") String pageNumStr) {
        JsonInfo jsonInfo = new JsonInfo();
        int pageNum = Integer.parseInt(pageNumStr);
        Integer num = statusService.allStatusNum();
        if (num == 0) {
            jsonInfo.setSuccess("数据为空");
        }
        int from = (pageNum - 1) * 50;
        List<ViewUserStatus> statuses = statusService.queryStatus(from);
        jsonInfo.setSuccess();
        jsonInfo.addInfo(num % 50 == 0 ? num / 50 : num / 50 + 1);
        jsonInfo.addInfo(statuses);
        return jsonInfo;
    }

    @RequestMapping("/GAllStatusByUsername")
    public JsonInfo queryAllStatusByUsername(HttpServletRequest req, HttpServletResponse resp) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer pid;
        String ruser, submitTime;
        String username = req.getParameter("username");
        Map<String, Integer> submitToal = new TreeMap<String, Integer>();
        Map<String, Integer> submitAc = new TreeMap<String, Integer>();
        Map<String, Integer> vis = new TreeMap<String, Integer>();
        UserSolve userSolve = null;
        if (null == username) {
            jsonInfo.setFail("无用户");
            return jsonInfo;
        }
        List<Status> list = statusService.getAllStatusByUsername(username);
        for (Status st : list) {
            submitTime = st.getSubmitTime().substring(0, 7);
            Integer num = submitToal.get(submitTime) == null ? 1 : submitToal.get(submitTime) + 1;
            submitToal.put(submitTime, num);
            if (st.getResult() == 1) {
                pid = st.getPid();
                ruser = st.getRuser();
                if (vis.get(ruser + "ABCDEFG" + pid) == null) {
                    vis.put(ruser + "ABCDEFG" + pid, 1);
                    Integer numAc = submitAc.get(submitTime) == null ? 1 : submitAc.get(submitTime) + 1;
                    submitAc.put(submitTime, numAc);
                }
            }

        }
        Map<String, Integer> totalMap = MapSort.sortMapByKey(submitToal);
        Map<String, Integer> acMap = MapSort.sortMapByKey(submitAc);
        jsonInfo.setSuccess();
        jsonInfo.addInfo(totalMap);
        jsonInfo.addInfo(acMap);
        return jsonInfo;
    }

    @RequestMapping("/GStatusByConditions")
    public JsonInfo queryAllStatusByConditions(HttpServletRequest req, HttpServletResponse resp) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer pid, result, lang, start;
        Integer pageNum = Integer.parseInt(req.getParameter("pagenum") == null ? "1" : req.getParameter("pagenum"));
        String ruser = req.getParameter("ruser") == null ? "" : req.getParameter("ruser");
        String pidStr = req.getParameter("pid") == null ? "" : req.getParameter("pid");
        String resultStr = req.getParameter("result") == null ? "" : req.getParameter("result");
        String langStr = req.getParameter("lang") == null ? "" : req.getParameter("lang");
        start = (pageNum - 1) * 50;
        if (pidStr == "") {
            pid = null;
        } else {
            pid = Integer.parseInt(pidStr);
        }
        if (resultStr == "" || "All".equals(resultStr)) {
            result = null;
        } else {
            result = ResultString.getResultString(resultStr);
        }
        if (langStr == "" || "All".equals(langStr)) {
            lang = null;
        } else {
            lang = ResultString.getSubmitLanguage(langStr);
        }
        Integer totalStatus = statusService.queryCountAllStatusByConditions(ruser, pid, result, lang, start);
        Integer totalPage = totalStatus % 50 == 0 ? totalStatus / 50 : totalStatus / 50 + 1;
        List<Status> list = statusService.queryAllStatusByConditions(ruser, pid, result, lang, start);
        if (0 == list.size()) {
            jsonInfo.setFail("未找到内容");
        } else {
            jsonInfo.setSuccess();
            jsonInfo.addInfo(totalPage);
            jsonInfo.addInfo(list);
        }
        return jsonInfo;
    }

    @GetMapping("/getStatusById")
    public JsonInfo getStatusById(@RequestParam("id") String idStr, @RequestParam("user") String user) {
        JsonInfo jsonInfo = new JsonInfo();
        Integer id = Integer.parseInt(idStr);
        Status status = statusService.queryStatusById(id);
        boolean permissionCanViewOthersCode = permissionService.queryUserPermissionAvailable(user, PermissionType.viewCode.getCode());
        boolean normalCanViewOthersCode = codeViewService.queryCanUserViewCodeByPid(user, id);
        if (null == status) {
            jsonInfo.setFail("评测信息不存在！");
            return jsonInfo;
        } else if (status.getRuser().equals(user) || permissionCanViewOthersCode || normalCanViewOthersCode ) {
            jsonInfo.setSuccess();
            jsonInfo.addInfo(status);
        } else{
            jsonInfo.setFail("不允许查看此评测代码，请完成该题解答或者使用ACB购买该题");
        }
        return jsonInfo;
    }

}
