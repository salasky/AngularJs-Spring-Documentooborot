package com.example.testproject1.storage;

import com.example.testproject1.model.documents.BaseDocument;

import java.util.List;

/**
 * Интерфейс сохранения объектов BaseDocument созданных с помощью Builder-ов
 *
 * @author smigranov
 */
public interface DocumentHolder {
    /**
     * Метод получения сохраненных документов
     * @return
     */
    List<BaseDocument> getAll();

    /**
     * Метод добавления документов в базу
     * @param baseDocumentList
     */
    void addAll(BaseDocument baseDocumentList);
}
