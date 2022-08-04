package com.example.testproject1.dao.jobtittle;

import com.example.testproject1.model.staff.JobTittle;

import java.util.List;
import java.util.Optional;

public interface JobTittleRepository {
    List<JobTittle> getAll();

    Optional<JobTittle> getById(String uuid);

    Integer create(JobTittle jobTittle);

    Integer update(JobTittle jobTittle);

    Integer deleteAll();

    Integer deleteById(String id);

    boolean existById(String uuid);
}
