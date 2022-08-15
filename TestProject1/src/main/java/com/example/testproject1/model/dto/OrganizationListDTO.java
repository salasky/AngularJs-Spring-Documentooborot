package com.example.testproject1.model.dto;

import com.example.testproject1.model.staff.Organization;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с JAXB для маршалинга класса {@link Organization}
 *
 * @author smigranov
 */
@XmlRootElement
public class OrganizationListDTO {
    /**
     * List Организаций
     */
    @XmlElement(name = "organization")
    @XmlElementWrapper(name = "organizationList")
    private List<Organization> organizationList = new ArrayList<>();

    /**
     * Метод получения списка организаций
     *
     * @return {@link List} объектов {@link Organization}
     */
    @JsonProperty("list")
    public final List<Organization> getOrganizationList() {
        return organizationList;
    }
}
