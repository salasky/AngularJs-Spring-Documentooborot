package com.example.testproject1.exception;

import java.text.MessageFormat;

public class DepartmentExistInDb extends Exception {
    private String messages;
    public DepartmentExistInDb(String message) {
        super(message);
        this.messages=message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MessageFormat.format("Department c id {0} уже существует",messages);
    }
}