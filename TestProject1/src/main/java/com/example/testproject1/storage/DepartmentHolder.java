package com.example.testproject1.storage;

import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Person;

import java.util.List;

/**
 * Интерфейс хранения Department
 *
 * @author smigranov
 */
public interface DepartmentHolder {
    /**
     * Метод получения сохраненных department
     *
     * @return
     */
    List<Department> getDepartmentList();
}
