package com.example.testproject1.dao.outgoingdocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.dao.basedocument.AbstractBaseDocumentRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.mapper.document.OutgoingDocumentMapper;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.testproject1.queryholder.incomingdocumentquery.IncomingDocumentQueryHolder.INCOMING_DOCUMENT_CREATE_QUERY;
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
public class OutgoingDocumentRepositoryAbstract extends AbstractBaseDocumentRepository implements CrudRepository<OutgoingDocument> {

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
        if (outgoingDocument == null) {
            throw new DocflowRuntimeApplicationException("OutgoingDocument не может быть null");
        }
        super.create(outgoingDocument);
        jdbcTemplate.update(OUTGOING_DOCUMENT_CREATE_QUERY, outgoingDocument.getId().toString(),
                outgoingDocument.getSender().getId().toString(),
                outgoingDocument.getDeliveryType().toString());
        return outgoingDocument;
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
    public Optional<OutgoingDocument> getById(UUID id) {
        return jdbcTemplate.query(OUTGOING_DOCUMENT_GET_BY_ID_QUERY, outgoingDocumentMapper, id.toString())
                .stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OutgoingDocument update(OutgoingDocument outgoingDocument) {
        super.update(outgoingDocument);
        if (outgoingDocument == null) {
            throw new DocflowRuntimeApplicationException("OutgoingDocument не может быть null");
        }
        jdbcTemplate.update(OUTGOING_DOCUMENT_UPDATE_QUERY, outgoingDocument.getSender().getId().toString(),
                outgoingDocument.getDeliveryType().toString(), outgoingDocument.getId().toString());
        return outgoingDocument;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        jdbcTemplate.update(OUTGOING_DOCUMENT_DELETE_ALL_QUERY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(UUID id) {
        jdbcTemplate.update(OUTGOING_DOCUMENT_DELETE_BY_ID_QUERY, id.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(UUID uuid) {
        return jdbcTemplate.query(OUTGOING_DOCUMENT_GET_BY_ID_QUERY, outgoingDocumentMapper, uuid.toString())
                .stream().findFirst().isPresent();
    }

    @Override
    public void saveAll(List<OutgoingDocument> entityList) {
        super.saveAllBase(entityList);
        jdbcTemplate.batchUpdate(OUTGOING_DOCUMENT_CREATE_QUERY, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, entityList.get(i).getId().toString());
                ps.setString(2, entityList.get(i).getSender().getId().toString());
                ps.setString(3, entityList.get(i).getDeliveryType().toString());
            }
            @Override
            public int getBatchSize() {
                return entityList.size();
            }
        });
    }
}
