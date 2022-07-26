package com.example.testproject1.storage;

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
