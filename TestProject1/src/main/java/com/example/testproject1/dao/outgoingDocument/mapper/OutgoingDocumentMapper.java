package com.example.testproject1.dao.outgoingDocument.mapper;

import com.example.testproject1.dao.baseDocument.mapper.BaseDocumentMapper;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.documentenum.DocumentDeliveryType;
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
public class OutgoingDocumentMapper implements RowMapper<OutgoingDocument> {
    @Autowired
    private BaseDocumentMapper baseDocumentMapper;

    @Override
    public OutgoingDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Мапим baseDocument к incomingDocument
        BaseDocument baseDocument = baseDocumentMapper.mapRow(rs, rowNum);

        OutgoingDocument outgoingDocument = new OutgoingDocument();
        outgoingDocument.setDeliveryType(DocumentDeliveryType.valueOf(rs.getString("outgoing_delivery_type")));
        outgoingDocument.setId(baseDocument.getId());
        outgoingDocument.setName(baseDocument.getName());
        outgoingDocument.setText(baseDocument.getText());
        outgoingDocument.setAuthor(baseDocument.getAuthor());
        outgoingDocument.setRegNumber(baseDocument.getRegNumber());
        outgoingDocument.setCreatingDate(baseDocument.getCreatingDate());

        //Мапим Person(sender) к outgoingDocument
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
        outgoingDocument.setSender(sender);

        return outgoingDocument;
    }
}
