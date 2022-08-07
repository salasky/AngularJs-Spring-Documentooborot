package com.example.testproject1.exception;

import java.text.MessageFormat;

/**
 * Класс исключений выдаваемое при попытке сохранения {@link com.example.testproject1.model.document.BaseDocument}
 * с уже существующим в базе id
 *
 * @author smigranov
 */
public class BaseDocumentExistInDataBaseException extends Exception {
    /**
     * id существующего документа
     */
    private String messages;

    public BaseDocumentExistInDataBaseException(String message) {
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
