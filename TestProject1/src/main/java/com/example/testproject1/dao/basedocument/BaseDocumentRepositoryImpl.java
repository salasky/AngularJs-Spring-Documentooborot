package com.example.testproject1.dao.basedocument;

import com.example.testproject1.dao.basedocument.mapper.BaseDocumentMapper;
import com.example.testproject1.dao.queryholder.QueryHolder;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.person.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.testproject1.dao.queryholder.QueryHolder.BASE_DOCUMENT_CREATE_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.BASE_DOCUMENT_DELETE_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.BASE_DOCUMENT_DELETE_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.BASE_DOCUMENT_EXIST_BY_REG_NUMBER_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.BASE_DOCUMENT_GET_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.BASE_DOCUMENT_GET_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.BASE_DOCUMENT_UPDATE_QUERY;

/**
 * Класс реализующий интерфейс {@link BaseDocumentRepository}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository
public class BaseDocumentRepositoryImpl implements BaseDocumentRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDocumentRepositoryImpl.class);
    /**
     * Бин JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * Маппер для извлечения {@link BaseDocument}
     */
    @Autowired
    private BaseDocumentMapper baseDocumentMapper;
    /**
     * Сервис для работы с {@link Person}
     */
    @Autowired
    private PersonService personService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<BaseDocument> create(BaseDocument baseDocument) {
        if(baseDocument!=null) {
            try {
                personService.create(baseDocument.getAuthor());
                int countCreate = jdbcTemplate.update(BASE_DOCUMENT_CREATE_QUERY, baseDocument.getId().toString(), baseDocument.getName(), baseDocument.getText(),
                        baseDocument.getRegNumber(), baseDocument.getCreatingDate(), baseDocument.getAuthor().getId().toString());
                if (countCreate == 1) {
                    return Optional.of(baseDocument);
                }
                return Optional.empty();
            } catch (DataIntegrityViolationException ex) {
                throw new RuntimeException(ex);
            }
        }
        else throw new IllegalArgumentException("BaseDocument не может быть null");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BaseDocument> getAll() {
        return jdbcTemplate.query(BASE_DOCUMENT_GET_ALL_QUERY, baseDocumentMapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<BaseDocument> getById(String id) {
        return jdbcTemplate.query(BASE_DOCUMENT_GET_BY_ID_QUERY, baseDocumentMapper, id).stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer update(BaseDocument baseDocument) {
        return jdbcTemplate.update(BASE_DOCUMENT_UPDATE_QUERY, baseDocument.getName(), baseDocument.getText(),
                baseDocument.getRegNumber(), baseDocument.getCreatingDate(), baseDocument.getAuthor().getId().toString(),
                baseDocument.getId().toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteAll() {
        return jdbcTemplate.update(BASE_DOCUMENT_DELETE_ALL_QUERY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteById(String id) {
        int update = jdbcTemplate.update(BASE_DOCUMENT_DELETE_BY_ID_QUERY, id);
        return update;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existByRegNumber(Long regNumber) {
        Optional<BaseDocument> baseDocumentOptional = jdbcTemplate.query(BASE_DOCUMENT_EXIST_BY_REG_NUMBER_QUERY, baseDocumentMapper, regNumber).stream().findFirst();
        return baseDocumentOptional.isPresent();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(String uuid) {
        Optional<BaseDocument> baseDocumentOptional = jdbcTemplate.query(BASE_DOCUMENT_GET_BY_ID_QUERY, baseDocumentMapper, uuid).stream().findFirst();
        return baseDocumentOptional.isPresent();
    }
}
