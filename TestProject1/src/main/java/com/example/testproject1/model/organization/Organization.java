package com.example.testproject1.model.organization;

import com.example.testproject1.model.Staff;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

/**
 * Класс Организации.Наследуется от {@link Staff}
 *
 * @author smigranov
 */
@XmlRootElement
@XmlType(name = "organization",propOrder = {"organizationFullName", "organizationShortName", "organizationSupervisor","organizationContactNumber"})
public class Organization extends Staff{
    /**
     * Полное название организации
     */
    private String OrganizationFullName;
    /**
     * Короткое название организации
     */
    private String OrganizationShortName;
    /**
     * Руководитель организации
     */
    private String OrganizationSupervisor;
    /**
     * Контактный телефон организации
     */
    private String OrganizationContactNumber;

    public void setId(UUID id){
        super.setId(id);
    }
    @XmlAttribute(name = "id")
    public UUID getId(){
        return super.getId();
    }
    @XmlElement(name = "organizationFullName")
    public String getOrganizationFullName() {
        return OrganizationFullName;
    }
    @XmlElement(name = "organizationShortName")
    public String getOrganizationShortName() {
        return OrganizationShortName;
    }
    @XmlElement(name = "organizationSupervisor")
    public String getOrganizationSupervisor() {
        return OrganizationSupervisor;
    }
    @XmlElement(name = "organizationContactNumber")
    public String getOrganizationContactNumber() {
        return OrganizationContactNumber;
    }

    public void setOrganizationFullName(String organizationFullName) {
        OrganizationFullName = organizationFullName;
    }

    public void setOrganizationShortName(String organizationShortName) {
        OrganizationShortName = organizationShortName;
    }

    public void setOrganizationSupervisor(String organizationSupervisor) {
        OrganizationSupervisor = organizationSupervisor;
    }

    public void setOrganizationContactNumber(String organizationContactNumber) {
        OrganizationContactNumber = organizationContactNumber;
    }

    @Override
    public String toString() {
        return "Organization{" +
                ", id=" + id +" " +
                "OrganizationFullName='" + OrganizationFullName + '\'' +
                ", OrganizationShortName='" + OrganizationShortName + '\'' +
                ", OrganizationSupervisor='" + OrganizationSupervisor + '\'' +
                ", OrganizationContactNumber='" + OrganizationContactNumber + '\'' +
                '}';
    }
}
