package com.example.testproject1.dao.basedocument;

import com.example.testproject1.model.document.BaseDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static com.example.testproject1.queryholder.basedocumentquery.BaseDocumentQueryHolder.BASE_DOCUMENT_CREATE_QUERY;
import static com.example.testproject1.queryholder.basedocumentquery.BaseDocumentQueryHolder.BASE_DOCUMENT_UPDATE_QUERY;

/**
 * Класс реализующий интерфейс . Для выполнения операций с базой данных.
 *
 * @author smigranov
 */

public abstract class BaseDocumentRepositoryImpl{

    /**
     * Бин JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * {@inheritDoc}
     */
    public BaseDocument create(BaseDocument baseDocument) {
        if (baseDocument != null) {
            jdbcTemplate.update(BASE_DOCUMENT_CREATE_QUERY, baseDocument.getId().toString(), baseDocument.getName(), baseDocument.getText(),
                    baseDocument.getRegNumber(), baseDocument.getCreatingDate(), baseDocument.getAuthor().getId().toString());
            return baseDocument;
        } else {
            throw new IllegalArgumentException("BaseDocument не может быть null");
        }
    }
    /**
     * {@inheritDoc}
     */
    public int update(BaseDocument baseDocument) {
        return jdbcTemplate.update(BASE_DOCUMENT_UPDATE_QUERY, baseDocument.getName(), baseDocument.getText(),
                baseDocument.getRegNumber(), baseDocument.getCreatingDate(), baseDocument.getAuthor().getId().toString(),
                baseDocument.getId().toString());
    }
}
