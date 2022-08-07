package com.example.testproject1.exception;

import java.text.MessageFormat;

public class JobTittleExistInDataBaseException extends Exception {
    private String messages;

    public JobTittleExistInDataBaseException(String message) {
        super(message);
        this.messages = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MessageFormat.format("JobTittle c id {0} уже существует", messages);
    }
}
