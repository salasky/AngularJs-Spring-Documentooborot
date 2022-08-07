package com.example.testproject1.mapper.document;

import com.example.testproject1.mapper.staff.PersonMapper;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.staff.Person;
import org.springframework.beans.factory.annotation.Autowired;
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
     * Бин маппер {@link Person}
     */
    @Autowired
    private PersonMapper personMapper;
    /**
     * Название столбца для мапинга в поле id
     */
    private final String BASE_DOCUMENT_ID="base_document_id";
    /**
     * Название столбца для мапинга в поле document_name
     */
    private final String BASE_DOCUMENT_NAME="base_document_name";
    /**
     * Название столбца для мапинга в поле document_text
     */
    private final String BASE_DOCUMENT_TEXT="base_document_text";
    /**
     * Название столбца для мапинга в поле document_number
     */
    private final String BASE_DOCUMENT_NUMBER="base_document_number";
    /**
     * Название столбца для мапинга в поле document_date
     */
    private final String BASE_DOCUMENT_DATE="base_document_date";
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
        Person person = personMapper.mapRow(rs, rowNum);
        baseDocument.setAuthor(person);
        return baseDocument;
    }
}
