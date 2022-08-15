package com.example.testproject1.model.dto.staff;

import com.example.testproject1.model.staff.Person;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с JAXB для маршалинга класса {@link Person}
 *
 * @author smigranov
 */
public class PersonDtoListCRUD {
    /**
     * Хранит список {@link Person}
     */
    @XmlElementWrapper(name = "personList")
    @XmlElement(name = "person")
    private final List<PersonDTO> personList = new ArrayList<>();

    /**
     * Метод получения списка Person
     *
     * @return @return {@link List} объектов {@link Person}
     */
    @JsonProperty("list")
    public List<PersonDTO> getPersonList() {
        return personList;
    }
}
