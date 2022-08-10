package com.example.testproject1.exception;

import java.text.MessageFormat;

public class UpdateException extends Exception{
    /**
     * сообщение о деталях исключения
     */
    private String messages;

    public UpdateException(String message) {
        super(message);
        this.messages = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MessageFormat.format("{0}", messages);
    }
}
