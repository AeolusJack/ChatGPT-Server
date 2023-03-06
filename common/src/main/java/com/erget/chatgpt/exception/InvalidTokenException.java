package com.erget.chatgpt.exception;

/**
 * token过期或无效异常
 */
public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String message, Throwable e) {
        super(message, e);
    }
}
