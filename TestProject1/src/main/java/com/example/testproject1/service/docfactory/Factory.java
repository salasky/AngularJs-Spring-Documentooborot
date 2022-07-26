package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.documents.BaseDocument;

/**
 * Интерфейс фабрик
 * @param <T>
 */
public interface Factory<T> {
    /**
     * Метод создания {@link BaseDocument}
     *
     * @return объект {@link BaseDocument}
     */
    T create();

}
