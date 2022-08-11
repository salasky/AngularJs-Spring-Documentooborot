package com.example.testproject1.dao.incomingdocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.dao.basedocument.BaseDocumentRepositoryImpl;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.mapper.document.IncomingDocumentMapper;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.testproject1.queryholder.incomingdocumentquery.IncomingDocumentQueryHolder.INCOMING_DOCUMENT_CREATE_QUERY;
import static com.example.testproject1.queryholder.incomingdocumentquery.IncomingDocumentQueryHolder.INCOMING_DOCUMENT_DELETE_ALL_QUERY;
import static com.example.testproject1.queryholder.incomingdocumentquery.IncomingDocumentQueryHolder.INCOMING_DOCUMENT_DELETE_BY_ID_QUERY;
import static com.example.testproject1.queryholder.incomingdocumentquery.IncomingDocumentQueryHolder.INCOMING_DOCUMENT_GET_ALL_QUERY;
import static com.example.testproject1.queryholder.incomingdocumentquery.IncomingDocumentQueryHolder.INCOMING_DOCUMENT_GET_BY_ID_QUERY;
import static com.example.testproject1.queryholder.incomingdocumentquery.IncomingDocumentQueryHolder.INCOMING_DOCUMENT_UPDATE_QUERY;

/**
 * Класс реализующий интерфейс {@link CrudRepository}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository("IncomingDocumentRepository")
public class IncomingDocumentRepository extends BaseDocumentRepositoryImpl implements CrudRepository<IncomingDocument> {

    /**
     * Бин JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * Маппер для извлечения {@link IncomingDocument}
     */
    @Autowired
    private IncomingDocumentMapper incomingDocumentMapper;

    /**
     * Сервис для работы с {@link Person}
     */
    @Autowired
    private CrudService<Person> personService;

    /**
     * {@inheritDoc}
     */
    @Override
    public IncomingDocument create(IncomingDocument incomingDocument) throws DocflowRuntimeApplicationException {
        if (incomingDocument != null) {
            super.create(incomingDocument);
            jdbcTemplate.update(INCOMING_DOCUMENT_CREATE_QUERY, incomingDocument.getId().toString(),
                    incomingDocument.getSender().getId().toString(),
                    incomingDocument.getDestination().getId().toString(),
                    incomingDocument.getNumber(), incomingDocument.getDateOfRegistration());
            return incomingDocument;
        } else {
            throw new DocflowRuntimeApplicationException("IncomingDocument не может быть null");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IncomingDocument> getAll() {
        return jdbcTemplate.query(INCOMING_DOCUMENT_GET_ALL_QUERY, incomingDocumentMapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<IncomingDocument> getById(String id) {
        return jdbcTemplate.query(INCOMING_DOCUMENT_GET_BY_ID_QUERY, incomingDocumentMapper, id)
                .stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(IncomingDocument incomingDocument) {
        int baseDocumentCount = super.update(incomingDocument);
        return jdbcTemplate.update(INCOMING_DOCUMENT_UPDATE_QUERY, incomingDocument.getSender().getId().toString(),
                incomingDocument.getDestination().getId().toString(), incomingDocument.getNumber(),
                incomingDocument.getDateOfRegistration(), incomingDocument.getId().toString()) + baseDocumentCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        jdbcTemplate.update(INCOMING_DOCUMENT_DELETE_ALL_QUERY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(String id) throws DeleteByIdException {
        int deleteCount = jdbcTemplate.update(INCOMING_DOCUMENT_DELETE_BY_ID_QUERY, id);
        if (deleteCount == 1) {
            return true;
        }
        throw new DeleteByIdException("IncomingDocument");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(UUID uuid) {
        return jdbcTemplate.query(INCOMING_DOCUMENT_GET_BY_ID_QUERY, incomingDocumentMapper, uuid.toString())
                .stream().findFirst().isPresent();
    }
}

