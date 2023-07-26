package com.resume.exception;

public class NoticeNotFountException extends RuntimeException{
    public NoticeNotFountException(String message){
        super(message);
    }

    public NoticeNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoticeNotFountException(Throwable cause) {
        super(cause);
    }

    protected NoticeNotFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
