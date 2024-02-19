package com.example.pharmdemo.exceptions;

public class UserDisabledException extends RuntimeException{

    public UserDisabledException(String message) {
        super(message);
    }

    public UserDisabledException(String message, Throwable cause) {
        super(message, cause);
    }
}
