package com.example.testproject1.exception;

import java.text.MessageFormat;

/**
 * Класс исключений выдаваемое при попытке сохранения {@link com.example.testproject1.model.staff.JobTittle}
 * с уже существующим в базе id
 *
 * @author smigranov
 */
public class JobTittleExistInDataBaseException extends Exception {
    /**
     * id существующей записи
     */
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
