package com.example.testproject1.service.dbservice;

import com.example.testproject1.model.staff.Department;

import java.util.List;
import java.util.Optional;

public interface CrudService <T> {
    Optional<T> create(T object);

    List<T> getall();

    Optional<T> getById(String id);

    Optional<T> update(T object);

    void deleteAll();

    void deleteById(String id);
}
