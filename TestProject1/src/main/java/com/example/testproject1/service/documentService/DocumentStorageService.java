package com.example.testproject1.service.documentService;

import com.example.testproject1.model.documents.BaseDocument;

import java.util.List;

/**
 * Интерфейс сохранения объектов BaseDocument созданных с помощью Builder-ов
 *
 * @author smigranov
 */
public interface DocumentStorageService {
    /**
     * Метод получения сохраненных документов
     * @return объект {@link List} содержащий объекты класса {@link BaseDocument}
     */
    List<BaseDocument> getAll();

    /**
     * Метод добавления документов в базу
     * @param baseDocumentList объект класса {@link BaseDocument}
     */
    void addAll(BaseDocument baseDocumentList);
}
