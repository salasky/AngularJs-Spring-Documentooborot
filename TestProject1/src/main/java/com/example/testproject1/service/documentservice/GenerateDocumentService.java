package com.example.testproject1.service.documentservice;

/**
 * Интерфейс генерации документов
 *
 * @author smigranov
 */
public interface GenerateDocumentService {
    /**
     * Метод генерации документов трех типов
     *
     * @param count Количество генерируемых документов каждого типа
     */
    void createAndSaveDocument(Integer count);
}
