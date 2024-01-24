package com.example.pharmdemo.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ErrorResponse {
    private final String message;
    private final ZonedDateTime timeStamp;
    private final HttpStatus httpStatus;


    public ErrorResponse(String message,
                         ZonedDateTime timeStamp,
                         HttpStatus httpStatus) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.httpStatus = httpStatus;
    }
}
