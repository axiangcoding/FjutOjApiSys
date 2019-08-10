package com.fjut.oj.interceptor;

import com.fjut.oj.exception.NotOwnerException;
import com.fjut.oj.redis.TokenManager;
import com.fjut.oj.pojo.TokenModel;
import com.fjut.oj.util.IPTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: axiang [2019/7/12] 用户是否请求私密内容的拦截器，请求中带用户名时生效
 */
@Component
public class CheckUserPrivateInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenManager manager;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法上就直接跳过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        CheckUserPrivate checkUserPrivate = handlerMethod.getMethodAnnotation(CheckUserPrivate.class);
        if (null == checkUserPrivate) {
            return true;
        }
        // 从头部获取Token
        String auth = request.getHeader("auth");
        String username = request.getParameter("username");
        TokenModel model = manager.getToken(auth);
        if (manager.checkToken(model) && model.getUsername().equals(username)) {
            return true;
        } else {
            String ip = IPTool.getClientIpAddress(request);
            throw new NotOwnerException("", ip);
        }
    }

}
