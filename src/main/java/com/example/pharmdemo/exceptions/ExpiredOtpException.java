package com.example.pharmdemo.exceptions;

public class ExpiredOtpException extends RuntimeException{

    public ExpiredOtpException(String message) {
        super(message);
    }

    public ExpiredOtpException(String message, Throwable cause) {
        super(message, cause);
    }
}
