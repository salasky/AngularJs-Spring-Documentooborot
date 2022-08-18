package com.example.testproject1.model.dto.staffdto;

import com.example.testproject1.model.staff.Department;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * List DTO класс {@link Department}
 *
 * @author smigranov
 */
public class DepartmentDtoList {
    /**
     * List Подразделений
     */
    private final List<DepartmentDTO> departmentList = new ArrayList<>();

    /**
     * Метод получения списка подразделений
     *
     * @return {@link List} объектов {@link Department}
     */
    @JsonProperty("list")
    public List<DepartmentDTO> getDepartmentList() {
        return departmentList;
    }
}
