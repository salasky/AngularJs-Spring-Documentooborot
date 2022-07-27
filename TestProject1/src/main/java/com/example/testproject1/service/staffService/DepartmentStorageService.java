package com.example.testproject1.service.staffService;

import com.example.testproject1.model.staff.Department;

import java.util.List;

/**
 * Интерфейс хранения Department
 *
 * @author smigranov
 */
public interface DepartmentStorageService {
    /**
     * Метод получения сохраненных department
     *
     * @return объект {@link List} содержащий объекты класса {@link Department}
     */
    List<Department> getDepartmentList();
}
