package com.bamboovir.muresearchboost.app.exception;

public class  CustomSecurityException extends RuntimeException {

    private static final long serialVersionUID = -7806029002430564887L;

    private String message;

    public CustomSecurityException() {
    }

    public CustomSecurityException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
