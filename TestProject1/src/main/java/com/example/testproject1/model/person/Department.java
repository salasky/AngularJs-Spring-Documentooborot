package com.example.testproject1.model.person;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

/**
 * Класс подразделение. Наследуется от {@link Staff}
 *
 * @author smigranov
 */
@XmlRootElement
@XmlType(name = "department",propOrder = {"fullName","shortName","supervisor","contactNumber","organization"})
public class Department extends Staff{
    /**
     * Полное название департамента
     */
    private String fullName;
    /**
     * Короткое название департамента
     */
    private String shortName;
    /**
     * Руководитель департамента
     */
    private String supervisor;
    /**
     * Контактный телефон департамента
     */
    private String contactNumber;
    /**
     * Организация к которой принадлежит подразделение
     */
    private Organization organization;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public void setId(UUID id){
        super.setId(id);
    }

    @XmlAttribute(name = "id")
    public UUID getId(){
        return super.getId();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
