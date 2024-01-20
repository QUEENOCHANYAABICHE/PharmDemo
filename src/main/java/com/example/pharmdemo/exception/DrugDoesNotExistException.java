package com.example.pharmdemo.exception;

public class DrugDoesNotExistException extends RuntimeException{

    public DrugDoesNotExistException(String message){
        super(message);

    }
}
