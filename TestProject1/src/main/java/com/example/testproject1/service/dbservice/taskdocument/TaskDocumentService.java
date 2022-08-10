package com.example.testproject1.service.dbservice.taskdocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.exception.UpdateException;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

/**
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций объектов класса {@link TaskDocument} к базе данных .
 *
 * @author smigranov
 */
@Service("TaskDocumentService")
public class TaskDocumentService implements CrudService<TaskDocument> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskDocumentService.class);
    /**
     * Бин {@link CrudRepository}
     */
    @Autowired
    private CrudRepository<TaskDocument> taskDocumentRepository;
    /**
     * Лог при успешном сохранении
     */
    private final String CREATE_SUCCESS = "TaskDocument успешно сохранен";
    /**
     * Лог при неудачном сохранении
     */
    private final String CREATE_FAIL="Неудачная попытка сохранения TaskDocument";
    /**
     * Лог при выдаче всех TaskDocument
     */
    private final String GET_ALL_ATTEMPT = "Попытка выдачи всех TaskDocument";
    /**
     * Лог при выдаче TaskDocument по id
     */
    private final String GET_BY_ID_ATTEMPT = "Попытка получить TaskDocument по id";
    /**
     * Лог при успешном обновлении
     */
    private final String UPDATE_SUCCESS = "TaskDocument успешно обновлен";
    /**
     * Лог при неудачном обновлении
     */
    private final String UPDATE_FAIL = "Неудачная попытка обновления TaskDocument";
    /**
     * Лог при попытке удаления всех записей
     */
    private final String DELETE_SUCCESS = "Попытка удаления записей из таблицы TaskDocument";
    /**
     * Лог при успешном удалении записи по id
     */
    private final String DELETE_BY_ID_SUCCESS = "Запись из таблицы TaskDocument успешно удалена";
    /**
     * Лог при неудачном удалении удалении записи по id
     */
    private final String DELETE_BY_ID_FAIL = "Запись из таблицы TaskDocument не удалена";

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDocument create(TaskDocument taskDocument) throws DocflowRuntimeApplicationException {
        TaskDocument taskDocumentDB = taskDocumentRepository.create(taskDocument);
        if (taskDocumentDB!=null) {
            LOGGER.info(CREATE_SUCCESS);
            return taskDocumentDB;
        }
        throw new DocflowRuntimeApplicationException(CREATE_FAIL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskDocument> getall() {
        LOGGER.info(GET_ALL_ATTEMPT);
        return taskDocumentRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TaskDocument> getById(String id) {
        LOGGER.info(GET_BY_ID_ATTEMPT);
        return taskDocumentRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDocument update(TaskDocument taskDocument) throws DocflowRuntimeApplicationException {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у TaskDocument с id {0}", taskDocument.getId().toString()));
        int updateCount = taskDocumentRepository.update(taskDocument);
        if (updateCount > 0) {
            LOGGER.info(UPDATE_SUCCESS);
            return taskDocument;
        }
        throw new DocflowRuntimeApplicationException(UPDATE_FAIL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info(DELETE_SUCCESS);
        taskDocumentRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(String id) throws DocflowRuntimeApplicationException {
        try {
            taskDocumentRepository.deleteById(id);
        } catch (DeleteByIdException e) {
            throw new DocflowRuntimeApplicationException(DELETE_BY_ID_FAIL);
        }
    }
}
