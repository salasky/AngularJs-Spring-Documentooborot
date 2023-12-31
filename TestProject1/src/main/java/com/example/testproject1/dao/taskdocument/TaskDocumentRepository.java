package com.example.testproject1.dao.taskdocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.dao.basedocument.AbstractBaseDocumentRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.sqlmapper.document.TaskDocumentMapper;
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
public class TaskDocumentRepository extends AbstractBaseDocumentRepository implements CrudRepository<TaskDocument> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TaskDocumentMapper taskDocumentMapper;

    @Autowired
    private CrudService<Person> personService;

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDocument create(TaskDocument taskDocument) {
        if (taskDocument == null) {
            throw new DocflowRuntimeApplicationException("TaskDocument не может быть null");
        }
        try {
            super.create(taskDocument);
            jdbcTemplate.update(TASK_DOCUMENT_CREATE_QUERY, taskDocument.getId().toString(), taskDocument.getOutDate(),
                    taskDocument.getExecPeriod(), taskDocument.getResponsible().getId().toString(),
                    taskDocument.getSignOfControl(), taskDocument.getControlPerson().getId().toString());
        } catch (DataAccessException e) {
            throw new DocflowRuntimeApplicationException("Ошибка сохранения TaskDocument", e);
        }
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
    public Optional<TaskDocument> getById(UUID id) {
        return jdbcTemplate.query(TASK_DOCUMENT_GET_BY_ID_QUERY, taskDocumentMapper, id.toString())
                .stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDocument update(TaskDocument taskDocument) {
        if (taskDocument == null) {
            throw new DocflowRuntimeApplicationException("TaskDocument не может быть null");
        }
        try {
            super.update(taskDocument);
            jdbcTemplate.update(TASK_DOCUMENT_UPDATE_QUERY, taskDocument.getOutDate(), taskDocument.getExecPeriod(),
                    taskDocument.getResponsible().getId().toString(), taskDocument.getSignOfControl(),
                    taskDocument.getControlPerson().getId().toString(), taskDocument.getId().toString());
        } catch (DataAccessException e) {
            throw new DocflowRuntimeApplicationException("Ошибка обновления TaskDocument", e);
        }
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
    public void deleteById(UUID id) {
        jdbcTemplate.update(TASK_DOCUMENT_DELETE_BY_ID_QUERY, id.toString());
        super.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(UUID uuid) {
        return jdbcTemplate.query(TASK_DOCUMENT_GET_BY_ID_QUERY, taskDocumentMapper, uuid.toString())
                .stream().findFirst().isPresent();
    }

    @Override
    public void saveAll(List<TaskDocument> entityList) throws BatchUpdateException {
        super.saveAllBase(entityList);
        jdbcTemplate.batchUpdate(TASK_DOCUMENT_CREATE_QUERY, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, entityList.get(i).getId().toString());
                ps.setTimestamp(2, entityList.get(i).getOutDate());
                ps.setString(3, entityList.get(i).getExecPeriod());
                ps.setString(4, entityList.get(i).getResponsible().getId().toString());
                ps.setBoolean(5, entityList.get(i).getSignOfControl());
                ps.setString(6, entityList.get(i).getControlPerson().getId().toString());
            }

            @Override
            public int getBatchSize() {
                return entityList.size();
            }
        });
    }
}
