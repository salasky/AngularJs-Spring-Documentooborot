package com.example.testproject1.exception;

import java.text.MessageFormat;

/**
 * Класс исключений выдаваемое при попытке сохранения {@link com.example.testproject1.model.staff.Department}
 * с уже существующим в базе id
 *
 * @author smigranov
 */
public class DepartmentExistInDataBaseException extends Exception {
    /**
     * id существующей записи
     */
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