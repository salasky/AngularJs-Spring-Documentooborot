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

@Component
public class PersonMapper implements RowMapper<Person> {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private JobTittleMapper jobTittleMapper;
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person=new Person();
        person.setId(UUID.fromString(rs.getString("person_id")));
        person.setFirstName(rs.getString("person_first_name"));
        person.setSecondName(rs.getString("person_second_name"));
        person.setLastName(rs.getString("person_last_name"));
        person.setPhoto(rs.getString("person_photo"));
        person.setPhoneNumber(rs.getString("person_phone_number"));
        person.setBirthDay((rs.getDate("person_birth_day")));

        Department department=departmentMapper.mapRow(rs,rowNum);

        JobTittle jobTittle=jobTittleMapper.mapRow(rs,rowNum);

        person.setDepartment(department);
        person.setJobTittle(jobTittle);
        return person;
    }
}
