package com.example.testproject1.service.dbservice.outgoingdocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.OutgoingDocument;
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
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций объектов класса {@link OutgoingDocument} к базе данных .
 *
 * @author smigranov
 */
@Transactional
@Service("OutgoingDocumentService")
public class OutgoingDocumentService implements CrudService<OutgoingDocument> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OutgoingDocumentService.class);
    /**
     * Бин {@link CrudRepository}
     */
    @Autowired
    private CrudRepository<OutgoingDocument> outgoingDocumentRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public OutgoingDocument create(OutgoingDocument outgoingDocument) throws DocflowRuntimeApplicationException {
        LOGGER.info("Попытка создания OutgoingDocument");
        return outgoingDocumentRepository.create(outgoingDocument);
    }
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<OutgoingDocument> getAll() {
        LOGGER.info("Попытка выдачи всех OutgoingDocument");
        return outgoingDocumentRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<OutgoingDocument> getById(String id) {
        LOGGER.info("Попытка получить OutgoingDocument по id");
        return outgoingDocumentRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OutgoingDocument update(OutgoingDocument outgoingDocument) throws DocflowRuntimeApplicationException {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у OutgoingDocument с id {0}", outgoingDocument.getId().toString()));
        return outgoingDocumentRepository.update(outgoingDocument);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info("Попытка удаления записей из таблицы OutgoingDocument");
        outgoingDocumentRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(String id) {
        LOGGER.info(MessageFormat.format("Попытка удаления OutgoingDocument с id {0}",id));
       outgoingDocumentRepository.deleteById(id);
    }
}
