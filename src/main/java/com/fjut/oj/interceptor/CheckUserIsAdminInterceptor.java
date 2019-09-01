package com.fjut.oj.interceptor;

import com.fjut.oj.exception.NotAdminException;
import com.fjut.oj.pojo.TokenModel;
import com.fjut.oj.redis.TokenManager;
import com.fjut.oj.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 检查用户是否为管理员拦截器
 *
 * @author axiang [20190707]
 */
public class CheckUserIsAdminInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenManager manager;

    @Autowired
    private UserPermissionService permissionService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果不是映射到方法上就直接跳过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        CheckUserIsAdmin checkUserIsAdmin = handlerMethod.getMethodAnnotation(CheckUserIsAdmin.class);
        if (null == checkUserIsAdmin) {
            return true;
        }
        // 从头部获取加密字符串
        String auth = request.getHeader("auth");
        // 解析为token
        TokenModel model = manager.getToken(auth);
        if (manager.checkToken(model) && permissionService.queryIsAdmin(model.getUsername())) {

            return true;
        } else {
            throw new NotAdminException();
        }
    }
}

