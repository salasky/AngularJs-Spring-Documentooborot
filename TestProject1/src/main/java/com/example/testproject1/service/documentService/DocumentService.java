package com.example.testproject1.service.documentService;

import com.example.testproject1.exception.DocumentExistsException;
import com.example.testproject1.model.document.BaseDocument;

/**
 * Интерфейс добавления документов
 */
public interface DocumentService {
    /**
     * Метод добавления документов в базу
     *
     * @param baseDocument передаем объект класса BaseDocument
     * @throws DocumentExistsException если такой объект с данным рег.номером в базе уже существет
     */
    void add(BaseDocument baseDocument) throws DocumentExistsException;
}
