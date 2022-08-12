package com.example.testproject1.mapper.document;

import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.IncomingDocument;
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
     * Название столбца для мапинга в поле document_number
     */
    private final String INCOMING_DOCUMENT_NUMBER = "incoming_document_number";
    /**
     * Название столбца для мапинга в поле date_of_registration
     */
    private final String INCOMING_DOCUMENT_DATE_OF_REGISTRATION = "incoming_document_date_of_registration";
    /**
     * Название столбца для мапинга в поле sender_id
     */
    private final String PERSON_SENDER_ID = "person_sender_id";
    /**
     * Название столбца для мапинга в поле sender_first_name
     */
    private final String PERSON_SENDER_FIRST_NAME = "person_sender_first_name";
    /**
     * Название столбца для мапинга в поле sender_second_name
     */
    private final String PERSON_SENDER_SECOND_NAME = "person_sender_second_name";
    /**
     * Название столбца для мапинга в поле sender_last_name
     */
    private final String PERSON_SENDER_LAST_NAME = "person_sender_last_name";

    /**
     * Название столбца для мапинга в поле person_id
     */
    private final String PERSON_DESTINATION_ID = "person_destination_id";
    /**
     * Название столбца для мапинга в поле person_first_name
     */
    private final String PERSON_DESTINATION_FIRST_NAME = "person_destination_first_name";
    /**
     * Название столбца для мапинга в поле person_second_name
     */
    private final String PERSON_DESTINATION_SECOND_NAME = "person_destination_second_name";
    /**
     * Название столбца для мапинга в поле person_last_name
     */
    private final String PERSON_DESTINATION_LAST_NAME = "person_destination_last_name";

    /**
     * {@inheritDoc}
     */
    @Override
    public IncomingDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Мапим baseDocument к incomingDocument
        BaseDocument baseDocument = baseDocumentMapper.mapRow(rs, rowNum);

        //Мапим Person(sender) к incomingDocument
        Person sender = Person.newBuilder()
                .setId(UUID.fromString(rs.getString(PERSON_SENDER_ID)))
                .setFirstName(rs.getString(PERSON_SENDER_FIRST_NAME))
                .setSecondName(rs.getString(PERSON_SENDER_SECOND_NAME))
                .setLastName(rs.getString(PERSON_SENDER_LAST_NAME)).build();

        //Мапим Person(destination) к incomingDocument
        Person destination = Person.newBuilder()
                .setId(UUID.fromString(rs.getString(PERSON_DESTINATION_ID)))
                .setFirstName(rs.getString(PERSON_DESTINATION_FIRST_NAME))
                .setSecondName(rs.getString(PERSON_DESTINATION_SECOND_NAME))
                .setLastName(rs.getString(PERSON_DESTINATION_LAST_NAME)).build();

        return (IncomingDocument) IncomingDocument.newBuilder()
                .setIncomingDocumentNumber(rs.getLong(INCOMING_DOCUMENT_NUMBER))
                .setIncomingDocumentDate(rs.getTimestamp(INCOMING_DOCUMENT_DATE_OF_REGISTRATION))
                .setIncomingDestination(destination)
                .setIncomingSender(sender)
                .setDocId(baseDocument.getId())
                .setDocName(baseDocument.getName())
                .setDocText(baseDocument.getText())
                .setDocAuthor(baseDocument.getAuthor())
                .setDocRegNumber(baseDocument.getRegNumber())
                .setDocDate(baseDocument.getCreatingDate()).build();
    }
}
