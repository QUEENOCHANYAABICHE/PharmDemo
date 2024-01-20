package com.example.pharmdemo.exception;

public class DrugAlreadyExistException extends RuntimeException {
    public DrugAlreadyExistException(String message) {
        super(message);
    }
}
