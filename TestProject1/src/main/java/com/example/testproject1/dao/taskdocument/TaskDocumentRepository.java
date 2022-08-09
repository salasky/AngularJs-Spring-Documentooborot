package com.example.testproject1.dao.taskdocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.dao.basedocument.BaseDocumentRepository;
import com.example.testproject1.mapper.document.TaskDocumentMapper;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.TaskDocument;
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

import static com.example.testproject1.queryholder.taskdocumentquery.TaskDocumentQueryHolder.TASK_DOCUMENT_CREATE_QUERY;
import static com.example.testproject1.queryholder.taskdocumentquery.TaskDocumentQueryHolder.TASK_DOCUMENT_DELETE_ALL_QUERY;
import static com.example.testproject1.queryholder.taskdocumentquery.TaskDocumentQueryHolder.TASK_DOCUMENT_DELETE_BY_ID_QUERY;
import static com.example.testproject1.queryholder.taskdocumentquery.TaskDocumentQueryHolder.TASK_DOCUMENT_GET_ALL_QUERY;
import static com.example.testproject1.queryholder.taskdocumentquery.TaskDocumentQueryHolder.TASK_DOCUMENT_GET_BY_ID_QUERY;
import static com.example.testproject1.queryholder.taskdocumentquery.TaskDocumentQueryHolder.TASK_DOCUMENT_UPDATE_QUERY;

/**
 * Класс реализующий интерфейс {@link CrudRepository}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository("TaskDocumentRepository")
public class TaskDocumentRepository implements CrudRepository<TaskDocument> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskDocumentRepository.class);
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
    public TaskDocument create(TaskDocument taskDocument) {
        if (taskDocument != null) {
                BaseDocument baseDocument = new BaseDocument();
                baseDocument.setId(taskDocument.getId());
                baseDocument.setName(taskDocument.getName());
                baseDocument.setText(taskDocument.getText());
                baseDocument.setRegNumber(taskDocument.getRegNumber());
                baseDocument.setCreatingDate(taskDocument.getCreatingDate());
                baseDocument.setAuthor(taskDocument.getAuthor());
                baseDocumentRepository.create(baseDocument);
                personService.create(taskDocument.getControlPerson());
                personService.create(taskDocument.getResponsible());
                jdbcTemplate.update(TASK_DOCUMENT_CREATE_QUERY, taskDocument.getId().toString(), taskDocument.getOutDate()
                        , taskDocument.getExecPeriod(), taskDocument.getResponsible().getId().toString(),
                        taskDocument.getSignOfControl(), taskDocument.getControlPerson().getId().toString());
                return taskDocument;
        } else throw new IllegalArgumentException("TaskDocument не может быть null");
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
    public int update(TaskDocument taskDocument) {
        int baseDocumentCount = baseDocumentRepository.update(taskDocument);
        return jdbcTemplate.update(TASK_DOCUMENT_UPDATE_QUERY, taskDocument.getOutDate(), taskDocument.getExecPeriod(),
                taskDocument.getResponsible().getId().toString(), taskDocument.getSignOfControl(),
                taskDocument.getControlPerson().getId().toString(), taskDocument.getId().toString()) + baseDocumentCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        jdbcTemplate.update(TASK_DOCUMENT_DELETE_ALL_QUERY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(String id) {
        int deleteCount = jdbcTemplate.update(TASK_DOCUMENT_DELETE_BY_ID_QUERY, id);
        if (deleteCount == 1) {
            return true;
        }
        throw new RuntimeException(
                MessageFormat.format("Ошибка удаления TaskDocument с id {0}",id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(UUID uuid) {
        return jdbcTemplate.query(TASK_DOCUMENT_GET_BY_ID_QUERY, taskDocumentMapper, uuid.toString())
                .stream().findFirst().isPresent();
    }
}