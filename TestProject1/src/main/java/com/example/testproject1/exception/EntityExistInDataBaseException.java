package com.example.testproject1.exception;

import java.text.MessageFormat;

/**
 * Класс исключений выдаваемое при попытке сохранения
 * с уже существующим в базе id
 *
 * @author smigranov
 */
public class EntityExistInDataBaseException extends RuntimeException {
    /**
     * id существующего документа
     */
    private String messages;

    public EntityExistInDataBaseException(String message) {
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
