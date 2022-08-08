package com.example.testproject1.mapper.document;

import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.staff.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Маппер для класса {@link BaseDocument}
 *
 * @author smigranov
 */
@Component
public class BaseDocumentMapper implements RowMapper<BaseDocument> {

    /**
     * Название столбца для мапинга в поле id
     */
    private final String BASE_DOCUMENT_ID = "base_document_id";
    /**
     * Название столбца для мапинга в поле document_name
     */
    private final String BASE_DOCUMENT_NAME = "base_document_name";
    /**
     * Название столбца для мапинга в поле document_text
     */
    private final String BASE_DOCUMENT_TEXT = "base_document_text";
    /**
     * Название столбца для мапинга в поле document_number
     */
    private final String BASE_DOCUMENT_NUMBER = "base_document_number";
    /**
     * Название столбца для мапинга в поле document_date
     */
    private final String BASE_DOCUMENT_DATE = "base_document_date";
    /**
     * Название столбца для мапинга в поле person_id
     */
    private final String PERSON_ID = "person_id";
    /**
     * Название столбца для мапинга в поле person_first_name
     */
    private final String PERSON_FIRST_NAME = "person_first_name";
    /**
     * Название столбца для мапинга в поле person_second_name
     */
    private final String PERSON_SECOND_NAME = "person_second_name";
    /**
     * Название столбца для мапинга в поле person_last_name
     */
    private final String PERSON_LAST_NAME = "person_last_name";

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        BaseDocument baseDocument = new BaseDocument();
        baseDocument.setId(UUID.fromString(rs.getString(BASE_DOCUMENT_ID)));
        baseDocument.setName(rs.getString(BASE_DOCUMENT_NAME));
        baseDocument.setText(rs.getString(BASE_DOCUMENT_TEXT));
        baseDocument.setRegNumber(rs.getLong(BASE_DOCUMENT_NUMBER));
        baseDocument.setCreatingDate(rs.getTimestamp(BASE_DOCUMENT_DATE));
        Person person = new Person();
        person.setId(UUID.fromString(rs.getString(PERSON_ID)));
        person.setFirstName(rs.getString(PERSON_FIRST_NAME));
        person.setSecondName(rs.getString(PERSON_SECOND_NAME));
        person.setLastName(rs.getString(PERSON_LAST_NAME));
        baseDocument.setAuthor(person);
        return baseDocument;
    }
}
