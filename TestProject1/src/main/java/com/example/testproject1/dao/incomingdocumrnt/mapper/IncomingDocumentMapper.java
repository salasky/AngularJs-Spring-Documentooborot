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
    /**
     * {@inheritDoc}
     */
    @Override
    public IncomingDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Мапим baseDocument к incomingDocument
        BaseDocument baseDocument = baseDocumentMapper.mapRow(rs, rowNum);

        IncomingDocument incomingDocument = new IncomingDocument();
        incomingDocument.setNumber(rs.getLong("incoming_document_number"));
        incomingDocument.setDateOfRegistration(rs.getTimestamp("incoming_document_date_of_registration"));
        incomingDocument.setId(baseDocument.getId());
        incomingDocument.setName(baseDocument.getName());
        incomingDocument.setText(baseDocument.getText());
        incomingDocument.setAuthor(baseDocument.getAuthor());
        incomingDocument.setRegNumber(baseDocument.getRegNumber());
        incomingDocument.setCreatingDate(baseDocument.getCreatingDate());

        //Мапим Person(sender) к incomingDocument
        Person sender = new Person();
        sender.setId(UUID.fromString(rs.getString("person_sender_id")));
        sender.setFirstName(rs.getString("person_sender_first_name"));
        sender.setSecondName(rs.getString("person_sender_second_name"));
        sender.setLastName(rs.getString("person_sender_last_name"));
        sender.setPhoto(rs.getString("person_sender_photo"));
        sender.setPhoneNumber(rs.getString("person_sender_phone_number"));
        sender.setBirthDay((rs.getDate("person_sender_birth_day")));
        Department departmentsender = new Department();
        departmentsender.setId(UUID.fromString(rs.getString("department_sender_id")));
        departmentsender.setFullName(rs.getString("department_sender_full_name"));
        departmentsender.setShortName(rs.getString("department_sender_short_name"));
        departmentsender.setSupervisor(rs.getString("department_sender_supervisor"));
        departmentsender.setContactNumber(rs.getString("department_sender_contact_number"));
        Organization organizationsender = new Organization();
        organizationsender.setId(UUID.fromString(rs.getString("organization_sender_id")));
        organizationsender.setFullName(rs.getString("organization_sender_full_name"));
        organizationsender.setShortName(rs.getString("organization_sender_short_name"));
        organizationsender.setSupervisor(rs.getString("organization_sender_supervisor"));
        organizationsender.setContactNumber(rs.getString("organization_sender_contact_number"));
        JobTittle jobTittlesender = new JobTittle();
        jobTittlesender.setUuid(UUID.fromString(rs.getString("job_tittle_sender_id")));
        jobTittlesender.setName(rs.getString("job_sender_name"));
        departmentsender.setOrganization(organizationsender);
        sender.setDepartment(departmentsender);
        sender.setJobTittle(jobTittlesender);
        incomingDocument.setSender(sender);

        //Мапим Person(destination) к incomingDocument
        Person destination = new Person();
        destination.setId(UUID.fromString(rs.getString("person_destination_id")));
        destination.setFirstName(rs.getString("person_destination_first_name"));
        destination.setSecondName(rs.getString("person_destination_second_name"));
        destination.setLastName(rs.getString("person_destination_last_name"));
        destination.setPhoto(rs.getString("person_destination_photo"));
        destination.setPhoneNumber(rs.getString("person_destination_phone_number"));
        destination.setBirthDay((rs.getDate("person_destination_birth_day")));

        Department departmentDestination = new Department();
        departmentDestination.setId(UUID.fromString(rs.getString("department_destination_id")));
        departmentDestination.setFullName(rs.getString("department_destination_full_name"));
        departmentDestination.setShortName(rs.getString("department_destination_short_name"));
        departmentDestination.setSupervisor(rs.getString("department_destination_supervisor"));
        departmentDestination.setContactNumber(rs.getString("department_destination_contact_number"));
        Organization organizationDestination = new Organization();
        organizationDestination.setId(UUID.fromString(rs.getString("organization_destination_id")));
        organizationDestination.setFullName(rs.getString("organization_destination_full_name"));
        organizationDestination.setShortName(rs.getString("organization_destination_short_name"));
        organizationDestination.setSupervisor(rs.getString("organization_destination_supervisor"));
        organizationDestination.setContactNumber(rs.getString("organization_destination_contact_number"));
        JobTittle jobTittleDestination = new JobTittle();
        jobTittleDestination.setUuid(UUID.fromString(rs.getString("job_tittle_destination_id")));
        jobTittleDestination.setName(rs.getString("job_destination_name"));
        departmentDestination.setOrganization(organizationDestination);
        destination.setDepartment(departmentDestination);
        destination.setJobTittle(jobTittleDestination);
        incomingDocument.setDestination(destination);

        return incomingDocument;
    }
}
