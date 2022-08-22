package com.example.testproject1.controller.advice;

import com.example.testproject1.model.utility.RestErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс AdviceController для перехвата исключений
 *
 * @author smigranov
 */
@RestControllerAdvice
public class RestResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
    /**
     * Метод перехвата Exception
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestErrorMessage handleAccessException(Exception ex) {
        LOGGER.error(ex.getMessage());
        return new RestErrorMessage(ex.getMessage());
    }
}