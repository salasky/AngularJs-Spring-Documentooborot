package com.example.testproject1.exception;

public class DeletePoorlyException extends Exception {

    public DeletePoorlyException() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return  "Ошибка удаления";
    }
}
