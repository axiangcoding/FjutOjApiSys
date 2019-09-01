package com.fjut.oj.controller;

import com.fjut.oj.exception.NotOwnerException;
import com.fjut.oj.interceptor.CheckUserPrivate;
import com.fjut.oj.pojo.*;
import com.fjut.oj.pojo.enums.PermissionType;
import com.fjut.oj.service.CodeViewService;
import com.fjut.oj.service.StatusService;
import com.fjut.oj.service.UserPermissionService;
import com.fjut.oj.service.UserSolveService;
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


    @GetMapping("/getAllStatusByPage")
    public JsonInfoVO queryAllStatus(@RequestParam("pageNum") String pageNumStr) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        int pageNum = Integer.parseInt(pageNumStr);
        Integer num = statusService.allStatusNum();
        if (num == 0) {
            JsonInfoVO.setSuccess("数据为空");
        }
        int from = (pageNum - 1) * 50;
        List<ViewUserStatus> statuses = statusService.queryStatus(from);
        JsonInfoVO.setSuccess();
        JsonInfoVO.addInfo(num % 50 == 0 ? num / 50 : num / 50 + 1);
        JsonInfoVO.addInfo(statuses);
        return JsonInfoVO;
    }

    @RequestMapping("/GAllStatusByUsername")
    public JsonInfoVO queryAllStatusByUsername(HttpServletRequest req, HttpServletResponse resp) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer pid;
        String ruser, submitTime;
        String username = req.getParameter("username");
        Map<String, Integer> submitToal = new TreeMap<String, Integer>();
        Map<String, Integer> submitAc = new TreeMap<String, Integer>();
        Map<String, Integer> vis = new TreeMap<String, Integer>();
        UserSolve userSolve = null;
        if (null == username) {
            JsonInfoVO.setFail("无用户");
            return JsonInfoVO;
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
        JsonInfoVO.setSuccess();
        JsonInfoVO.addInfo(totalMap);
        JsonInfoVO.addInfo(acMap);
        return JsonInfoVO;
    }

    @RequestMapping("/GStatusByConditions")
    public JsonInfoVO queryAllStatusByConditions(HttpServletRequest req, HttpServletResponse resp) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        Integer pid, result, lang, start;
        Integer pageNum = Integer.parseInt(req.getParameter("pagenum") == null ? "1" : req.getParameter("pagenum"));
        String ruser = req.getParameter("ruser") == null ? "" : req.getParameter("ruser");
        String pidStr = req.getParameter("pid") == null ? "" : req.getParameter("pid");
        String resultStr = req.getParameter("result") == null ? "" : req.getParameter("result");
        String langStr = req.getParameter("lang") == null ? "" : req.getParameter("lang");
        start = (pageNum - 1) * 50;
        if (pidStr.equals("")) {
            pid = null;
        } else {
            pid = Integer.parseInt(pidStr);
        }
        if (resultStr.equals("") || "All".equals(resultStr)) {
            result = null;
        } else {
            result = ResultString.getResultString(resultStr);
        }
        if (langStr.equals("") || "All".equals(langStr)) {
            lang = null;
        } else {
            lang = ResultString.getSubmitLanguage(langStr);
        }
        Integer totalStatus = statusService.queryCountAllStatusByConditions(ruser, pid, result, lang, start);
        Integer totalPage = totalStatus % 50 == 0 ? totalStatus / 50 : totalStatus / 50 + 1;
        List<ViewUserStatus> list = statusService.queryAllStatusByConditions(ruser, pid, result, lang, start);
        if (0 == list.size()) {
            JsonInfoVO.setFail("未找到内容");
        } else {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(totalPage);
            JsonInfoVO.addInfo(list);
        }
        return JsonInfoVO;
    }

    @CheckUserPrivate
    @GetMapping("/getStatusById")
    public JsonInfoVO getStatusById(@RequestParam("id") String idStr,
                                  @RequestParam(value = "username", required = false) String username) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        if ("".equals(username) || null == username) {
            throw new NotOwnerException();
        }
        Integer id = Integer.parseInt(idStr);
        ViewUserStatus viewUserStatus = statusService.queryStatusViewById(id);
        boolean permissionCanViewOthersCode = permissionService.queryUserPermissionAvailable(username, PermissionType.VIEW_CODE.getCode());
        boolean normalCanViewOthersCode = codeViewService.queryCanUserViewCodeByPid(username, id);
        if (null == viewUserStatus) {
            JsonInfoVO.setFail("评测信息不存在！");
            return JsonInfoVO;
        } else if (viewUserStatus.getRuser().equals(username) || permissionCanViewOthersCode || normalCanViewOthersCode) {
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(viewUserStatus);
        } else {
            JsonInfoVO.setSuccess("权限不足!");
            viewUserStatus.setCode("不允许查看此评测代码，请完成该题解答或者使用ACB购买该题");
            JsonInfoVO.addInfo(viewUserStatus);
        }
        return JsonInfoVO;
    }

}
