package com.example.testproject1.jaxb;

import com.example.testproject1.model.organization.Organization;

import java.util.List;

/**
 * Интерфейс анмаршализации {@link Organization}
 *
 * @author smigranov
 */
public interface OrganizationJaxbReader {
    /**
     * Метод получения объектов {@link Organization} из xml файла
     * @return
     */
    public List<Organization> getOrganization();
}
