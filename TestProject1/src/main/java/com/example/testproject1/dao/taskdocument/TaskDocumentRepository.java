package com.example.testproject1.dao.taskdocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.dao.basedocument.BaseDocumentRepositoryImpl;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.mapper.document.TaskDocumentMapper;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.PERSON_UPDATE_QUERY;
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
public class TaskDocumentRepository extends BaseDocumentRepositoryImpl implements CrudRepository<TaskDocument> {
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
     * Сервис для работы с {@link Person}
     */
    @Autowired
    private CrudService<Person> personService;

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDocument create(TaskDocument taskDocument) throws DocflowRuntimeApplicationException {
        if (taskDocument == null) {
            throw new DocflowRuntimeApplicationException("TaskDocument не может быть null");
        }
        super.create(taskDocument);
        jdbcTemplate.update(TASK_DOCUMENT_CREATE_QUERY, taskDocument.getId().toString(), taskDocument.getOutDate(),
                taskDocument.getExecPeriod(), taskDocument.getResponsible().getId().toString(),
                taskDocument.getSignOfControl(), taskDocument.getControlPerson().getId().toString());
        return taskDocument;
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
    public TaskDocument update(TaskDocument taskDocument) throws DocflowRuntimeApplicationException {
        super.update(taskDocument);
        if (taskDocument == null) {
            throw new DocflowRuntimeApplicationException("TaskDocument не может быть null");
        }
        jdbcTemplate.update(TASK_DOCUMENT_UPDATE_QUERY, taskDocument.getOutDate(), taskDocument.getExecPeriod(),
                taskDocument.getResponsible().getId().toString(), taskDocument.getSignOfControl(),
                taskDocument.getControlPerson().getId().toString(), taskDocument.getId().toString());
        return taskDocument;
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
    public void deleteById(String id) {
        jdbcTemplate.update(TASK_DOCUMENT_DELETE_BY_ID_QUERY, id);
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
