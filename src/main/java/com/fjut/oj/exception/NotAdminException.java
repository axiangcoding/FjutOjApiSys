package com.fjut.oj.exception;

/**
 * @author axiang [20190707] 不是管理员异常
 */
public class NotAdminException extends RuntimeException {
    public NotAdminException() {
        super();
    }

    public NotAdminException(String message) {
        super(message);
    }

    public NotAdminException(String message, Throwable cause) {

        super(message, cause);
    }

    public NotAdminException(Throwable cause) {
        super(cause);
    }

    protected NotAdminException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
