package com.resume.exception;

public class PostAccessDenyException extends RuntimeException{
    public PostAccessDenyException() {
    }

    public PostAccessDenyException(String message) {
        super(message);
    }

    public PostAccessDenyException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostAccessDenyException(Throwable cause) {
        super(cause);
    }

    public PostAccessDenyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
