package com.example.testproject1.model.organization;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с JAXB
 *
 * @author smigranov
 */
@XmlRootElement
public class Organizations {
    /**
     * List Организаций
     */
    @XmlElement(name = "organization")
    private List<Organization> list=new ArrayList<>();

    /**
     * Метод получения списка организаций
     * @return
     */
    public List<Organization> getOrganizationList() {
        return list;
    }

    public void setList(List<Organization> list) {
        this.list = list;
    }

    /**
     * Метод добавления организаций в list
     * @param organization
     * @return
     */
    public boolean add(Organization organization){
        return list.add(organization);
    }
}
