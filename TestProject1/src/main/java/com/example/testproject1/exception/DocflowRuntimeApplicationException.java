package com.example.testproject1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Класс исключений для ExceptionHandler
 *
 * @author smigranov
 */
public class DocflowRuntimeApplicationException extends Exception {
    /**
     * Информация о исключении
     */
    private String message;


    public DocflowRuntimeApplicationException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return message;
    }
}
