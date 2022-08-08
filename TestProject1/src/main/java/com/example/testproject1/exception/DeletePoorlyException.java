package com.example.testproject1.exception;

/**
 * Класс исключений выдаваемое при неудачном удалении записи.
 *
 * @author smigranov
 */
public class DeletePoorlyException extends RuntimeException {

    public DeletePoorlyException() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Ошибка удаления";
    }
}
