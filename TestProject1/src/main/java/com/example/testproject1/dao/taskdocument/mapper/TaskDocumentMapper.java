package com.example.testproject1.dao.taskdocument.mapper;

import com.example.testproject1.dao.basedocument.mapper.BaseDocumentMapper;
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
/**
 * Маппер для класса {@link TaskDocument}
 *
 * @author smigranov
 */
@Component
public class TaskDocumentMapper implements RowMapper<TaskDocument> {
    /**
     * Бин маппер {@link BaseDocument}
     */
    @Autowired
    private BaseDocumentMapper baseDocumentMapper;

    private final String TASK_DOCUMENT_OUT_DATE="task_document_out_date";
    private final String TASK_DOCUMENT_EXEC_PERIOD="task_document_exec_period";
    private final String TASK_DOCUMENT_SIGN_OF_CONTROL="task_document_sign_of_control";
    private final String PERSON_RESPONSE_ID="person_response_id";
    private final String PERSON_RESPONSE_FIRST_NAME="person_response_first_name";
    private final String PERSON_RESPONSE_SECOND_NAME="person_response_second_name";
    private final String PERSON_RESPONSE_LAST_NAME="person_response_last_name";
    private final String PERSON_RESPONSE_PHOTO="person_response_photo";
    private final String PERSON_RESPONSE_PHONE_NUMBER="person_response_phone_number";
    private final String PERSON_RESPONSE_BIRTH_DAY="person_response_birth_day";
    private final String DEPARTMENT_RESPONSE_ID="department_response_id";
    private final String DEPARTMENT_RESPONSE_FULL_NAME="department_response_full_name";
    private final String DEPARTMENT_RESPONSE_SHORT_NAME="department_response_short_name";
    private final String DEPARTMENT_RESPONSE_SUPERVISOR="department_response_supervisor";
    private final String DEPARTMENT_RESPONSE_CONTACT_NUMBER="department_response_contact_number";
    private final String ORGANIZATION_RESPONSE_ID="organization_response_id";
    private final String ORGANIZATION_RESPONSE_FULL_NAME="organization_response_full_name";
    private final String ORGANIZATION_RESPONSE_SHORT_NAME="organization_response_short_name";
    private final String ORGANIZATION_RESPONSE_SUPERVISOR="organization_response_supervisor";
    private final String ORGANIZATION_RESPONSE_CONTACT_NUMBER="organization_response_contact_number";
    private final String JOB_TITTLE_RESPONSE_ID="job_tittle_response_id";
    private final String JOB_RESPONSE_NAME="job_response_name";
    private final String PERSON_CONTROL_ID="person_control_id";
    private final String PERSON_CONTROL_FIRST_NAME="person_control_first_name";
    private final String PERSON_CONTROL_SECOND_NAME="person_control_second_name";
    private final String PERSON_CONTROL_LAST_NAME="person_control_last_name";
    private final String PERSON_CONTROL_PHOTO="person_control_photo";
    private final String PERSON_CONTROL_PHONE_NUMBER="person_control_phone_number";
    private final String PERSON_CONTROL_BIRTH_DAY="person_control_birth_day";
    private final String DEPARTMENT_CONTROL_ID="department_control_id";
    private final String DEPARTMENT_CONTROL_FULL_NAME="department_control_full_name";
    private final String DEPARTMENT_CONTROL_SHORT_NAME="department_control_short_name";
    private final String DEPARTMENT_CONTROL_SUPERVISOR="department_control_supervisor";
    private final String DEPARTMENT_CONTROL_CONTACT_NUMBER="department_control_contact_number";
    private final String ORGANIZATION_CONTROL_ID="organization_control_id";
    private final String ORGANIZATION_CONTROL_FULL_NAME="organization_control_full_name";
    private final String ORGANIZATION_CONTROL_SHORT_NAME="organization_control_short_name";
    private final String ORGANIZATION_CONTROL_SUPERVISOR="organization_control_supervisor";
    private final String ORGANIZATION_CONTROL_CONTACT_NUMBER="organization_control_contact_number";
    private final String JOB_TITTLE_CONTROL_ID="job_tittle_control_id";
    private final String JOB_CONTROL_NAME="job_control_name";

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Мапим baseDocument к taskDocument
        BaseDocument baseDocument = baseDocumentMapper.mapRow(rs, rowNum);
        TaskDocument taskDocument = new TaskDocument();
        taskDocument.setOutDate(rs.getTimestamp(TASK_DOCUMENT_OUT_DATE));
        taskDocument.setExecPeriod(rs.getString(TASK_DOCUMENT_EXEC_PERIOD));
        taskDocument.setSignOfControl(rs.getBoolean(TASK_DOCUMENT_SIGN_OF_CONTROL));
        taskDocument.setId(baseDocument.getId());
        taskDocument.setName(baseDocument.getName());
        taskDocument.setText(baseDocument.getText());
        taskDocument.setAuthor(baseDocument.getAuthor());
        taskDocument.setRegNumber(baseDocument.getRegNumber());
        taskDocument.setCreatingDate(baseDocument.getCreatingDate());

