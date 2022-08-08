package com.example.testproject1.service.dbservice.taskdocument;

import com.example.testproject1.dao.CrudRepositories;
import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class TaskDocumentServiceImpl implements CrudService<TaskDocument> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskDocumentServiceImpl.class);
    /**
     * Бин {@link CrudRepositories}
     */
    @Autowired
    @Qualifier("TaskDocumentRepository")
    private CrudRepositories taskDocumentRepository;
    /**
     * Лог при успешном сохранении
     */
    private final String CREATE_SUCCESS = "TaskDocument успешно сохранен";
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
     * Лог при успешном удалении всех записей
     */
    private final String DELETE_SUCCESS = "Записи из таблицы TaskDocument успешно удалены";
    /**
     * Лог при успешном удалении записи по id
     */
    private final String DELETE_BY_ID_SUCCESS = "Запись из таблицы TaskDocument успешно удалена";

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TaskDocument> create(TaskDocument taskDocument) {
        Optional<TaskDocument> optionalTaskDocument = taskDocumentRepository.create(taskDocument);
        if (optionalTaskDocument.isPresent()) {
            LOGGER.info(CREATE_SUCCESS);
            return Optional.ofNullable(taskDocument);
        }
        LOGGER.error(MessageFormat.format("Неудачная попытка сохранения TaskDocument c id {0}", taskDocument.getId().toString()));
        return Optional.empty();
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
    public Optional<TaskDocument> update(TaskDocument taskDocument) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у TaskDocument с id {0}", taskDocument.getId().toString()));
        int updateCount = taskDocumentRepository.update(taskDocument);
        if (updateCount >0) {
            LOGGER.info(UPDATE_SUCCESS);
            return Optional.ofNullable(taskDocument);
        }
        LOGGER.error(UPDATE_FAIL);
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        try {
            if (taskDocumentRepository.deleteAll()) {
                LOGGER.info(DELETE_SUCCESS);
            }
        } catch (DeletePoorlyException e) {
            LOGGER.error(e.toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(String id) {
        try {
            if (taskDocumentRepository.deleteById(id)) {
                LOGGER.info(DELETE_BY_ID_SUCCESS);
            }
        } catch (DeletePoorlyException e) {
            LOGGER.error(e.toString());
        }
    }
}
