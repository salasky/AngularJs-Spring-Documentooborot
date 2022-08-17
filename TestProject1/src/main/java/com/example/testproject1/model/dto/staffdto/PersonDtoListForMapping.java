package com.example.testproject1.model.dto.staffdto;

import com.example.testproject1.model.staff.Person;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с JAXB для маршалинга класса {@link Person}
 *
 * @author smigranov
 */
public class PersonDtoListForMapping {
    /**
     * Хранит список {@link Person}
     */
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
