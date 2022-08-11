package com.example.testproject1.service.dbservice.incomingdocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

/**
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций объектов класса {@link  IncomingDocument} к базе данных .
 *
 * @author smigranov
 */
@Service("IncomingDocumentService")
public class IncomingDocumentService implements CrudService<IncomingDocument> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IncomingDocumentService.class);
    /**
     * Бин {@link CrudRepository}
     */
    @Autowired
    private CrudRepository<IncomingDocument> incomingDocumentRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public IncomingDocument create(IncomingDocument incomingDocument) throws DocflowRuntimeApplicationException {
        IncomingDocument incomingDocumentDB = incomingDocumentRepository.create(incomingDocument);
        if (incomingDocumentDB != null) {
            LOGGER.info("IncomingDocument успешно сохранен");
            return incomingDocumentDB;
        }
        throw new DocflowRuntimeApplicationException("Неудачная попытка сохранения IncomingDocument");
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<IncomingDocument> getall() {
        LOGGER.info("Попытка выдачи всех IncomingDocument");
        return incomingDocumentRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<IncomingDocument> getById(String id) {
        LOGGER.info("Попытка получить IncomingDocument по id");
        return incomingDocumentRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IncomingDocument update(IncomingDocument incomingDocument) throws DocflowRuntimeApplicationException {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у IncomingDocument с id {0}", incomingDocument.getId().toString()));
        int updateCount = incomingDocumentRepository.update(incomingDocument);
        if (updateCount > 0) {
            LOGGER.info("IncomingDocument успешно обновлен");
            return incomingDocument;
        }
        throw new DocflowRuntimeApplicationException("Неудачная попытка обновления IncomingDocument");
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
    public void deleteById(String id) throws DocflowRuntimeApplicationException {
        try {
            incomingDocumentRepository.deleteById(id);
        } catch (DeleteByIdException e) {
            throw new DocflowRuntimeApplicationException("Запись из таблицы incoming_document не удалена");
        }
    }
}
