package com.example.testproject1.exception;

import java.text.MessageFormat;

public class BaseDocumentExistInDb extends Exception {
    private String messages;

    public BaseDocumentExistInDb(String message) {
        super(message);
        this.messages = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MessageFormat.format("BaseDocument c id {0} уже существует", messages);
    }
}
