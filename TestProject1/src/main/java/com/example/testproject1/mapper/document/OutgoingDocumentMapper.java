package com.example.testproject1.mapper.document;

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
/**
 * Маппер для класса {@link OutgoingDocument}
 *
 * @author smigranov
 */
@Component
public class OutgoingDocumentMapper implements RowMapper<OutgoingDocument> {
    /**
     * Бин маппера {@link BaseDocument}
     */
    @Autowired
    private BaseDocumentMapper baseDocumentMapper;
    /**
     * Название столбца для мапинга в поле outgoing_delivery_type
     */
    private final String OUTGOING_DELIVERY_TYPE="outgoing_delivery_type";
    /**
     * Название столбца для мапинга в поле person_sender_id
     */
    private final String PERSON_SENDER_ID="person_sender_id";
    /**
     * Название столбца для мапинга в поле person_sender_first_name
     */
    private final String PERSON_SENDER_FIRST_NAME="person_sender_first_name";
    /**
     * Название столбца для мапинга в поле person_sender_second_name
     */
    private final String PERSON_SENDER_SECOND_NAME="person_sender_second_name";
    /**
     * Название столбца для мапинга в поле person_sender_last_name
     */
    private final String PERSON_SENDER_LAST_NAME="person_sender_last_name";
    /**
     * Название столбца для мапинга в поле person_sender_photo
     */
    private final String PERSON_SENDER_PHOTO="person_sender_photo";
    /**
     * Название столбца для мапинга в поле person_sender_phone_number
     */
    private final String PERSON_SENDER_PHONE_NUMBER="person_sender_phone_number";
    /**
     * Название столбца для мапинга в поле person_sender_birth_day
     */
    private final String PERSON_SENDER_BIRTH_DAY="person_sender_birth_day";
    /**
     * Название столбца для мапинга в поле department_sender_id
     */
    private final String DEPARTMENT_SENDER_ID="department_sender_id";
    /**
     * Название столбца для мапинга в поле department_sender_full_name
     */
    private final String DEPARTMENT_SENDER_FULL_NAME="department_sender_full_name";
    /**
     * Название столбца для мапинга в поле department_sender_short_name
     */
    private final String DEPARTMENT_SENDER_SHORT_NAME="department_sender_short_name";
    /**
     * Название столбца для мапинга в поле department_sender_supervisor
     */
    private final String DEPARTMENT_SENDER_SUPERVISOR="department_sender_supervisor";
    /**
     * Название столбца для мапинга в поле department_sender_contact_number
     */
    private final String DEPARTMENT_SENDER_CONTACT_NUMBER="department_sender_contact_number";
    /**
     * Название столбца для мапинга в поле organization_sender_id
     */
    private final String ORGANIZATION_SENDER_ID="organization_sender_id";
    /**
     * Название столбца для мапинга в поле organization_sender_full_name
     */
    private final String ORGANIZATION_SENDER_FULL_NAME="organization_sender_full_name";
    /**
     * Название столбца для мапинга в поле document_organization_sender_short_namenumber
     */
    private final String ORGANIZATION_SENDER_SHORT_NAME="organization_sender_short_name";
    /**
     * Название столбца для мапинга в поле organization_sender_supervisor
     */
    private final String ORGANIZATION_SENDER_SUPERVISOR="organization_sender_supervisor";
    /**
     * Название столбца для мапинга в поле organization_sender_contact_number
     */
    private final String ORGANIZATION_SENDER_CONTACT_NUMBER="organization_sender_contact_number";
    /**
     * Название столбца для мапинга в поле job_tittle_sender_id
     */
    private final String JOB_TITTLE_SENDER_ID="job_tittle_sender_id";
    /**
     * Название столбца для мапинга в поле job_sender_name
     */
    private final String JOB_SENDER_NAME="job_sender_name";
    /**
     * {@inheritDoc}
     */
    @Override
    public OutgoingDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Мапим baseDocument к incomingDocument
        BaseDocument baseDocument = baseDocumentMapper.mapRow(rs, rowNum);
        OutgoingDocument outgoingDocument = new OutgoingDocument();
        outgoingDocument.setDeliveryType(DocumentDeliveryType.valueOf(rs.getString(OUTGOING_DELIVERY_TYPE)));
        outgoingDocument.setId(baseDocument.getId());
        outgoingDocument.setName(baseDocument.getName());
        outgoingDocument.setText(baseDocument.getText());
        outgoingDocument.setAuthor(baseDocument.getAuthor());
        outgoingDocument.setRegNumber(baseDocument.getRegNumber());
        outgoingDocument.setCreatingDate(baseDocument.getCreatingDate());

        //Мапим Person(sender) к outgoingDocument
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
        outgoingDocument.setSender(sender);

        return outgoingDocument;
    }
}
