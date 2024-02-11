package com.example.pharmdemo.exceptions;

public class AccountDoesNotExistException extends RuntimeException{

    public AccountDoesNotExistException(String message) {
        super(message);
    }

    public AccountDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
