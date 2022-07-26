package com.example.testproject1.model.DTO;

import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с JAXB для маршалинга класса {@link Organization}
 *
 * @author smigranov
 */
@XmlRootElement
public class OrganizationListXmlDTO {
    /**
     * List Организаций
     */
    @XmlElement(name = "organization")
    private List<Organization> list=new ArrayList<>();
    /**
     * Метод получения списка организаций
     * @return {@link List} объектов {@link Organization}
     */
    public List<Organization> getOrganizationList() {
        return list;
    }

    public void setList(List<Organization> list) {
        this.list = list;
    }

}
