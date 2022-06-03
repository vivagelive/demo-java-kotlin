package com.itechart.demojavakotlin.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handle(EntityNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), exception.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handle(InvalidInputDataException exception) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), exception.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handle(UnprocessableException exception) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), exception.getStatus());
    }
}
