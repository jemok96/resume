package com.resume.exception;

public class PostNotFountException extends RuntimeException{
    public PostNotFountException(String message){
        super(message);
    }

    public PostNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostNotFountException(Throwable cause) {
        super(cause);
    }

    protected PostNotFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PostNotFountException() {
        super();}
}
