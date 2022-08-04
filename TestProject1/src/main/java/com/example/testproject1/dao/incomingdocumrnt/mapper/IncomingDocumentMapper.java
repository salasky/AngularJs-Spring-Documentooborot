package com.example.testproject1.dao.incomingdocumrnt.mapper;

import com.example.testproject1.dao.basedocument.mapper.BaseDocumentMapper;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.IncomingDocument;
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
 * Маппер для класса {@link IncomingDocument}
 *
 * @author smigranov
 */
@Component
public class IncomingDocumentMapper implements RowMapper<IncomingDocument> {
    /**
     * Бин маппер {@link BaseDocument}
     */
    @Autowired
    private BaseDocumentMapper baseDocumentMapper;
    private final String INCOMING_DOCUMENT_NUMBER="incoming_document_number";
    private final String INCOMING_DOCUMENT_DATE_OF_REGISTRATION="incoming_document_date_of_registration";
    private final String PERSON_SENDER_ID="person_sender_id";
    private final String PERSON_SENDER_FIRST_NAME="person_sender_first_name";
    private final String PERSON_SENDER_SECOND_NAME="person_sender_second_name";
    private final String PERSON_SENDER_LAST_NAME="person_sender_last_name";
    private final String PERSON_SENDER_PHOTO="person_sender_photo";
    private final String PERSON_SENDER_PHONE_NUMBER="person_sender_phone_number";
    private final String PERSON_SENDER_BIRTH_DAY="person_sender_birth_day";
    private final String DEPARTMENT_SENDER_ID="department_sender_id";
    private final String DEPARTMENT_SENDER_FULL_NAME="department_sender_full_name";
    private final String DEPARTMENT_SENDER_SHORT_NAME="department_sender_short_name";
    private final String DEPARTMENT_SENDER_SUPERVISOR="department_sender_supervisor";
    private final String DEPARTMENT_SENDER_CONTACT_NUMBER="department_sender_contact_number";
    private final String ORGANIZATION_SENDER_ID="organization_sender_id";
    private final String ORGANIZATION_SENDER_FULL_NAME="organization_sender_full_name";
    private final String ORGANIZATION_SENDER_SHORT_NAME="organization_sender_short_name";
    private final String ORGANIZATION_SENDER_SUPERVISOR="organization_sender_supervisor";
    private final String ORGANIZATION_SENDER_CONTACT_NUMBER="organization_sender_contact_number";
    private final String JOB_TITTLE_SENDER_ID="job_tittle_sender_id";
    private final String JOB_SENDER_NAME="job_sender_name";
    private final String PERSON_DESTINATION_ID="person_destination_id";
    private final String PERSON_DESTINATION_FIRST_NAME="person_destination_first_name";
    private final String PERSON_DESTINATION_SECOND_NAME="person_destination_second_name";
    private final String PERSON_DESTINATION_LAST_NAME="person_destination_last_name";
    private final String PERSON_DESTINATION_PHOTO="person_destination_photo";
    private final String PERSON_DESTINATION_PHONE_NUMBER="person_destination_phone_number";
    private final String PERSON_DESTINATION_BIRTH_DAY="person_destination_birth_day";
    private final String DEPARTMENT_DESTINATION_ID="department_destination_id";
    private final String DEPARTMENT_DESTINATION_FULL_NAME="department_destination_full_name";
    private final String DEPARTMENT_DESTINATION_SHORT_NAME="department_destination_short_name";
    private final String DEPARTMENT_DESTINATION_SUPERVISOR="department_destination_supervisor";
    private final String DEPARTMENT_DESTINATION_CONTACT_NUMBER="department_destination_contact_number";
    private final String ORGANIZATION_DESTINATION_ID="organization_destination_id";
    private final String ORGANIZATION_DESTINATION_FULL_NAME="organization_destination_full_name";
    private final String ORGANIZATION_DESTINATION_SHORT_NAME="organization_destination_short_name";
    private final String ORGANIZATION_DESTINATION_SUPERVISOR="organization_destination_supervisor";
    private final String ORGANIZATION_DESTINATION_CONTACT_NUMBER="organization_destination_contact_number";
    private final String JOB_TITTLE_DESTINATION_ID="job_tittle_destination_id";
    private final String JOB_DESTINATION_NAME="job_destination_name";
    /**
     * {@inheritDoc}
     */
    @Override
    public IncomingDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Мапим baseDocument к incomingDocument
        BaseDocument baseDocument = baseDocumentMapper.mapRow(rs, rowNum);

        IncomingDocument incomingDocument = new IncomingDocument();
        incomingDocument.setNumber(rs.getLong(INCOMING_DOCUMENT_NUMBER));
        incomingDocument.setDateOfRegistration(rs.getTimestamp(INCOMING_DOCUMENT_DATE_OF_REGISTRATION));
        incomingDocument.setId(baseDocument.getId());
        incomingDocument.setName(baseDocument.getName());
        incomingDocument.setText(baseDocument.getText());
        incomingDocument.setAuthor(baseDocument.getAuthor());
        incomingDocument.setRegNumber(baseDocument.getRegNumber());
        incomingDocument.setCreatingDate(baseDocument.getCreatingDate());

