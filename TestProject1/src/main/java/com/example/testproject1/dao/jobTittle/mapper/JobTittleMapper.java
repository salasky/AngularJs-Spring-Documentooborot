package com.example.testproject1.dao.jobTittle.mapper;

import com.example.testproject1.model.staff.JobTittle;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
@Component
public class JobTittleMapper implements RowMapper<JobTittle> {
    @Override
    public JobTittle mapRow(ResultSet rs, int rowNum) throws SQLException {
        JobTittle jobTittle=new JobTittle();
        jobTittle.setUuid(UUID.fromString(rs.getString("id")));
        jobTittle.setName(rs.getString("name"));
        return jobTittle;
    }
}
