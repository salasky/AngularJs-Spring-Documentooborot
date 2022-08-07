package com.example.testproject1.dao.person.mapper;

import com.example.testproject1.dao.department.mapper.DepartmentMapper;
import com.example.testproject1.dao.jobtittle.mapper.JobTittleMapper;
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
    private final String PERSON_ID="person_id";
    private final String PERSON_FIRST_NAME="person_first_name";
    private final String PERSON_SECOND_NAME="person_second_name";
    private final String PERSON_LAST_NAME="person_last_name";
    private final String PERSON_PHOTO="person_photo";
    private final String PERSON_PHONE_NUMBER="person_phone_number";
    private final String PERSON_BIRTH_DAY="person_birth_day";
    /**
     * {@inheritDoc}
     */
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(UUID.fromString(rs.getString(PERSON_ID)));
        person.setFirstName(rs.getString(PERSON_FIRST_NAME));
        person.setSecondName(rs.getString(PERSON_SECOND_NAME));
        person.setLastName(rs.getString(PERSON_LAST_NAME));
        person.setPhoto(rs.getString(PERSON_PHOTO));
        person.setPhoneNumber(rs.getString(PERSON_PHONE_NUMBER));
        person.setBirthDay((rs.getDate(PERSON_BIRTH_DAY)));
        Department department = departmentMapper.mapRow(rs, rowNum);
        JobTittle jobTittle = jobTittleMapper.mapRow(rs, rowNum);
        person.setDepartment(department);
        person.setJobTittle(jobTittle);
        return person;
    }
}
