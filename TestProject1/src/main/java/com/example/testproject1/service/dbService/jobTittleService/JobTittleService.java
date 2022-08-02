package com.example.testproject1.service.dbService.jobTittleService;

import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;

import java.util.List;
import java.util.Optional;

public interface JobTittleService {
    Optional<JobTittle> create(JobTittle jobTittle);
    List<JobTittle> getall();
    Optional<JobTittle> getById(String id);
    Optional<JobTittle> update(JobTittle jobTittle);
    String deleteAll();
    String deleteById(String id);
}