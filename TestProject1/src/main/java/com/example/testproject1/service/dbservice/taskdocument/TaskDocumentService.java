package com.example.testproject1.service.dbservice.taskdocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public TaskDocument create(TaskDocument taskDocument) throws DocflowRuntimeApplicationException {
        LOGGER.info("Попытка создания TaskDocument");
        return taskDocumentRepository.create(taskDocument);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<TaskDocument> getAll() {
        LOGGER.info("Попытка выдачи всех TaskDocument");
        return taskDocumentRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TaskDocument> getById(UUID id) {
        LOGGER.info("Попытка получить TaskDocument по id");
        return taskDocumentRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDocument update(TaskDocument taskDocument) throws DocflowRuntimeApplicationException {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у TaskDocument с id {0}", taskDocument.getId().toString()));
        return taskDocumentRepository.update(taskDocument);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info("Попытка удаления записей из таблицы TaskDocument");
        taskDocumentRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(UUID id) {
        LOGGER.info(MessageFormat.format("Попытка удаления TaskDocument с id {0}", id.toString()));
        taskDocumentRepository.deleteById(id);
    }

    @Override
    public void saveALL(List<TaskDocument> entityList) {
        LOGGER.info("Попытка сохранения List<TaskDocument> в таблицу TaskDocument");
        taskDocumentRepository.saveAll(entityList);
    }
}
