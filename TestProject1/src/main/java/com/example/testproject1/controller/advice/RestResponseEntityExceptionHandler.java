package com.example.testproject1.controller.advice;

import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.staff.Organization;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ DocflowRuntimeApplicationException.class,RuntimeException.class })
    public ResponseEntity<Object> handleAccessDeniedException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "ВСЕ ХЕРНЯ!ПЕРЕДЕЛЫВАЙ!", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}