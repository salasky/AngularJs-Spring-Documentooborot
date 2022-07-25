package com.example.testproject1.model.person;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.text.MessageFormat;
import java.util.UUID;

/**
 * Класс должностей
 *
 * @author smigranov
 */
@XmlRootElement
@XmlType(name = "jobTittle",propOrder = {"uuid","jobTittleName"})
public class JobTittle {
    /**
     * Идентификатор должности
     */
    private UUID uuid;
    /**
     * Наименование должности
     */
    private String jobTittleName;

    public JobTittle(UUID uuid, String jobTittleName) {
        this.uuid = uuid;
        this.jobTittleName = jobTittleName;
    }

    public JobTittle() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getJobTittleName() {
        return jobTittleName;
    }

    public void setJobTittleName(String jobTittleName) {
        this.jobTittleName = jobTittleName;
    }

    @Override
    public String toString() {
        Object[] jobTittleArgs = {uuid,jobTittleName};
        MessageFormat form = new MessageFormat(
                "id {0}, jobTittleName= {1}");
        return form.format(jobTittleArgs);
    }
}
