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
     * {@inheritDoc}
     */
    @Override
    public BaseDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = Person.newBuilder().
                setId(UUID.fromString(rs.getString(PERSON_ID))).build();

        return BaseDocument.newBuilder().setDocId(UUID.fromString(rs.getString(BASE_DOCUMENT_ID)))
                .setDocName(rs.getString(BASE_DOCUMENT_NAME))
                .setDocText(rs.getString(BASE_DOCUMENT_TEXT))
                .setDocRegNumber(rs.getLong(BASE_DOCUMENT_NUMBER))
                .setDocDate(rs.getTimestamp(BASE_DOCUMENT_DATE))
                .setDocAuthor(person).build();
    }
}
