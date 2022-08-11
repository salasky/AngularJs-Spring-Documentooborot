package com.example.testproject1.mapper.staff;

import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Маппер для класса {@link Person}
 *
 * @author smigranov
 */
@Component
public class PersonMapper implements RowMapper<Person> {
    /**
     * Бин маппер {@link Department}
     */
    @Autowired
    private DepartmentMapper departmentMapper;
    /**
     * Бин маппер {@link JobTittle}
     */
    @Autowired
    private JobTittleMapper jobTittleMapper;
    /**
     * Название столбца для мапинга в поле person_id
     */
    private final String PERSON_ID = "person_id";
    /**
     * Название столбца для мапинга в поле person_first_name
     */
    private final String PERSON_FIRST_NAME = "person_first_name";
    /**
     * Название столбца для мапинга в поле person_second_name
     */
    private final String PERSON_SECOND_NAME = "person_second_name";
    /**
     * Название столбца для мапинга в поле person_last_name
     */
    private final String PERSON_LAST_NAME = "person_last_name";
    /**
     * Название столбца для мапинга в поле person_photo
     */
    private final String PERSON_PHOTO = "person_photo";
    /**
     * Название столбца для мапинга в поле person_phone_number
     */
    private final String PERSON_PHONE_NUMBER = "person_phone_number";
    /**
     * Название столбца для мапинга в поле person_birth_day
     */
    private final String PERSON_BIRTH_DAY = "person_birth_day";

    /**
     * {@inheritDoc}
     */
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = departmentMapper.mapRow(rs, rowNum);
        JobTittle jobTittle = jobTittleMapper.mapRow(rs, rowNum);

        return Person.newBuilder()
                .setId(UUID.fromString(rs.getString(PERSON_ID)))
                .setFirstName(rs.getString(PERSON_FIRST_NAME))
                .setSecondName(rs.getString(PERSON_SECOND_NAME))
                .setLastName(rs.getString(PERSON_LAST_NAME))
                .setPhoto(rs.getString(PERSON_PHOTO))
                .setPhoneNumber(rs.getString(PERSON_PHONE_NUMBER))
                .setBirthDay((rs.getDate(PERSON_BIRTH_DAY)))
                .setJobTittle(jobTittle)
                .setDepartment(department).build();
    }
}
