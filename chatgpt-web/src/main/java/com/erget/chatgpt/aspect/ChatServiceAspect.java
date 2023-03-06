package com.erget.chatgpt.aspect;


import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.erget.chatgpt.dto.ResultDto;
import com.erget.chatgpt.entity.ChatData;
import com.erget.chatgpt.service.ChatDataStorageService;
import com.erget.chatgpt.util.UserContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 使用afterReturn拦截切点，在被切方法发生异常后不会再做
 * 处理，但是需要注意，这里获取到的参数值是在被切的方法中经过处理后的参数值
 */
@Slf4j
@Aspect
@Component
public class ChatServiceAspect extends AbstractAspectJ {

    @Resource
    private ChatDataStorageService chatDataStorageService;

    @Autowired
    UserContextUtil userContextUtil;

    @Pointcut(value = "@annotation(com.erget.chatgpt.aspect.annotation.ChatStorageAspect)")
    public void serviceMethodPointcut() {
        //切点
    }


    @Before("serviceMethodPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        ChatData chatData = new ChatData();
        chatData.setContent(joinPoint.getArgs()[0].toString());
        chatData.setContentType("Q_"+name);
        chatData.setToken("");
        chatData.setCreatedTime(DateUtil.date());
        chatData.setCreatedBy(userContextUtil.getUserName());
        chatData.setUpdatedTime(DateUtil.date());
        chatData.setUpdatedBy(userContextUtil.getUserName());
        chatData.setRevision(0);
        chatDataStorageService.save(chatData);
    }
    /**
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "serviceMethodPointcut()")
    public void doActionAfter(JoinPoint joinPoint,Object ret){
        //返回内容
        log.info("RESPONSE : " + ret);
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        ResultDto resultDto = (ResultDto) ret;
        ChatData chatData = new ChatData();
        //包一层，返回数据有特殊字符，入库时会出问题
        ArrayList<String> strings = new ArrayList<>();
        strings.add(JSONUtil.toJsonStr(resultDto.getData()));
        chatData.setContent(JSONUtil.toJsonStr(strings));
        chatData.setContentType("A_"+name);
        chatData.setToken("");
        chatData.setCreatedTime(DateUtil.date());
        chatData.setCreatedBy(userContextUtil.getUserName());
        chatData.setUpdatedTime(DateUtil.date());
        chatData.setUpdatedBy(userContextUtil.getUserName());
        chatData.setRevision(0);
        chatDataStorageService.save(chatData);
    }




}
