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
     * @return
     */
     <T> T jaxbXMLToObject(String xmlData, Class<T> clazz);


}
