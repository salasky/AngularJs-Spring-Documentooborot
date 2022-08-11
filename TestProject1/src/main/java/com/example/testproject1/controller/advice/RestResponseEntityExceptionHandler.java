package com.example.testproject1.controller.advice;

import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {
    @ExceptionHandler({RuntimeException.class })
    public ResponseEntity<Object> handleAccessRuntimeException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "ВСЕ ХЕРНЯ!ПЕРЕДЕЛЫВАЙ RuntimeException! "+ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ DocflowRuntimeApplicationException.class })
    public ResponseEntity<Object> handleAccessException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "ВСЕ ХЕРНЯ!ПЕРЕДЕЛЫВАЙ Exception! "+ex.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}