package com.example.testproject1.exception;

import java.text.MessageFormat;

/**
 * Класс исключений для ExceptionHendler
 *
 * @author smigranov
 */
public class DocflowRuntimeApplicationException extends Exception {
    /**
     * Информация о исключении
     */
    private String message;

    public DocflowRuntimeApplicationException(String message) {
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
