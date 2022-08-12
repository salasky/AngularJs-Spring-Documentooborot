package com.example.testproject1.model.dto;

import com.example.testproject1.model.staff.JobTittle;

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
public class JobTittlleXmlDTO {
    /**
     * List Job
     */
    @XmlElementWrapper(name = "jobList")
    @XmlElement(name = "job")
    private final List<JobTittle> jobTittleList = new ArrayList<>();

    /**
     * Метод получения списка подразделений
     */
    public List<JobTittle> getDepartmentList() {
        return jobTittleList;
    }
}
