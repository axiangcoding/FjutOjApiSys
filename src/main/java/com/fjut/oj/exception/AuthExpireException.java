package com.fjut.oj.exception;


/**
 * 权限过期异常
 * @author axiang [20190707]
 */
public class AuthExpireException extends RuntimeException {
    public AuthExpireException() {
        super();
    }

    public AuthExpireException(String message) {
        super(message);
    }

    public AuthExpireException(String message, Throwable cause) {

        super(message, cause);
    }

    public AuthExpireException(Throwable cause) {
        super(cause);
    }

    protected AuthExpireException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
