package com.example.testproject1.dao.jobtittle.mapper;

import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.staff.JobTittle;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
/**
 * Маппер для класса {@link JobTittle}
 *
 * @author smigranov
 */
@Component
public class JobTittleMapper implements RowMapper<JobTittle> {
    /**
     * {@inheritDoc}
     */
    @Override
    public JobTittle mapRow(ResultSet rs, int rowNum) throws SQLException {
        JobTittle jobTittle = new JobTittle();
        jobTittle.setUuid(UUID.fromString(rs.getString("job_tittle_id")));
        jobTittle.setName(rs.getString("job_name"));
        return jobTittle;
    }
}
