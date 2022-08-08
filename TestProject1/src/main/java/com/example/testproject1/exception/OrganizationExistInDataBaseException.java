package com.example.testproject1.exception;

import java.text.MessageFormat;

/**
 * Класс исключений выдаваемое при попытке сохранения {@link com.example.testproject1.model.staff.Organization}
 * с уже существующим в базе id
 *
 * @author smigranov
 */
public class OrganizationExistInDataBaseException extends Exception {
    /**
     * id существующей записи
     */
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
