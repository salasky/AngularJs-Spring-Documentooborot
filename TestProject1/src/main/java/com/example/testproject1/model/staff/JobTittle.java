package com.example.testproject1.model.staff;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;

/**
 * Класс должностей
 *
 * @author smigranov
 */
@XmlRootElement
@XmlType(name = "jobTittle", propOrder = {"name"})
public class JobTittle {
    /**
     * Идентификатор должности
     */
    @Column(name ="id")
    private UUID uuid;
    /**
     * Наименование должности
     */
    @Column(name ="name")
    private String name;

    public JobTittle(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public JobTittle() {
    }
    @XmlAttribute(name = "id")
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    @XmlElement(name = "jobTittleName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        Object[] jobTittleArgs = {uuid, name};
        MessageFormat form = new MessageFormat(
                "id {0}, jobTittleName= {1}");
        return form.format(jobTittleArgs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobTittle)) return false;
        JobTittle jobTittle = (JobTittle) o;
        return Objects.equals(uuid, jobTittle.uuid) && Objects.equals(name, jobTittle.name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(uuid, name);
    }
}
