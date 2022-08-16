package com.example.testproject1.model.dto.staff;

import com.example.testproject1.model.staff.JobTittle;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для десериализация JobTittlle из XML
 *
 * @author smigranov
 */
@XmlRootElement
public class JobTittleListDTO {
    /**
     * List Job
     */
    @XmlElementWrapper(name = "jobList")
    @XmlElement(name = "job")
    private final List<JobTittle> jobList = new ArrayList<>();

    /**
     * Метод получения списка подразделений
     */
    @JsonProperty("list")
    public List<JobTittle> getJobList() {
        return jobList;
    }
}
