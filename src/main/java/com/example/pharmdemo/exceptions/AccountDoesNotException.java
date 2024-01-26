package com.example.pharmdemo.exceptions;

public class AccountDoesNotException extends RuntimeException{

    public AccountDoesNotException(String message) {
        super(message);
    }

    public AccountDoesNotException(String message, Throwable cause) {
        super(message, cause);
    }
}
