package com.example.testproject1.mapper.staff;

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
     * Название столбца для мапинга в поле job_tittle_id
     */
    private final String JOB_TITTLE_ID = "job_tittle_id";
    /**
     * Название столбца для мапинга в поле job_name
     */
    private final String JOB_NAME = "job_name";

    /**
     * {@inheritDoc}
     */
    @Override
    public JobTittle mapRow(ResultSet rs, int rowNum) throws SQLException {
        JobTittle jobTittle = new JobTittle();
        jobTittle.setUuid(UUID.fromString(rs.getString(JOB_TITTLE_ID)));
        jobTittle.setName(rs.getString(JOB_NAME));
        return jobTittle;
    }
}
