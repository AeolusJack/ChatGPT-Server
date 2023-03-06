package com.erget.chatgpt.hander;

import com.erget.chatgpt.dto.ResultDto;
import com.erget.chatgpt.dto.ResultDtoFactory;
import com.erget.chatgpt.exception.InvalidTokenException;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalHandlerExceptionResolver {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultDto allExceptionHandler(Exception e) {
        log.debug("================进入异常处理================");
        if (e instanceof InvalidTokenException){
            return ResultDtoFactory.toCustom("201",e.getMessage(),null);
        }
        log.error("请求出错",e);
        return ResultDtoFactory.toNack(e.getMessage());
    }

}

