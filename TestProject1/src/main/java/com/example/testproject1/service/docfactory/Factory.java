package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.document.BaseDocument;

/**
 * Интерфейс фабрик для объектов классы которых унаследованы от {@link BaseDocument}
 *
 * @param <T> реализация фабрик
 *
 * @author smigranov
 */
public interface Factory<T> {
    /**
     * Метод создания {@link BaseDocument}
     *
     * @return объект {@link BaseDocument}
     */
    T create();

}
