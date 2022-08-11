package com.example.testproject1.dao.basedocument;

import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.BaseDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.example.testproject1.queryholder.basedocumentquery.BaseDocumentQueryHolder.BASE_DOCUMENT_CREATE_QUERY;
import static com.example.testproject1.queryholder.basedocumentquery.BaseDocumentQueryHolder.BASE_DOCUMENT_UPDATE_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.DEPARTMENT_UPDATE_QUERY;

/**
 * Абстрактный класс для выполнения операций над {@link BaseDocument} в таблице base_document.
 *
 * @author smigranov
 */
public abstract class BaseDocumentRepositoryImpl {

    /**
     * Бин JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * {@inheritDoc}
     */
    public BaseDocument create(BaseDocument baseDocument) throws DocflowRuntimeApplicationException {
        if (baseDocument==null) {
            throw new DocflowRuntimeApplicationException("BaseDocument не может быть null");
        }
        jdbcTemplate.update(BASE_DOCUMENT_CREATE_QUERY, baseDocument.getId().toString(), baseDocument.getName(), baseDocument.getText(),
                baseDocument.getRegNumber(), baseDocument.getCreatingDate(), baseDocument.getAuthor().getId().toString());
        return baseDocument;
    }

    /**
     * {@inheritDoc}
     */
    public BaseDocument update(BaseDocument baseDocument) throws DocflowRuntimeApplicationException {
        if (baseDocument == null) {
            throw new DocflowRuntimeApplicationException("BaseDocument не может быть null");
        }
        jdbcTemplate.update(BASE_DOCUMENT_UPDATE_QUERY, baseDocument.getName(), baseDocument.getText(),
                baseDocument.getRegNumber(), baseDocument.getCreatingDate(), baseDocument.getAuthor().getId().toString(),
                baseDocument.getId().toString());
        return baseDocument;
    }
}
