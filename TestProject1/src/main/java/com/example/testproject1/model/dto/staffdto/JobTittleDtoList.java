package com.example.testproject1.model.dto.staffdto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class JobTittleDtoList {
    /**
     * List Job
     */
    private final List<JobTittleDTO> jobList = new ArrayList<>();

    /**
     * Метод получения списка подразделений
     */
    @JsonProperty("list")
    public List<JobTittleDTO> getJobList() {
        return jobList;
    }
}
