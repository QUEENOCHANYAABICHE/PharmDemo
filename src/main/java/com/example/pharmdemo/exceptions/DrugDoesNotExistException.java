package com.example.pharmdemo.exceptions;

public class DrugDoesNotExistException extends RuntimeException{
    public DrugDoesNotExistException(String message) {
        super(message);
    }

    public DrugDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
