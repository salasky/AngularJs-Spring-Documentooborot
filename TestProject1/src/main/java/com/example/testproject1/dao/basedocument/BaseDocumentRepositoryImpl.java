package com.example.testproject1.dao.basedocument;

import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.mapper.document.BaseDocumentMapper;
import com.example.testproject1.model.document.BaseDocument;
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
    @Qualifier("PersonService")
    private CrudService personService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<BaseDocument> create(BaseDocument baseDocument) {
        if (baseDocument != null) {
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
        } else throw new IllegalArgumentException("BaseDocument не может быть null");
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
    public boolean deleteAll() throws DeletePoorlyException {
        int deleteCount = jdbcTemplate.update(BASE_DOCUMENT_DELETE_ALL_QUERY);
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
        int deleteCount = jdbcTemplate.update(BASE_DOCUMENT_DELETE_BY_ID_QUERY, id);
        if (deleteCount == 1) {
            return true;
        }
        throw new DeletePoorlyException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existByRegNumber(Long regNumber) {
        return jdbcTemplate.query(BASE_DOCUMENT_EXIST_BY_REG_NUMBER_QUERY, baseDocumentMapper, regNumber)
                .stream().findFirst().isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(String uuid) {
        return jdbcTemplate.query(BASE_DOCUMENT_GET_BY_ID_QUERY, baseDocumentMapper, uuid)
                .stream().findFirst().isPresent();
    }
}
