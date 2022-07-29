package com.example.testproject1.dao.person.mapper;

import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class PersonMapper implements RowMapper<Person> {

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

        Department department=new Department();
        department.setId(UUID.fromString(rs.getString("department_id")));
        department.setFullName(rs.getString("department_full_name"));
        department.setShortName(rs.getString("department_short_name"));
        department.setSupervisor(rs.getString("department_supervisor"));
        department.setContactNumber(rs.getString("department_contact_number"));

        Organization organization = new Organization();
        organization.setId(UUID.fromString(rs.getString("organization_id")));
        organization.setFullName(rs.getString("organization_full_name"));
        organization.setShortName(rs.getString("organization_short_name"));
        organization.setSupervisor(rs.getString("organization_supervisor"));
        organization.setContactNumber(rs.getString("organization_contact_number"));

        JobTittle jobTittle=new JobTittle();
        jobTittle.setUuid(UUID.fromString(rs.getString("job_tittle_id")));
        jobTittle.setName(rs.getString("job_name"));

        department.setOrganization(organization);
        person.setDepartment(department);
        person.setJobTittle(jobTittle);
        return person;
    }
}
