package com.example.testproject1.dao.taskDocument.mapper;

import com.example.testproject1.model.documents.BaseDocument;
import com.example.testproject1.model.documents.TaskDocument;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TaskDocumentMapper implements RowMapper<TaskDocument> {
    @Override
    public TaskDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        TaskDocument taskDocument=new TaskDocument();
        taskDocument.setOutDate(rs.getTimestamp("task_document_out_date"));
        taskDocument.setExecPeriod(rs.getString("task_document_exec_period"));
        taskDocument.setSignOfControl(rs.getBoolean("task_document_sign_of_control"));

        //Мапим baseDocument к taskDocument
        BaseDocument baseDocument=new BaseDocument();
        baseDocument.setId(UUID.fromString(rs.getString("base_document_id")));
        baseDocument.setName(rs.getString("base_document_name"));
        baseDocument.setText(rs.getString("base_document_text"));
        baseDocument.setRegNumber(rs.getLong("base_document_number"));
        baseDocument.setCreatingDate(rs.getTimestamp("base_document_date"));
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
        baseDocument.setAuthor(person);

        taskDocument.setId(baseDocument.getId());

        //Мапим Person(responsible) к taskDocument
        Person response=new Person();
        response.setId(UUID.fromString(rs.getString("person_response_id")));
        response.setFirstName(rs.getString("person_response_id_first_name"));
        response.setSecondName(rs.getString("person_response_id_second_name"));
        response.setLastName(rs.getString("person_response_id_last_name"));
        response.setPhoto(rs.getString("person_response_id_photo"));
        response.setPhoneNumber(rs.getString("person_response_id_phone_number"));
        response.setBirthDay((rs.getDate("person_response_id_birth_day")));
        Department departmentResponse=new Department();
        departmentResponse.setId(UUID.fromString(rs.getString("department_response_id")));
        departmentResponse.setFullName(rs.getString("department_response_full_name"));
        departmentResponse.setShortName(rs.getString("department_response_short_name"));
        departmentResponse.setSupervisor(rs.getString("department_response_supervisor"));
        departmentResponse.setContactNumber(rs.getString("department_response_contact_number"));
        Organization organizationResponse = new Organization();
        organizationResponse.setId(UUID.fromString(rs.getString("organization_response_id")));
        organizationResponse.setFullName(rs.getString("organization_response_full_name"));
        organizationResponse.setShortName(rs.getString("organization_response_short_name"));
        organizationResponse.setSupervisor(rs.getString("organization_response_supervisor"));
        organizationResponse.setContactNumber(rs.getString("organization_response_contact_number"));
        JobTittle jobTittleResponse=new JobTittle();
        jobTittleResponse.setUuid(UUID.fromString(rs.getString("job_tittle_response_id")));
        jobTittleResponse.setName(rs.getString("job_response_name"));
        departmentResponse.setOrganization(organizationResponse);
        response.setDepartment(departmentResponse);
        response.setJobTittle(jobTittleResponse);
        taskDocument.setResponsible(response);

        //Мапим Person(control_person) к taskDocument
        Person controlPerson=new Person();
        controlPerson.setId(UUID.fromString(rs.getString("person_control_id")));
        controlPerson.setFirstName(rs.getString("person_control_id_first_name"));
        controlPerson.setSecondName(rs.getString("person_control_id_second_name"));
        controlPerson.setLastName(rs.getString("person_control_id_last_name"));
        controlPerson.setPhoto(rs.getString("person_control_id_photo"));
        controlPerson.setPhoneNumber(rs.getString("person_control_id_phone_number"));
        controlPerson.setBirthDay((rs.getDate("person_control_id_birth_day")));
        Department departmentControl=new Department();
        departmentControl.setId(UUID.fromString(rs.getString("department_control_id")));
        departmentControl.setFullName(rs.getString("department_control_full_name"));
        departmentControl.setShortName(rs.getString("department_control_short_name"));
        departmentControl.setSupervisor(rs.getString("department_control_supervisor"));
        departmentControl.setContactNumber(rs.getString("department_control_contact_number"));
        Organization organizationControl = new Organization();
        organizationControl.setId(UUID.fromString(rs.getString("organization_control_id")));
        organizationControl.setFullName(rs.getString("organization_control_full_name"));
        organizationControl.setShortName(rs.getString("organization_control_short_name"));
        organizationControl.setSupervisor(rs.getString("organization_control_supervisor"));
        organizationControl.setContactNumber(rs.getString("organization_control_contact_number"));
        JobTittle jobTittleControl=new JobTittle();
        jobTittleControl.setUuid(UUID.fromString(rs.getString("job_tittle_control_id")));
        jobTittleControl.setName(rs.getString("job_control_name"));
        departmentControl.setOrganization(organizationControl);
        controlPerson.setDepartment(departmentControl);
        controlPerson.setJobTittle(jobTittleControl);
        taskDocument.setControlPerson(controlPerson);

        return taskDocument;
    }
}
