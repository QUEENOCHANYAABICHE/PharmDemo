package com.example.pharmdemo.exceptions;

public class DrugAlreadyExistsException extends RuntimeException{
    public DrugAlreadyExistsException(String message) {
        super(message);
    }

    public DrugAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
