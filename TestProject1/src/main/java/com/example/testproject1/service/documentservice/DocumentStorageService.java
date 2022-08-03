package com.example.testproject1.service.documentservice;

import com.example.testproject1.model.document.BaseDocument;

import java.util.List;

/**
 * Интерфейс сохранения объектов BaseDocument созданных с помощью Builder-ов
 *
 * @author smigranov
 */
public interface DocumentStorageService {
    /**
     * Метод получения сохраненных документов
     *
     * @return объект {@link List} содержащий объекты класса {@link BaseDocument}
     */
    List<BaseDocument> getAll();

    /**
     * Метод добавления документов в базу
     *
     * @param baseDocumentList объект класса {@link BaseDocument}
     */
    void addDocument(BaseDocument baseDocumentList);

    /**
     * Метод проверки существования документа с указанным рег.номером
     *
     * @param regNumber регистрационный номер базового документа
     * @return возращает true если есть документ с данным рег.номером, иначе false
     */
    boolean existByRegNumber(Long regNumber);
}
