package com.example.testproject1.service.dbservice.incomingdocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.BatchUpdateException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций объектов класса {@link  IncomingDocument} к базе данных .
 *
 * @author smigranov
 */
@Service("IncomingDocumentService")
public class IncomingDocumentService implements CrudService<IncomingDocument> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IncomingDocumentService.class);

    @Autowired
    private CrudRepository<IncomingDocument> incomingDocumentRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public IncomingDocument create(IncomingDocument incomingDocument) {
        LOGGER.info("Попытка создания IncomingDocument");
        if (incomingDocument.getId() == null) {
            incomingDocument.setId(UUID.randomUUID());
        }
        return incomingDocumentRepository.create(incomingDocument);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<IncomingDocument> getAll() {
        LOGGER.info("Попытка выдачи всех IncomingDocument");
        return incomingDocumentRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<IncomingDocument> getById(UUID id) {
        LOGGER.info("Попытка получить IncomingDocument по id");
        return incomingDocumentRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IncomingDocument update(IncomingDocument incomingDocument) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у IncomingDocument с id {0}", incomingDocument.getId().toString()));
        return incomingDocumentRepository.update(incomingDocument);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info("Попытка удаления записей из таблицы incoming_document");
        incomingDocumentRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(UUID id) {
        LOGGER.info(MessageFormat.format("Попытка удаления IncomingDocument с id {0}", id.toString()));
        incomingDocumentRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<IncomingDocument> entityList) {
        LOGGER.info("Попытка сохранения List<IncomingDocument> в таблицу IncomingDocument");
        entityList.stream().filter(entity -> entity.getId() == null).forEach(entity -> entity.setId(UUID.randomUUID()));
        try {
            incomingDocumentRepository.saveAll(entityList);
        } catch (BatchUpdateException e) {
            throw new DocflowRuntimeApplicationException("Ошибка сохранения", e);
        }
    }
}
