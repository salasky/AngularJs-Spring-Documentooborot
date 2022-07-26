package com.example.testproject1.model.staff;

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
@XmlType(name = "organization",propOrder = {"fullName","shortName","supervisor","contactNumber"})
public class Organization extends Staff{
    /**
     * Полное название организации
     */
    private String fullName;
    /**
     * Короткое название организации
     */
    private String shortName;
    /**
     * Руководитель организации
     */
    private String supervisor;
    /**
     * Контактный телефон организации
     */
    private String contactNumber;
    @XmlAttribute(name = "id")
    public UUID getId(){
        return super.getId();
    }
    public void setId(UUID id){
        super.setId(id);
    }

    @XmlElement(name = "fullName")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @XmlElement(name = "shortName")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    @XmlElement(name = "supervisor")
    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
    @XmlElement(name = "contactNumber")
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
