package com.example.testproject1.model.staff;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Column(name = "id")
    private UUID id;
    /**
     * Наименование должности
     */
    @NotNull
    @Size(min = 2)
    @Column(name = "name")
    private String name;

    public JobTittle() {
    }

    @XmlAttribute(name = "id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
        Object[] jobTittleArgs = {id, name};
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
        return Objects.equals(id, jobTittle.id) && Objects.equals(name, jobTittle.name);
    }

    public static JobTittle.JobTittleBuilder newBuilder() {
        return new JobTittle().new JobTittleBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /**
     * Внутренний класс Builder
     *
     * @author smigranov
     */
    public class JobTittleBuilder {

        public JobTittleBuilder setUuid(UUID uuid) {
            JobTittle.this.id = uuid;
            return this;
        }

        public JobTittleBuilder setName(String name) {
            JobTittle.this.name = name;
            return this;
        }

        /**
         * Метод build
         *
         * @return Возвращает объект класса {@link JobTittle}
         */
        public JobTittle build() {
            return JobTittle.this;
        }
    }
}
