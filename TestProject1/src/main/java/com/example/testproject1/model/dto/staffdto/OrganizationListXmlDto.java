package com.example.testproject1.model.dto.staffdto;

import com.example.testproject1.model.staff.Organization;

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
public class OrganizationListXmlDto {
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
    public final List<Organization> getOrganizationList() {
        return organizationList;
    }
}
