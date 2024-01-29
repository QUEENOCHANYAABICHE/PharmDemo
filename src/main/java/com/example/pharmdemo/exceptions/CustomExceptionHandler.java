package com.example.pharmdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {DrugAlreadyExistsException.class})
    public ResponseEntity<Object> handleDrugAlreadyExistsException(DrugAlreadyExistsException e){
        //Create payload containing exception details
        //Return the actual response entity
        return handleException(e, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value ={DrugDoesNotExistException.class})
    public ResponseEntity<Object> handleDrugDoesNotExistException(DrugDoesNotExistException e){
        return handleException(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException e){
      return handleException(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UsernameNotFoundException e){
        return handleException(e, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> handleException(RuntimeException e, HttpStatus httpStatus){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        new ErrorResponse(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")),
                badRequest
        );
        return new ResponseEntity<>(e, httpStatus);

    }

}
