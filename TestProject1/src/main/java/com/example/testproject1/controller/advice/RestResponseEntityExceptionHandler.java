package com.example.testproject1.controller.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс AdviceController для перехвата исключений
 *
 * @author smigranov
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {
    /**
     * Метод перехвата RuntimeException
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleAccessRuntimeException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "RuntimeException! " + ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Метод перехвата Exception
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAccessException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                " Exception! " + ex.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    /**
     * Метод перехвата MethodArgumentNotValidException (Ошибки при валидации)
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}