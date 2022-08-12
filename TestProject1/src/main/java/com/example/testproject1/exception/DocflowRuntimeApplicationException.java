package com.example.testproject1.exception;

/**
 * Класс исключений для ExceptionHandler
 *
 * @author smigranov
 */
public class DocflowRuntimeApplicationException extends RuntimeException {

    public DocflowRuntimeApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocflowRuntimeApplicationException(String message) {
        super(message);
    }
}
