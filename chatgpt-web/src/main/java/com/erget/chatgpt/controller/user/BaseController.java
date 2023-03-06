package com.erget.chatgpt.controller.user;

import com.erget.chatgpt.dto.ResultDto;
import com.erget.chatgpt.dto.ResultDtoFactory;



public class BaseController {

    private static final String SUCCESS = "SUCCESS";

    protected static ResultDto<String> ok() {
        return ResultDtoFactory.toAck();
    }

    protected static <T> ResultDto<T> ok(T t) {
        return ResultDtoFactory.toAckData(t);
    }



    protected static <T> ResultDto<Exception> fail(Exception exception) {
        return ResultDtoFactory.toNack("请求失败",exception);
    }


    protected static  ResultDto fail() {
        return ResultDtoFactory.toNack("请求失败");
    }



}
