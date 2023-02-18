package com.erget.chatgpt.constant;



public final class ResultCode {

    private ResultCode() {

    }

    public static final String ACK = "ACK";  //成功

    public static final String NACK = "NACK";     //失败

    public static final String REDIRECT = "REDIRECT";

    public static final String VALIDATION_ERROR = "VALIDATION_ERROR";

    public static final String COMMON_ERROR = "COMMON_ERROR";

    public static final String BUSINESS_ERROR = "BUSINESS_ERROR";  //业务异常

    public static final String UNAUTHORIZED = "UNAUTHORIZED";       //未授权

}
