package com.example.testproject1.model.staff;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * Класс Организации.Наследуется от {@link Staff}
 *
 * @author smigranov
 */
@XmlRootElement
@XmlType(name = "organization", propOrder = {"fullName", "shortName", "supervisor", "contactNumber"})
public class Organization extends Staff {
    /**
     * Полное название организации
     */
    @Column(name = "full_name")
    private String fullName;
    /**
     * Короткое название организации
     */
    @Column(name = "short_name")
    private String shortName;
    /**
     * Руководитель организации
     */
    @Column(name = "supervisor")
    private String supervisor;
    /**
     * Контактный телефон организации
     */
    @Column(name = "contact_number")
    private String contactNumber;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        if (!super.equals(o)) return false;
        Organization that = (Organization) o;
        return Objects.equals(fullName, that.fullName) && Objects.equals(shortName, that.shortName) && Objects.equals(supervisor, that.supervisor) && Objects.equals(contactNumber, that.contactNumber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fullName, shortName, supervisor, contactNumber);
    }

    @Override
    public String toString() {
        Object[] taskArgs = {id, fullName, shortName, supervisor, contactNumber};
        MessageFormat form = new MessageFormat(
                "Organization id= {0} fullName= {1}, shortName= {2}, supervisor= {3}" +
                        ", contactNumber= {4}");
        return form.format(taskArgs);

    }
}
