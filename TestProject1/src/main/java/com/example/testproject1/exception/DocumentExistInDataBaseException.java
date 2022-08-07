package com.example.testproject1.exception;

import java.text.MessageFormat;

public class DocumentExistInDataBaseException extends Exception {
    private String messages;

    public DocumentExistInDataBaseException(String message) {
        super(message);
        this.messages = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MessageFormat.format("Document c id {0} уже существует", messages);
    }
}