package com.example.testproject1.exception;

import java.text.MessageFormat;

/**
 * При создании документа с существующим рег.номером выбрасывается исключение DocumentExistsException
 *
 * @author smigranov
 */
public class DocumentExistsByRegistrationNumberException extends Exception {
    /**
     * Рег.номер или идентификатор созданного документа,который уже существует у другого документа
     */
    private Long detail;

    public DocumentExistsByRegistrationNumberException(Long detail, String message) {
        super(message);
        this.detail = detail;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MessageFormat.format("DocumentExistsException: RegNumber {0} exist", detail);
    }
}
