package com.example.testproject1.dao.basedocument;

import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.BaseDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.BatchUpdateException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.example.testproject1.queryholder.basedocumentquery.BaseDocumentQueryHolder.BASE_DOCUMENT_CREATE_QUERY;
import static com.example.testproject1.queryholder.basedocumentquery.BaseDocumentQueryHolder.BASE_DOCUMENT_UPDATE_QUERY;

/**
 * Абстрактный класс для выполнения операций над {@link BaseDocument} в таблице base_document.
 *
 * @author smigranov
 */
public abstract class AbstractBaseDocumentRepository {

    /**
     * Бин JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Метод сохранения BaseDocument
     */
    public BaseDocument create(BaseDocument baseDocument) {
        if (baseDocument == null) {
            throw new DocflowRuntimeApplicationException("BaseDocument не может быть null");
        }
        try {
            jdbcTemplate.update(BASE_DOCUMENT_CREATE_QUERY, baseDocument.getId().toString(), baseDocument.getName(), baseDocument.getText(),
                    baseDocument.getRegNumber(), baseDocument.getCreatingDate(), baseDocument.getAuthor().getId().toString());
        } catch (DataAccessException e) {
            throw new DocflowRuntimeApplicationException("Ошибка сохранения", e);
        }
        return baseDocument;
    }

    /**
     * Метод обнавления BaseDocument
     */
    public BaseDocument update(BaseDocument baseDocument) throws DocflowRuntimeApplicationException {
        if (baseDocument == null) {
            throw new DocflowRuntimeApplicationException("BaseDocument не может быть null");
        }
        try {
            jdbcTemplate.update(BASE_DOCUMENT_UPDATE_QUERY, baseDocument.getName(), baseDocument.getText(),
                    baseDocument.getRegNumber(), baseDocument.getCreatingDate(), baseDocument.getAuthor().getId().toString(),
                    baseDocument.getId().toString());
        } catch (DataAccessException e) {
            throw new DocflowRuntimeApplicationException("Ошибка обновления", e);
        }
        return baseDocument;

    }

    public void saveAllBase(List<? extends BaseDocument> entityList) throws BatchUpdateException {
        jdbcTemplate.batchUpdate(BASE_DOCUMENT_CREATE_QUERY, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, entityList.get(i).getId().toString());
                ps.setString(2, entityList.get(i).getName());
                ps.setString(3, entityList.get(i).getText());
                ps.setLong(4, entityList.get(i).getRegNumber());
                ps.setTimestamp(5, entityList.get(i).getCreatingDate());
                ps.setString(6, entityList.get(i).getAuthor().getId().toString());
            }

            @Override
            public int getBatchSize() {
                return entityList.size();
            }
        });

    }
}
