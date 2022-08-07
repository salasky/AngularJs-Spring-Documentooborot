package com.example.testproject1.exception;

import java.text.MessageFormat;

public class PersonExistInDataBaseException extends Exception {
    private String messages;

    public PersonExistInDataBaseException(String message) {
        super(message);
        this.messages = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MessageFormat.format("Person c id {0} уже существует", messages);
    }
}