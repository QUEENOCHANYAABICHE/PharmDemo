package com.example.pharmdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {DrugAlreadyExistsException.class})
    public ResponseEntity<HashMap<String,Object>> handleDrugAlreadyExistsException(DrugAlreadyExistsException e){
        return  handle (e.getMessage(),HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT);
    }
    private ResponseEntity<HashMap<String,Object>> handle(String message,int statusCode,HttpStatus httpStatus){
        HashMap<String,Object> errors = new HashMap<>();
        errors.put("message",message);
        errors.put("status",statusCode);
        errors.put("date", formatLocalDateTime(LocalDateTime.now()));
        return new ResponseEntity<>(errors,httpStatus);
    }

    private String formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    @ExceptionHandler(value ={DrugDoesNotExistException.class})
    public ResponseEntity<HashMap<String,Object>> handleDrugDoesNotExistException(DrugDoesNotExistException e){
        return  handle (e.getMessage(),HttpStatus.CONFLICT.value(),HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<HashMap<String,Object>> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        return  handle (e.getMessage(),HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = InvalidCredentialsException.class)
    public ResponseEntity<HashMap<String,Object>> handleInvalidCredentialsException(InvalidCredentialsException e){
        return  handle (e.getMessage(),HttpStatus.CONFLICT.value(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<HashMap<String,Object>> handleUserNotFoundException(UsernameNotFoundException e){
        return  handle (e.getMessage(),HttpStatus.CONFLICT.value(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<HashMap<String,Object>> handleUserNotFoundException(UserNotFoundException e){
        return  handle (e.getMessage(),HttpStatus.CONFLICT.value(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InputValidationException.class)
    public ResponseEntity<HashMap<String,Object>>  handleInputValidationException(InputValidationException e){
        return  handle (e.getMessage(),HttpStatus.CONFLICT.value(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ExpiredOtpException.class)
    public ResponseEntity<HashMap<String,Object>>  handleExpiredOtpException(ExpiredOtpException e){
        return  handle (e.getMessage(),HttpStatus.CONFLICT.value(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OtpException.class)
    public ResponseEntity<HashMap<String,Object>>  handleOtpException(OtpException e){
        return  handle (e.getMessage(),HttpStatus.CONFLICT.value(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserDisabledException.class)
    public ResponseEntity<HashMap<String,Object>>  handleUserDisabledException(UserDisabledException e){
        return  handle (e.getMessage(),HttpStatus.CONFLICT.value(),HttpStatus.BAD_REQUEST);
    }


}
