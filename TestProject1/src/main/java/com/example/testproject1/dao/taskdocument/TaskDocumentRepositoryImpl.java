package com.example.testproject1.dao.taskdocument;

import com.example.testproject1.dao.taskdocument.mapper.TaskDocumentMapper;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.baseDocument.BaseDocumentService;
import com.example.testproject1.service.dbservice.person.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.testproject1.dao.queryholder.QueryHolder.TASK_DOCUMENT_CREATE_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.TASK_DOCUMENT_DELETE_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.TASK_DOCUMENT_DELETE_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.TASK_DOCUMENT_GET_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.TASK_DOCUMENT_GET_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.TASK_DOCUMENT_UPDATE_QUERY;

/**
 * Класс реализующий интерфейс {@link TaskDocumentRepository}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository
public class TaskDocumentRepositoryImpl implements TaskDocumentRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskDocumentRepositoryImpl.class);
    /**
     * Бин JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * Маппер для извлечения {@link TaskDocument}
     */
    @Autowired
    private TaskDocumentMapper taskDocumentMapper;
    /**
     * Сервис для работы с {@link BaseDocument}
     */
    @Autowired
    private BaseDocumentService baseDocumentService;
    /**
     * Сервис для работы с {@link Person}
     */
    @Autowired
    private PersonService personService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer create(TaskDocument taskDocument) {
        try {
            BaseDocument baseDocument = new BaseDocument();
            baseDocument.setId(taskDocument.getId());
            baseDocument.setName(taskDocument.getName());
            baseDocument.setText(taskDocument.getText());
            baseDocument.setRegNumber(taskDocument.getRegNumber());
            baseDocument.setCreatingDate(taskDocument.getCreatingDate());
            baseDocument.setAuthor(taskDocument.getAuthor());
            baseDocumentService.create(baseDocument);
            personService.create(taskDocument.getControlPerson());
            personService.create(taskDocument.getResponsible());
            return jdbcTemplate.update(TASK_DOCUMENT_CREATE_QUERY, taskDocument.getId().toString(), taskDocument.getOutDate()
                    , taskDocument.getExecPeriod(), taskDocument.getResponsible().getId().toString(),
                    taskDocument.getSignOfControl(), taskDocument.getControlPerson().getId().toString());
        } catch (DataIntegrityViolationException ex) {
            LOGGER.error(ex.toString());
            return 0;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskDocument> getAll() {
        return jdbcTemplate.query(TASK_DOCUMENT_GET_ALL_QUERY, taskDocumentMapper);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TaskDocument> getById(String id) {
        return jdbcTemplate.query(TASK_DOCUMENT_GET_BY_ID_QUERY, taskDocumentMapper, id)
                .stream().findFirst();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer update(TaskDocument taskDocument) {
        return jdbcTemplate.update(TASK_DOCUMENT_UPDATE_QUERY, taskDocument.getOutDate(), taskDocument.getExecPeriod(),
                taskDocument.getResponsible().getId().toString(), taskDocument.getSignOfControl(),
                taskDocument.getControlPerson().getId().toString(), taskDocument.getId().toString());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteAll() {
        return jdbcTemplate.update(TASK_DOCUMENT_DELETE_ALL_QUERY);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteById(String id) {
        int update = jdbcTemplate.update(TASK_DOCUMENT_DELETE_BY_ID_QUERY, id);
        return update;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(String uuid) {
        Optional<TaskDocument> taskDocument = jdbcTemplate.query(TASK_DOCUMENT_GET_BY_ID_QUERY, taskDocumentMapper, uuid).stream().findFirst();
        return taskDocument.isPresent();
    }
}
