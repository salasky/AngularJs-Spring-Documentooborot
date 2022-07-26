package com.example.testproject1.storage;

import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;

import java.util.List;

/**
 * Интерфейс получения {@link com.example.testproject1.model.staff.Organization}
 *
 * @author smigranov
 */
public interface OrganizationHolder {
    /**
     * Метод получения сохраненных organization
     *
     * @return
     */
    List<Organization> getOrganizationList();
}
