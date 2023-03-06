package com.erget.chatgpt.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 统一认证拦截（只拦截chat相关）
 */


@Component
public class UserInterceptor  implements HandlerInterceptor {


    @Autowired
    JwtValidator jwtValidator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        String user = request.getHeader("user");
        if (StringUtils.isBlank(token) || StringUtils.isBlank(user)){
            response.setCharacterEncoding("UTF-8"); // 避免乱码
            throw new Exception("请求参数缺失");
        }
        Boolean tokenExpired = jwtValidator.isTokenExpired(token);
        Boolean aBoolean = jwtValidator.validateToken(user, token);
        if (tokenExpired && aBoolean){

        }else {
            throw new ServletException("token过期或无效");
        }
        return true;
    }
}