package com.erget.chatgpt.interceptor;

import com.erget.chatgpt.exception.InvalidTokenException;
import com.erget.chatgpt.util.JwtUtil;
import com.erget.chatgpt.util.UserContextUtil;
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
    JwtUtil jwtUtil;
    @Autowired
    UserContextUtil userContextUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        String user = request.getHeader("user");
        if (StringUtils.isBlank(token) || StringUtils.isBlank(user)){
            response.setCharacterEncoding("UTF-8"); // 避免乱码
            throw new ServletException("请求参数缺失");
        }
        //保存用户名
        userContextUtil.setUserName(user);
        Boolean tokenExpired = jwtUtil.validateToken(token,user);
        if (tokenExpired){

        }else {
            throw new InvalidTokenException("token过期或无效");
        }
        return true;
    }
}