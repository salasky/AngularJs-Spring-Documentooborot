package com.example.testproject1.dao.incomingdocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.dao.basedocument.AbstractBaseDocumentRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.service.sqlmapper.document.IncomingDocumentMapper;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.BatchUpdateException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class IncomingDocumentRepository extends AbstractBaseDocumentRepository implements CrudRepository<IncomingDocument> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IncomingDocumentMapper incomingDocumentMapper;

    @Autowired
    private CrudService<Person> personService;

    /**
     * {@inheritDoc}
     */
    @Override
    public IncomingDocument create(IncomingDocument incomingDocument) {
        if (incomingDocument == null) {
            throw new DocflowRuntimeApplicationException("IncomingDocument не может быть null");
        }
        try {
            super.create(incomingDocument);
            jdbcTemplate.update(INCOMING_DOCUMENT_CREATE_QUERY, incomingDocument.getId().toString(),
                    incomingDocument.getSender().getId().toString(),
                    incomingDocument.getDestination().getId().toString(),
                    incomingDocument.getNumber(), incomingDocument.getDateOfRegistration());
        } catch (DataAccessException e) {
            throw new DocflowRuntimeApplicationException("Ошибка сохранения", e);
        }
        return incomingDocument;
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
    public Optional<IncomingDocument> getById(UUID id) {
        return jdbcTemplate.query(INCOMING_DOCUMENT_GET_BY_ID_QUERY, incomingDocumentMapper, id.toString())
                .stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IncomingDocument update(IncomingDocument incomingDocument) {
        if (incomingDocument == null) {
            throw new DocflowRuntimeApplicationException("IncomingDocument не может быть null");
        }
        try {
            super.update(incomingDocument);
            jdbcTemplate.update(INCOMING_DOCUMENT_UPDATE_QUERY, incomingDocument.getSender().getId().toString(),
                    incomingDocument.getDestination().getId().toString(), incomingDocument.getNumber(),
                    incomingDocument.getDateOfRegistration(), incomingDocument.getId().toString());
        } catch (DataAccessException e) {
            throw new DocflowRuntimeApplicationException("Ошибка обновления", e);
        }
        return incomingDocument;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        jdbcTemplate.update(INCOMING_DOCUMENT_DELETE_ALL_QUERY);
        super.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(UUID id) {
        jdbcTemplate.update(INCOMING_DOCUMENT_DELETE_BY_ID_QUERY, id.toString());
        super.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(UUID uuid) {
        return jdbcTemplate.query(INCOMING_DOCUMENT_GET_BY_ID_QUERY, incomingDocumentMapper, uuid.toString())
                .stream().findFirst().isPresent();
    }

    @Override
    public void saveAll(List<IncomingDocument> entityList) throws BatchUpdateException {
        super.saveAllBase(entityList);
        jdbcTemplate.batchUpdate(INCOMING_DOCUMENT_CREATE_QUERY, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, entityList.get(i).getId().toString());
                ps.setString(2, entityList.get(i).getSender().getId().toString());
                ps.setString(3, entityList.get(i).getDestination().getId().toString());
                ps.setLong(4, entityList.get(i).getNumber());
                ps.setTimestamp(5, entityList.get(i).getDateOfRegistration());
            }

            @Override
            public int getBatchSize() {
                return entityList.size();
            }
        });
    }
}

