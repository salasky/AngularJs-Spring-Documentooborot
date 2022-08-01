package com.example.testproject1.dao.department;

import com.example.testproject1.model.staff.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {
    List<Department> getAll();

    Optional<Department> getById(String id);

    Integer create(Department department);

    Integer update(Department department);

    Integer deleteAll();

    Integer deleteById(String id);
}
