package com.example.testproject1.service.documents;

/**
 * Интерфейс генерации документов
 *
 * @author smigranov
 */
public interface GenerateDocument {
    /**
     * Метод генерации документов трех типов
     *
     * @param task     Количество генерируемых документов
     */
    void generateDocument(Integer task);

}
