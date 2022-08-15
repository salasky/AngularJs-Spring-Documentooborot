package com.example.testproject1.model.dto;

import com.example.testproject1.model.staff.Department;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с JAXB для маршалинга класса {@link Department}
 *
 * @author smigranov
 */
@XmlRootElement
public class DepartmentListDTO {
    /**
     * List Подразделений
     */
    @XmlElementWrapper(name = "departmentList")
    @XmlElement(name = "department")
    private final List<Department> departmentList = new ArrayList<>();

    /**
     * Метод получения списка подразделений
     *
     * @return {@link List} объектов {@link Department}
     */
    @JsonProperty("list")
    public List<Department> getDepartmentList() {
        return departmentList;
    }

}
