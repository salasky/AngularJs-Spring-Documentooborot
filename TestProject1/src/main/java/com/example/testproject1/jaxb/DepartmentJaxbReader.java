package com.example.testproject1.jaxb;

import com.example.testproject1.model.department.Department;
import com.example.testproject1.model.person.Person;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Интерфейс анмаршализации объктов класса {@link Department}
 *
 * @author smigranov
 */
public interface DepartmentJaxbReader {
    /**
     * Метод получения объектов класса {@link Department} из xml файла
     * @return
     */
    public List<Department> getDepartment();
}
