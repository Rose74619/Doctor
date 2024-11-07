package com.example.exception;

import com.example.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalResourceExceptionHandling {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> GlobalExceptionErrorHandler(ResourceNotFound err){
        ErrorDetails errorDetails=new ErrorDetails(err.getMessage(), new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> ExceptionErrorHandler(Exception err){
        ErrorDetails errorDetails=new ErrorDetails(err.getMessage(), new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
}}
