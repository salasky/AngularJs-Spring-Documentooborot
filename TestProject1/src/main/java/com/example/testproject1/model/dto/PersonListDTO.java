package com.example.testproject1.model.dto;

import com.example.testproject1.model.staff.Person;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с JAXB для маршалинга класса {@link Person}
 *
 * @author smigranov
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonListDTO {
    /**
     * Хранит список {@link Person}
     */
    @XmlElementWrapper(name = "personList")
    @XmlElement(name = "person")
    private final List<Person> personList = new ArrayList<>();

    /**
     * Метод получения списка Person
     *
     * @return @return {@link List} объектов {@link Person}
     */
    @JsonProperty("list")
    public List<Person> getPersonList() {
        return personList;
    }

}
