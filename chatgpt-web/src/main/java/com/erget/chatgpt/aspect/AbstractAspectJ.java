package com.erget.chatgpt.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 切面拦截抽象类
 */
public abstract class AbstractAspectJ {


    protected Method getMethod(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        return methodSignature.getMethod();
    }
}
