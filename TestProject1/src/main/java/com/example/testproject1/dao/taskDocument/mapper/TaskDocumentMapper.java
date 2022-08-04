package com.example.testproject1.dao.taskDocument.mapper;

import com.example.testproject1.dao.baseDocument.mapper.BaseDocumentMapper;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class TaskDocumentMapper implements RowMapper<TaskDocument> {
    @Autowired
    private BaseDocumentMapper baseDocumentMapper;

    @Override
    public TaskDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Мапим baseDocument к taskDocument
        BaseDocument baseDocument = baseDocumentMapper.mapRow(rs, rowNum);

        TaskDocument taskDocument = new TaskDocument();
        taskDocument.setOutDate(rs.getTimestamp("task_document_out_date"));
        taskDocument.setExecPeriod(rs.getString("task_document_exec_period"));
        taskDocument.setSignOfControl(rs.getBoolean("task_document_sign_of_control"));
        taskDocument.setId(baseDocument.getId());
        taskDocument.setName(baseDocument.getName());
        taskDocument.setText(baseDocument.getText());
        taskDocument.setAuthor(baseDocument.getAuthor());
        taskDocument.setRegNumber(baseDocument.getRegNumber());
        taskDocument.setCreatingDate(baseDocument.getCreatingDate());

        //Мапим Person(responsible) к taskDocument
        Person response = new Person();
        response.setId(UUID.fromString(rs.getString("person_response_id")));
        response.setFirstName(rs.getString("person_response_first_name"));
        response.setSecondName(rs.getString("person_response_second_name"));
        response.setLastName(rs.getString("person_response_last_name"));
        response.setPhoto(rs.getString("person_response_photo"));
        response.setPhoneNumber(rs.getString("person_response_phone_number"));
        response.setBirthDay((rs.getDate("person_response_birth_day")));
        Department departmentResponse = new Department();
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
        JobTittle jobTittleResponse = new JobTittle();
        jobTittleResponse.setUuid(UUID.fromString(rs.getString("job_tittle_response_id")));
        jobTittleResponse.setName(rs.getString("job_response_name"));
        departmentResponse.setOrganization(organizationResponse);
        response.setDepartment(departmentResponse);
        response.setJobTittle(jobTittleResponse);
        taskDocument.setResponsible(response);

        //Мапим Person(control_person) к taskDocument
        Person controlPerson = new Person();
        controlPerson.setId(UUID.fromString(rs.getString("person_control_id")));
        controlPerson.setFirstName(rs.getString("person_control_id_first_name"));
        controlPerson.setSecondName(rs.getString("person_control_id_second_name"));
        controlPerson.setLastName(rs.getString("person_control_id_last_name"));
        controlPerson.setPhoto(rs.getString("person_control_id_photo"));
        controlPerson.setPhoneNumber(rs.getString("person_control_id_phone_number"));
        controlPerson.setBirthDay((rs.getDate("person_control_id_birth_day")));
        Department departmentControl = new Department();
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
        JobTittle jobTittleControl = new JobTittle();
        jobTittleControl.setUuid(UUID.fromString(rs.getString("job_tittle_control_id")));
        jobTittleControl.setName(rs.getString("job_control_name"));
        departmentControl.setOrganization(organizationControl);
        controlPerson.setDepartment(departmentControl);
        controlPerson.setJobTittle(jobTittleControl);
        taskDocument.setControlPerson(controlPerson);

        return taskDocument;
    }
}
