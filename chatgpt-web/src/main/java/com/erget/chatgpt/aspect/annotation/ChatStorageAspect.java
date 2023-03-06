package com.erget.chatgpt.aspect.annotation;


import java.lang.annotation.*;

/**
 * chat切面注解（用来做对话拦截）
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ChatStorageAspect {
    String desc() default "";
}
