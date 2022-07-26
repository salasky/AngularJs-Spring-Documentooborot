package com.example.testproject1.service.jaxb;

import com.example.testproject1.model.person.Person;

/**
 * Интерфейс анмаршализации объктов класса {@link Person}
 *
 * @author smigranov
 */
public interface JaxbReader {
    /**
     * Метод получения объектов из xml файла
     * @param clazz Класс,который хотим получить
     * @return Возвращает объект класса, который передали
     * @param <T>
     */
     <T> T jaxbXMLToObject(Class<T> clazz);
}
