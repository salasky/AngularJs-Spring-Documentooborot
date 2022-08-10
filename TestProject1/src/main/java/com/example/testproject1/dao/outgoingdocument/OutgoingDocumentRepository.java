package com.example.testproject1.dao.outgoingdocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.dao.basedocument.BaseDocumentRepositoryImpl;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.mapper.document.OutgoingDocumentMapper;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.testproject1.queryholder.outgoingdocumentquery.OutgoingDocumentQueryHolder.OUTGOING_DOCUMENT_CREATE_QUERY;
import static com.example.testproject1.queryholder.outgoingdocumentquery.OutgoingDocumentQueryHolder.OUTGOING_DOCUMENT_DELETE_ALL_QUERY;
import static com.example.testproject1.queryholder.outgoingdocumentquery.OutgoingDocumentQueryHolder.OUTGOING_DOCUMENT_DELETE_BY_ID_QUERY;
import static com.example.testproject1.queryholder.outgoingdocumentquery.OutgoingDocumentQueryHolder.OUTGOING_DOCUMENT_GET_ALL_QUERY;
import static com.example.testproject1.queryholder.outgoingdocumentquery.OutgoingDocumentQueryHolder.OUTGOING_DOCUMENT_GET_BY_ID_QUERY;
import static com.example.testproject1.queryholder.outgoingdocumentquery.OutgoingDocumentQueryHolder.OUTGOING_DOCUMENT_UPDATE_QUERY;

/**
 * Класс реализующий интерфейс {@link CrudRepository}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository("OutgoingDocumentRepository")
public class OutgoingDocumentRepository extends BaseDocumentRepositoryImpl implements CrudRepository<OutgoingDocument> {

    /**
     * Бин JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * Маппер для извлечения {@link OutgoingDocument}
     */
    @Autowired
    private OutgoingDocumentMapper outgoingDocumentMapper;
    /**
     * Сервис для работы с {@link Person}
     */
    @Autowired
    private CrudService<Person> personService;

    /**
     * {@inheritDoc}
     */
    @Override
    public OutgoingDocument create(OutgoingDocument outgoingDocument) {
        if (outgoingDocument != null) {
                super.create(outgoingDocument);
                jdbcTemplate.update(OUTGOING_DOCUMENT_CREATE_QUERY, outgoingDocument.getId().toString(),
                        outgoingDocument.getSender().getId().toString(),
                        outgoingDocument.getDeliveryType().toString());
                return outgoingDocument;
        } else {
            throw new IllegalArgumentException("OutgoingDocument не может быть null");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OutgoingDocument> getAll() {
        return jdbcTemplate.query(OUTGOING_DOCUMENT_GET_ALL_QUERY, outgoingDocumentMapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<OutgoingDocument> getById(String id) {
        return jdbcTemplate.query(OUTGOING_DOCUMENT_GET_BY_ID_QUERY, outgoingDocumentMapper, id)
                .stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(OutgoingDocument outgoingDocument) {
        int count=super.update(outgoingDocument);
        return jdbcTemplate.update(OUTGOING_DOCUMENT_UPDATE_QUERY, outgoingDocument.getSender().getId().toString(),
                outgoingDocument.getDeliveryType().toString(), outgoingDocument.getId().toString()) + count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll(){
        jdbcTemplate.update(OUTGOING_DOCUMENT_DELETE_ALL_QUERY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(String id) throws DeleteByIdException {
        int deleteCount = jdbcTemplate.update(OUTGOING_DOCUMENT_DELETE_BY_ID_QUERY, id);
        if (deleteCount == 1) {
            return true;
        }
        throw new DeleteByIdException("OutgoingDocument");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(UUID uuid) {
        return jdbcTemplate.query(OUTGOING_DOCUMENT_GET_BY_ID_QUERY, outgoingDocumentMapper, uuid.toString())
                .stream().findFirst().isPresent();
    }
}
