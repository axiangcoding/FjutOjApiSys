package com.fjut.oj.interceptor;

import com.fjut.oj.exception.NotLoginException;
import com.fjut.oj.pojo.TokenModel;
import com.fjut.oj.redis.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: axiang [20190705] 判断用户登录状态拦截器
 */
@Component
public class CheckUserIsLoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenManager manager;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法上就直接跳过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        CheckUserIsLogin checkUserIsLogin = handlerMethod.getMethodAnnotation(CheckUserIsLogin.class);
        if (null == checkUserIsLogin) {
            return true;
        }
        // 从头部获取Token
        String auth = request.getHeader("auth");
        TokenModel model = manager.getToken(auth);
        if (manager.checkToken(model)) {
            return true;
        } else {
            throw new NotLoginException();
        }
    }

}
