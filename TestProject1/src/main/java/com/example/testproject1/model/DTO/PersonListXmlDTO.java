package com.example.testproject1.model.DTO;

import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Person;

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
public class PersonListXmlDTO {
    /**
     * Хранит список {@link Person}
     */
    @XmlElementWrapper(name = "personList")
    @XmlElement(name = "person")
    private List<Person> list = new ArrayList<>();

    public PersonListXmlDTO() {
    }

    public void setList(List<Person> list) {
        this.list = list;
    }

    /**
     * Метод получения списка Person
     *
     * @return @return {@link List} объектов {@link Person}
     */

    public List<Person> getPersonList() {
        return list;
    }

}