        //Мапим Person(sender) к incomingDocument
        Person sender = new Person();
        sender.setId(UUID.fromString(rs.getString(PERSON_SENDER_ID)));
        sender.setFirstName(rs.getString(PERSON_SENDER_FIRST_NAME));
        sender.setSecondName(rs.getString(PERSON_SENDER_SECOND_NAME));
        sender.setLastName(rs.getString(PERSON_SENDER_LAST_NAME));
        sender.setPhoto(rs.getString(PERSON_SENDER_PHOTO));
        sender.setPhoneNumber(rs.getString(PERSON_SENDER_PHONE_NUMBER));
        sender.setBirthDay((rs.getDate(PERSON_SENDER_BIRTH_DAY)));

        Department departmentsender = new Department();
        departmentsender.setId(UUID.fromString(rs.getString(DEPARTMENT_SENDER_ID)));
        departmentsender.setFullName(rs.getString(DEPARTMENT_SENDER_FULL_NAME));
        departmentsender.setShortName(rs.getString(DEPARTMENT_SENDER_SHORT_NAME));
        departmentsender.setSupervisor(rs.getString(DEPARTMENT_SENDER_SUPERVISOR));
        departmentsender.setContactNumber(rs.getString(DEPARTMENT_SENDER_CONTACT_NUMBER));

        Organization organizationsender = new Organization();
        organizationsender.setId(UUID.fromString(rs.getString(ORGANIZATION_SENDER_ID)));
        organizationsender.setFullName(rs.getString(ORGANIZATION_SENDER_FULL_NAME));
        organizationsender.setShortName(rs.getString(ORGANIZATION_SENDER_SHORT_NAME));
        organizationsender.setSupervisor(rs.getString(ORGANIZATION_SENDER_SUPERVISOR));
        organizationsender.setContactNumber(rs.getString(ORGANIZATION_SENDER_CONTACT_NUMBER));

        JobTittle jobTittlesender = new JobTittle();
        jobTittlesender.setUuid(UUID.fromString(rs.getString(JOB_TITTLE_SENDER_ID)));
        jobTittlesender.setName(rs.getString(JOB_SENDER_NAME));
        departmentsender.setOrganization(organizationsender);
        sender.setDepartment(departmentsender);
        sender.setJobTittle(jobTittlesender);
        incomingDocument.setSender(sender);

        //Мапим Person(destination) к incomingDocument
        Person destination = new Person();
        destination.setId(UUID.fromString(rs.getString(PERSON_DESTINATION_ID)));
        destination.setFirstName(rs.getString(PERSON_DESTINATION_FIRST_NAME));
        destination.setSecondName(rs.getString(PERSON_DESTINATION_SECOND_NAME));
        destination.setLastName(rs.getString(PERSON_DESTINATION_LAST_NAME));
        destination.setPhoto(rs.getString(PERSON_DESTINATION_PHOTO));
        destination.setPhoneNumber(rs.getString(PERSON_DESTINATION_PHONE_NUMBER));
        destination.setBirthDay((rs.getDate(PERSON_DESTINATION_BIRTH_DAY)));

        Department departmentDestination = new Department();
        departmentDestination.setId(UUID.fromString(rs.getString(DEPARTMENT_DESTINATION_ID)));
        departmentDestination.setFullName(rs.getString(DEPARTMENT_DESTINATION_FULL_NAME));
        departmentDestination.setShortName(rs.getString(DEPARTMENT_DESTINATION_SHORT_NAME));
        departmentDestination.setSupervisor(rs.getString(DEPARTMENT_DESTINATION_SUPERVISOR));
        departmentDestination.setContactNumber(rs.getString(DEPARTMENT_DESTINATION_CONTACT_NUMBER));

        Organization organizationDestination = new Organization();
        organizationDestination.setId(UUID.fromString(rs.getString(ORGANIZATION_DESTINATION_ID)));
        organizationDestination.setFullName(rs.getString(ORGANIZATION_DESTINATION_FULL_NAME));
        organizationDestination.setShortName(rs.getString(ORGANIZATION_DESTINATION_SHORT_NAME));
        organizationDestination.setSupervisor(rs.getString(ORGANIZATION_DESTINATION_SUPERVISOR));
        organizationDestination.setContactNumber(rs.getString(ORGANIZATION_DESTINATION_CONTACT_NUMBER));

        JobTittle jobTittleDestination = new JobTittle();
        jobTittleDestination.setUuid(UUID.fromString(rs.getString(JOB_TITTLE_DESTINATION_ID)));
        jobTittleDestination.setName(rs.getString(JOB_DESTINATION_NAME));
        departmentDestination.setOrganization(organizationDestination);
        destination.setDepartment(departmentDestination);
        destination.setJobTittle(jobTittleDestination);
        incomingDocument.setDestination(destination);

        return incomingDocument;
    }
}
