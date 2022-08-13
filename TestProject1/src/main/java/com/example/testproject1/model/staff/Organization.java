package com.example.testproject1.model.staff;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
    @NotNull
    @Column(name = "full_name")
    private String fullName;
    /**
     * Короткое название организации
     */
    @NotNull
    @Column(name = "short_name")
    private String shortName;
    /**
     * Руководитель организации
     */
    @NotNull
    @Column(name = "supervisor")
    private String supervisor;
    /**
     * Контактные телефоны организации
     */
    @NotNull
    @Column(name = "contact_number")
    private List<String> contactNumber;

    public UUID getId() {
        return super.getId();
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
    public List<String> getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(List<String> contactNumber) {
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

    public static Organization.OrganizationBuilder newBuilder() {
        return new Organization().new OrganizationBuilder();
    }

    /**
     * Внутренний класс Builder
     *
     * @author smigranov
     */
    public class OrganizationBuilder {
        public OrganizationBuilder setId(UUID uuid) {
            Organization.this.id = uuid;
            return this;
        }

        public OrganizationBuilder setFullName(String fullName) {
            Organization.this.fullName = fullName;
            return this;
        }

        public OrganizationBuilder setShortName(String shortName) {
            Organization.this.shortName = shortName;
            return this;
        }

        public OrganizationBuilder setSupervisor(String supervisor) {
            Organization.this.supervisor = supervisor;
            return this;
        }

        public OrganizationBuilder setContactNumber(List<String> contactNumber) {
            Organization.this.contactNumber = contactNumber;
            return this;
        }

        /**
         * Метод build
         *
         * @return Возвращает объект класса {@link Organization}
         */
        public Organization build() {
            return Organization.this;
        }
    }
}
