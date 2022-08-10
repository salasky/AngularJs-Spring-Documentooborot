package com.example.testproject1.exception;

import java.text.MessageFormat;

/**
 * Исключение выбрасываемое при удалении
 *
 * @author smigranov
 */
public class DeleteByIdException extends Exception {
    /**
     * Сообщение о деталях исключения
     */
    private String messages;

    public DeleteByIdException(String message) {
        super(message);
        this.messages = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MessageFormat.format("Ошибка удаления по id {0}", messages);
    }
}
