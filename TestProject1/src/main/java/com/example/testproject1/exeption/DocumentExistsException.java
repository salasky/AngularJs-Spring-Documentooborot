package com.example.testproject1.exeption;

/**
 * При создании документа с существующим рег.номером выбрасывается исключение DocumentExistsException
 *
 * @author smigranov
 */
public class DocumentExistsException extends Exception {
    /**
     * Рег.номер или идентификатор созданного документа,который уже существует у другого документа
     */
    private Long detail;

    public DocumentExistsException(Long detail, String message) {
        super(message);
        this.detail = detail;
    }

    public DocumentExistsException() {

    }

    @Override
    public String toString() {
        return "DocumentExistsException{" +
                "RegNumber= " + detail + " exist" +
                '}';
    }
}
