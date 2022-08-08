package com.example.testproject1.dao.outgoingdocument;

import com.example.testproject1.dao.CrudRepositories;
import com.example.testproject1.dao.basedocument.BaseDocumentRepository;
import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.mapper.document.OutgoingDocumentMapper;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.testproject1.dao.queryholder.QueryHolder.OUTGOING_DOCUMENT_CREATE_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.OUTGOING_DOCUMENT_DELETE_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.OUTGOING_DOCUMENT_DELETE_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.OUTGOING_DOCUMENT_GET_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.OUTGOING_DOCUMENT_GET_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.OUTGOING_DOCUMENT_UPDATE_QUERY;

/**
 * Класс реализующий интерфейс {@link CrudRepositories}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository("OutgoingDocumentRepository")
public class OutgoingDocumentRepositoryImpl implements CrudRepositories<OutgoingDocument> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OutgoingDocumentRepositoryImpl.class);
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
     * Сервис для работы с {@link BaseDocument}
     */
    @Autowired
    private BaseDocumentRepository baseDocumentRepository;
    /**
     * Сервис для работы с {@link Person}
     */
    @Autowired
    @Qualifier("PersonService")
    private CrudService personService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<OutgoingDocument> create(OutgoingDocument outgoingDocument) {
        if (outgoingDocument != null) {
            try {
                BaseDocument baseDocument = new BaseDocument();
                baseDocument.setId(outgoingDocument.getId());
                baseDocument.setName(outgoingDocument.getName());
                baseDocument.setText(outgoingDocument.getText());
                baseDocument.setRegNumber(outgoingDocument.getRegNumber());
                baseDocument.setCreatingDate(outgoingDocument.getCreatingDate());
                baseDocument.setAuthor(outgoingDocument.getAuthor());
                baseDocumentRepository.create(baseDocument);
                personService.create(outgoingDocument.getSender());
                int createCount = jdbcTemplate.update(OUTGOING_DOCUMENT_CREATE_QUERY, outgoingDocument.getId().toString(),
                        outgoingDocument.getSender().getId().toString(),
                        outgoingDocument.getDeliveryType().toString());
                if (createCount == 1) {
                    return Optional.ofNullable(outgoingDocument);
                }
                return Optional.empty();
            } catch (DataIntegrityViolationException ex) {
                throw new RuntimeException(ex);
            }
        } else throw new IllegalArgumentException("OutgoingDocument не может быть null");
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
        int baseDocumentCount = baseDocumentRepository.update(outgoingDocument);
        return jdbcTemplate.update(OUTGOING_DOCUMENT_UPDATE_QUERY, outgoingDocument.getSender().getId().toString(),
                outgoingDocument.getDeliveryType().toString(), outgoingDocument.getId().toString()) + baseDocumentCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAll() throws DeletePoorlyException {
        int deleteCount = jdbcTemplate.update(OUTGOING_DOCUMENT_DELETE_ALL_QUERY);
        if (deleteCount > 0) {
            return true;
        }
        throw new DeletePoorlyException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(String id) throws DeletePoorlyException {
        int deleteCount = jdbcTemplate.update(OUTGOING_DOCUMENT_DELETE_BY_ID_QUERY, id);
        if (deleteCount == 1) {
            return true;
        }
        throw new DeletePoorlyException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(String uuid) {
        return jdbcTemplate.query(OUTGOING_DOCUMENT_GET_BY_ID_QUERY, outgoingDocumentMapper, uuid)
                .stream().findFirst().isPresent();
    }
}
