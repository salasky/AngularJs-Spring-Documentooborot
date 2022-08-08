package com.example.testproject1.exception;

import java.text.MessageFormat;

/**
 * Класс исключений выдаваемое при попытке сохранения документов
 * с уже существующим в базе id
 *
 * @author smigranov
 */
public class DocumentExistInDataBaseException extends Exception {
    /**
     * id существующей записи
     */
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