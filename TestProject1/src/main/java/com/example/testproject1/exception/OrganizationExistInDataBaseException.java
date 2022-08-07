package com.example.testproject1.exception;

import java.text.MessageFormat;

public class OrganizationExistInDataBaseException extends Exception {
    private String messages;

    public OrganizationExistInDataBaseException(String message) {
        super(message);
        this.messages = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MessageFormat.format("Organization c id {0} уже существует", messages);
    }
}
