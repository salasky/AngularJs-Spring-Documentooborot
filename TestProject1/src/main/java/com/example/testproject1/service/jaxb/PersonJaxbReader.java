package com.example.testproject1.service.jaxb;

import com.example.testproject1.model.person.Person;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Интерфейс анмаршализации объктов класса {@link Person}
 *
 * @author smigranov
 */
public interface PersonJaxbReader {

    /**
     * Метод получения {@link Person} из xml файла
     * @return
     */
    public List<Person> getPerson();
}
