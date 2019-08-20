package com.fjut.oj.exception;

/**
 * @author axiang [2019/7/12] 请求者请求私密内容异常
 */

public class NotOwnerException extends RuntimeException {

    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public NotOwnerException() {
        super();
    }

    public NotOwnerException(String message, String ip){
        super(message);
        this.ip = ip;
    }

    public NotOwnerException(String message) {
        super(message);
    }

    public NotOwnerException(String message, Throwable cause) {

        super(message, cause);
    }

    public NotOwnerException(Throwable cause) {
        super(cause);
    }

    protected NotOwnerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
