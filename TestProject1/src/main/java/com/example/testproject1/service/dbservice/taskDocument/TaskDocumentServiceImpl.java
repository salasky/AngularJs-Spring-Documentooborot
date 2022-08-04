package com.example.testproject1.service.dbservice.taskDocument;

import com.example.testproject1.dao.taskdocument.TaskDocumentRepository;
import com.example.testproject1.model.document.TaskDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class TaskDocumentServiceImpl implements TaskDocumentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskDocumentServiceImpl.class);

    @Autowired
    private TaskDocumentRepository taskDocumentRepository;

    @Override
    public Optional<TaskDocument> create(TaskDocument taskDocument) {
        LOGGER.info("Попытка создания TaskDocument");
        int updateCount = taskDocumentRepository.create(taskDocument);
        if (updateCount == 1) {
            LOGGER.info("TaskDocument успешно сохранен");
            return Optional.ofNullable(taskDocument);
        }
        LOGGER.error(MessageFormat.format("Неудачная попытка сохранения TaskDocument c id {0}", taskDocument.getId().toString()));
        return null;
    }

    @Override
    public List<TaskDocument> getall() {
        LOGGER.info("Попытка выдачи всех Department");
        return taskDocumentRepository.getAll();
    }

    @Override
    public Optional<TaskDocument> getById(String id) {
        LOGGER.info("Попытка получить TaskDocument по id");
        return taskDocumentRepository.getById(id);
    }

    @Override
    public Optional<TaskDocument> update(TaskDocument taskDocument) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у TaskDocument с id {0}", taskDocument.getId().toString()));
        int updateCount = taskDocumentRepository.update(taskDocument);
        if (updateCount == 1) {
            LOGGER.info("TaskDocument успешно обновлен");
            return Optional.ofNullable(taskDocument);
        }
        LOGGER.error("Неудачная попытка обновления TaskDocument");
        return null;
    }

    @Override
    public String deleteAll() {
        LOGGER.info("Попытка удалить все записи в таблице task_document");
        int count = taskDocumentRepository.deleteAll();
        if (count > 0) {
            LOGGER.info("Записи из таблицы task_document успешно удалены");
            return "Записи из таблицы task_document успешно удалены";
        }
        LOGGER.error("Не удачная попытка удаления записей из таблицы task_document");
        return "Не удачная попытка удаления записей из таблицы task_document";
    }

    @Override
    public String deleteById(String id) {
        LOGGER.info("Попытка удалить запись из таблицы task_document");
        int count = taskDocumentRepository.deleteById(id);
        if (count > 0) {
            LOGGER.info("Запись из таблицы task_document успешно удалена");
            return "Запись из таблицы task_document успешно удалена";
        }
        LOGGER.error("Не удачная попытка удаления записи из таблицы task_document");
        return "Не удачная попытка удаления записи из таблицы task_document";
    }
}
