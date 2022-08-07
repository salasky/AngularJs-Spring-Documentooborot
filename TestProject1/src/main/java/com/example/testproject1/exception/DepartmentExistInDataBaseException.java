package com.example.testproject1.exception;

import java.text.MessageFormat;

public class DepartmentExistInDataBaseException extends Exception {
    private String messages;

    public DepartmentExistInDataBaseException(String message) {
        super(message);
        this.messages = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MessageFormat.format("Department c id {0} уже существует", messages);
    }
}