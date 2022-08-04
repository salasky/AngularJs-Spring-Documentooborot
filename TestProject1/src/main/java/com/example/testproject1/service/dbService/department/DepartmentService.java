package com.example.testproject1.service.dbService.department;

import com.example.testproject1.model.staff.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Optional<Department> create(Department department);

    List<Department> getall();

    Optional<Department> getById(String id);

    Optional<Department> update(Department department);

    String deleteAll();

    String deleteById(String id);
}
