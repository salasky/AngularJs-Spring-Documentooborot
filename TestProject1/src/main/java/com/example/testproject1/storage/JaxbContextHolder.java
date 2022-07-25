package com.example.testproject1.storage;

import com.example.testproject1.model.person.Department;
import com.example.testproject1.model.person.Persons;

import javax.xml.bind.JAXBContext;

/**
 * Интерфейс для получения контекста Jaxb
 *
 * @author smigranov
 */
public interface JaxbContextHolder {
    /**
     * Метод получения контекста jaxb
     * @return
     */
    public JAXBContext getContext();
}
