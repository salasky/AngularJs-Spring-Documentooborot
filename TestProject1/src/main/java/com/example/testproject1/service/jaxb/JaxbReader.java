package com.example.testproject1.service.jaxb;

import com.example.testproject1.model.staff.Person;

/**
 * Интерфейс анмаршализации объктов класса {@link Person}
 * Для unmarshalling-а xml файлов любого типа
 *
 * @author smigranov
 */
public interface JaxbReader {
    /**
     * Метод получения объектов из xml файла
     *
     * @param <T> класс для анмаршализации
     * @return Возвращает объект класса, который передали
     */
    <T> T jaxbXMLToObject(String fileName);
}