        //Мапим Person(responsible) к taskDocument
        Person response = new Person();
        response.setId(UUID.fromString(rs.getString(PERSON_RESPONSE_ID)));
        response.setFirstName(rs.getString(PERSON_RESPONSE_FIRST_NAME));
        response.setSecondName(rs.getString(PERSON_RESPONSE_SECOND_NAME));
        response.setLastName(rs.getString(PERSON_RESPONSE_LAST_NAME));
        response.setPhoto(rs.getString(PERSON_RESPONSE_PHOTO));
        response.setPhoneNumber(rs.getString(PERSON_RESPONSE_PHONE_NUMBER));
        response.setBirthDay((rs.getDate(PERSON_RESPONSE_BIRTH_DAY)));
        Department departmentResponse = new Department();
        departmentResponse.setId(UUID.fromString(rs.getString(DEPARTMENT_RESPONSE_ID)));
        departmentResponse.setFullName(rs.getString(DEPARTMENT_RESPONSE_FULL_NAME));
        departmentResponse.setShortName(rs.getString(DEPARTMENT_RESPONSE_SHORT_NAME));
        departmentResponse.setSupervisor(rs.getString(DEPARTMENT_RESPONSE_SUPERVISOR));
        departmentResponse.setContactNumber(rs.getString(DEPARTMENT_RESPONSE_CONTACT_NUMBER));
        Organization organizationResponse = new Organization();
        organizationResponse.setId(UUID.fromString(rs.getString(ORGANIZATION_RESPONSE_ID)));
        organizationResponse.setFullName(rs.getString(ORGANIZATION_RESPONSE_FULL_NAME));
        organizationResponse.setShortName(rs.getString(ORGANIZATION_RESPONSE_SHORT_NAME));
        organizationResponse.setSupervisor(rs.getString(ORGANIZATION_RESPONSE_SUPERVISOR));
        organizationResponse.setContactNumber(rs.getString(ORGANIZATION_RESPONSE_CONTACT_NUMBER));
        JobTittle jobTittleResponse = new JobTittle();
        jobTittleResponse.setUuid(UUID.fromString(rs.getString(JOB_TITTLE_RESPONSE_ID)));
        jobTittleResponse.setName(rs.getString(JOB_RESPONSE_NAME));
        departmentResponse.setOrganization(organizationResponse);
        response.setDepartment(departmentResponse);
        response.setJobTittle(jobTittleResponse);
        taskDocument.setResponsible(response);

        //Мапим Person(control_person) к taskDocument
        Person controlPerson = new Person();
        controlPerson.setId(UUID.fromString(rs.getString(PERSON_CONTROL_ID)));
        controlPerson.setFirstName(rs.getString(PERSON_CONTROL_FIRST_NAME));
        controlPerson.setSecondName(rs.getString(PERSON_CONTROL_SECOND_NAME));
        controlPerson.setLastName(rs.getString(PERSON_CONTROL_LAST_NAME));
        controlPerson.setPhoto(rs.getString(PERSON_CONTROL_PHOTO));
        controlPerson.setPhoneNumber(rs.getString(PERSON_CONTROL_PHONE_NUMBER));
        controlPerson.setBirthDay((rs.getDate(PERSON_CONTROL_BIRTH_DAY)));
        Department departmentControl = new Department();
        departmentControl.setId(UUID.fromString(rs.getString(DEPARTMENT_CONTROL_ID)));
        departmentControl.setFullName(rs.getString(DEPARTMENT_CONTROL_FULL_NAME));
        departmentControl.setShortName(rs.getString(DEPARTMENT_CONTROL_SHORT_NAME));
        departmentControl.setSupervisor(rs.getString(DEPARTMENT_CONTROL_SUPERVISOR));
        departmentControl.setContactNumber(rs.getString(DEPARTMENT_CONTROL_CONTACT_NUMBER));
        Organization organizationControl = new Organization();
        organizationControl.setId(UUID.fromString(rs.getString(ORGANIZATION_CONTROL_ID)));
        organizationControl.setFullName(rs.getString(ORGANIZATION_CONTROL_FULL_NAME));
        organizationControl.setShortName(rs.getString(ORGANIZATION_CONTROL_SHORT_NAME));
        organizationControl.setSupervisor(rs.getString(ORGANIZATION_CONTROL_SUPERVISOR));
        organizationControl.setContactNumber(rs.getString(ORGANIZATION_CONTROL_CONTACT_NUMBER));
        JobTittle jobTittleControl = new JobTittle();
        jobTittleControl.setUuid(UUID.fromString(rs.getString(JOB_TITTLE_CONTROL_ID)));
        jobTittleControl.setName(rs.getString(JOB_CONTROL_NAME));
        departmentControl.setOrganization(organizationControl);
        controlPerson.setDepartment(departmentControl);
        controlPerson.setJobTittle(jobTittleControl);
        taskDocument.setControlPerson(controlPerson);
        return taskDocument;
    }
}
