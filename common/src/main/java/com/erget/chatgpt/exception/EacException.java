package com.erget.chatgpt.exception;

/**
 * @author wangqingan
 * @version 08/02/2018 10:07 PM
 */
public class EacException extends RuntimeException {

    public EacException(String message) {
        super(message);
    }

    public EacException(String message, Throwable e) {
        super(message, e);
    }
}
