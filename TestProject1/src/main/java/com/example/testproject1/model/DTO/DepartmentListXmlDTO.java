package com.example.testproject1.model.DTO;

import com.example.testproject1.model.staff.Department;

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
public class DepartmentListXmlDTO {
    /**
     * List Подразделений
     */
    @XmlElementWrapper(name = "departmentList")
    @XmlElement(name = "department")
    private List<Department> list = new ArrayList<>();

    public DepartmentListXmlDTO() {
    }

    public void setList(List<Department> list) {
        this.list = list;
    }

    /**
     * Метод получения списка подразделений
     *
     * @return {@link List} объектов {@link Department}
     */
    public List<Department> getDepartmentList() {
        return list;
    }

}
