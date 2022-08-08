package com.example.testproject1.dao.incomingdocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.dao.basedocument.BaseDocumentRepository;
import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.mapper.document.IncomingDocumentMapper;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.testproject1.dao.queryholder.QueryHolder.INCOMING_DOCUMENT_CREATE_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.INCOMING_DOCUMENT_DELETE_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.INCOMING_DOCUMENT_DELETE_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.INCOMING_DOCUMENT_GET_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.INCOMING_DOCUMENT_GET_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.INCOMING_DOCUMENT_UPDATE_QUERY;

/**
 * Класс реализующий интерфейс {@link CrudRepository}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository("IncomingDocumentRepository")
public class IncomingDocumentRepositoryImpl implements CrudRepository<IncomingDocument> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IncomingDocumentRepositoryImpl.class);
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
     * Сервис для работы с {@link BaseDocument}
     */
    @Autowired
    private BaseDocumentRepository baseDocumentRepository;
    /**
     * Сервис для работы с {@link Person}
     */
    @Autowired
    private CrudService<Person> personService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<IncomingDocument> create(IncomingDocument incomingDocument) {
        if (incomingDocument != null) {
            try {
                BaseDocument baseDocument = new BaseDocument();
                baseDocument.setId(incomingDocument.getId());
                baseDocument.setName(incomingDocument.getName());
                baseDocument.setText(incomingDocument.getText());
                baseDocument.setRegNumber(incomingDocument.getRegNumber());
                baseDocument.setCreatingDate(incomingDocument.getCreatingDate());
                baseDocument.setAuthor(incomingDocument.getAuthor());
                baseDocumentRepository.create(baseDocument);
                personService.create(incomingDocument.getSender());
                personService.create(incomingDocument.getDestination());
                int countCreate = jdbcTemplate.update(INCOMING_DOCUMENT_CREATE_QUERY, incomingDocument.getId().toString(),
                        incomingDocument.getSender().getId().toString(),
                        incomingDocument.getDestination().getId().toString(),
                        incomingDocument.getNumber(), incomingDocument.getDateOfRegistration());
                if (countCreate == 1) {
                    return Optional.ofNullable(incomingDocument);
                }
                return Optional.empty();
            } catch (DataIntegrityViolationException ex) {
                throw new RuntimeException(ex);
            }
        } else throw new IllegalArgumentException("IncomingDocument не может быть null");
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
        int baseDocumentCount = baseDocumentRepository.update(incomingDocument);
        return jdbcTemplate.update(INCOMING_DOCUMENT_UPDATE_QUERY, incomingDocument.getSender().getId().toString(),
                incomingDocument.getDestination().getId().toString(), incomingDocument.getNumber(),
                incomingDocument.getDateOfRegistration(), incomingDocument.getId().toString()) + baseDocumentCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAll() throws DeletePoorlyException {
        int deleteCount = jdbcTemplate.update(INCOMING_DOCUMENT_DELETE_ALL_QUERY);
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
        int deleteCount = jdbcTemplate.update(INCOMING_DOCUMENT_DELETE_BY_ID_QUERY, id);
        if (deleteCount == 1) {
            return true;
        }
        throw new DeletePoorlyException();
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

