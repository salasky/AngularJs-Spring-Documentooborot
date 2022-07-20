package com.example.testproject1.service.documents;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;

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
    void documentAdd(BaseDocument baseDocument) throws DocumentExistsException;
}
