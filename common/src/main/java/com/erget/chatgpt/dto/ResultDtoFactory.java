package com.erget.chatgpt.dto;

import com.erget.chatgpt.constant.ResultCode;
import com.erget.chatgpt.enums.EErrorCode;
import com.erget.chatgpt.exception.BaseException;
import com.erget.chatgpt.exception.BizServiceException;
import com.erget.chatgpt.exception.DisplayableError;
import com.erget.chatgpt.util.MessageUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.validation.BindingResult;


import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Class Name: ResultDtoFactory
 *
 * @author SC
 */
public final class ResultDtoFactory {

    private ResultDtoFactory() {
    }

    public static <T> ResultDto <T> toCustom(String code, String msg, T data) {
        ResultDto <T> dto = new ResultDto <T>();
        dto.setCode(code);
        dto.setMessage(msg);
        dto.setData(data);
        return dto;
    }

    public static <T> ResultDto <T> toAck(String msg, T data) {
        ResultDto <T> dto = new ResultDto <T>();
        dto.setCode(ResultCode.ACK);
        dto.setMessage(msg);
        dto.setData(data);
        return dto;
    }

    public static <T> ResultDto <T> toAck(String msg) {
        return toAck(msg, null);
    }

    public static <T> ResultDto <T> toAckData(T data) {
        return toAck(null, data);
    }

    public static <T> ResultDto <T> toAck() {
        return toAck(null, null);
    }

    public static ResultDto <String> toRedirect(String url) {
        ResultDto <String> dto = new ResultDto <String>();
        dto.setCode(ResultCode.REDIRECT);
        dto.setData(url);
        return dto;
    }

    /**
     * Description: 在controller层直接返回错误消息，避免在controller中用该方法catch异常做处理
     *
     * @param msg
     * @return
     */
    public static <T> ResultDto <T> toNack(String msg) {
        return toNack(msg, null);
    }

    /**
     * Description: 在controller层直接返回错误消息，避免在controller中用该方法catch异常做处理
     *
     * @param error
     * @return
     */
    public static <T> ResultDto <T> toNack(DisplayableError error) {
        String msg = "";
        if (error != null && StringUtils.isNotBlank(error.getErrorCode())) {
            msg = MessageUtil.getMessage(error.getDisplayMsg(), error.getArgs());
        }
        return toNack(msg, null);
    }

    /**
     * Description: 在controller层直接返回错误消息，避免在controller中用该方法catch异常做处理
     *
     * @param msg
     * @param data
     * @return
     */
    public static <T> ResultDto <T> toNack(String msg, T data) {
        ResultDto <T> dto = new ResultDto <T>();
        dto.setCode(ResultCode.NACK);
        dto.setMessage(msg);
        dto.setData(data);
        return dto;
    }

    public static ResultDto <BindingResult> toValidationError(String msg, BindingResult br) {
        ResultDto <BindingResult> dto = new ResultDto <BindingResult>();
        dto.setCode(ResultCode.VALIDATION_ERROR);
        dto.setMessage(msg);
        dto.setData(br);
        return dto;
    }

    private static ResultDto <String> toCommonError(String code, String msg, String details) {
        ResultDto <String> dto = new ResultDto <String>();
        dto.setCode(ResultCode.COMMON_ERROR);
        StringBuilder text = new StringBuilder();
        if (StringUtils.isBlank(msg)) {
            text.append(MessageUtil.getMessage(EErrorCode.COMM_INTERNAL_ERROR.getDisplayMsg())).append("[")
                    .append("时间：").append((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())).append("]");
        } else {
            text.append(msg);
        }
        if (StringUtils.isNotBlank(code)) {
            text.append("(").append(code).append(")");
        }
        dto.setMessage(text.toString());
//        if (!prod) {
        dto.setData(details);
//        }
        return dto;
    }

    /**
     * Description: 异常的stacktrace和message将在非生产环境中显示出来
     *
     * @param e
     * @return
     */
    public static ResultDto <String> toCommonError(BaseException e) {
        DisplayableError error = e.getError();
        String msg = StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : (error != null ? MessageUtil.getMessage(error.getDisplayMsg()) : e.getMessage());
        return toCommonError(error != null ? error.getErrorCode() : null, msg, ExceptionUtils.getStackTrace(e));
    }

    /**
     * Description: 异常的stacktrace和message将在非生产环境中显示出来
     *
     * @param e
     * @return
     */
    public static ResultDto <String> toCommonError(Exception e) {
        return toCommonError(null, null, ExceptionUtils.getStackTrace(e));
    }

    /**
     * Description: 传入的msg将显示出来
     *
     * @param msg
     * @return
     */
    public static ResultDto <String> toCommonError(String msg) {
        return toCommonError(null, msg, null);
    }

    public static ResultDto <String> toBizError(String msg, Exception e) {
        ResultDto <String> dto = new ResultDto <String>();
        dto.setCode(ResultCode.BUSINESS_ERROR);
        dto.setMessage(msg == null ? e.getMessage() : msg);
        return dto;
    }

    public static <T> ResultDto <T> toUnauthorized(String msg) {
        ResultDto <T> dto = new ResultDto <T>();
        dto.setCode(ResultCode.UNAUTHORIZED);
        dto.setMessage(msg);
        dto.setData(null);
        return dto;
    }

    public static ResultDto <String> toBizError(String code, String msg, Exception e) {
        ResultDto <String> dto = new ResultDto <String>();
        dto.setCode(StringUtils.isNotEmpty(code) ? code : ResultCode.BUSINESS_ERROR);
        dto.setMessage(msg == null ? e.getMessage() : msg);
        return dto;
    }


    public static <T> ResultDto <T> toApiSuccess(T data) {
        ResultDto <T> dto = new ResultDto <T>();
        dto.setCode("1");
        dto.setMessage(null);
        dto.setData(data);
        return dto;
    }

    public static <T> ResultDto <T> toApiSuccess(String message, T data) {
        ResultDto <T> dto = new ResultDto <T>();
        dto.setCode("1");
        dto.setMessage(message);
        dto.setData(data);
        return dto;
    }


    public static <T> ResultDto <T> toApiError(String code, String message) {
        ResultDto <T> dto = new ResultDto <T>();
        dto.setCode(code);
        dto.setMessage(message);
        return dto;
    }

    //2019年11月20日22:51:23 邹郎  接口异常返回错误对象
    public static <T> ResultDto <T> toApiError(String code, String message, T data) {
        ResultDto <T> dto = new ResultDto <T>();
        dto.setMessage(message);
        dto.setCode(code);
        dto.setData(data);
        return dto;
    }

    public static <T> ResultDto <T> toApiError(String code, String message, Exception e) {
        ResultDto <T> dto = new ResultDto <T>();
        if (e instanceof BizServiceException) {
            BizServiceException bisE = ((BizServiceException) e);
            dto.setCode(bisE.getError().getErrorCode());
            dto.setMessage(bisE.getMessage());
        } else {
            return toApiError(code, message);
        }
        return dto;
    }
}
