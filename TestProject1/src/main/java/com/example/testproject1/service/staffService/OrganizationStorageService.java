package com.example.testproject1.service.staffService;

import com.example.testproject1.model.staff.Organization;

import java.util.List;

/**
 * Интерфейс получения {@link com.example.testproject1.model.staff.Organization}
 *
 * @author smigranov
 */
public interface OrganizationStorageService {
    /**
     * Метод получения сохраненных organization
     *
     * @return объект {@link List} содержащий объекты класса {@link Organization}
     */
    List<Organization> getOrganizationList();
}
