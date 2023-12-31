package com.example.testproject1.service.dbservice.outgoingdocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.OutgoingDocument;
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
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций объектов класса {@link OutgoingDocument} к базе данных .
 *
 * @author smigranov
 */
@Transactional
@Service("OutgoingDocumentService")
public class OutgoingDocumentService implements CrudService<OutgoingDocument> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OutgoingDocumentService.class);

    @Autowired
    private CrudRepository<OutgoingDocument> outgoingDocumentRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public OutgoingDocument create(OutgoingDocument outgoingDocument) {
        LOGGER.info("Попытка создания OutgoingDocument");
        if (outgoingDocument.getId() == null) {
            outgoingDocument.setId(UUID.randomUUID());
        }
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
    public Optional<OutgoingDocument> getById(UUID id) {
        LOGGER.info("Попытка получить OutgoingDocument по id");
        return outgoingDocumentRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OutgoingDocument update(OutgoingDocument outgoingDocument) {
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
    public void deleteById(UUID id) {
        LOGGER.info(MessageFormat.format("Попытка удаления OutgoingDocument с id {0}", id.toString()));
        outgoingDocumentRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<OutgoingDocument> entityList) {
        LOGGER.info("Попытка сохранения List<OutgoingDocument> в таблицу OutgoingDocument");
        entityList.stream().filter(entity -> entity.getId() == null).forEach(entity -> entity.setId(UUID.randomUUID()));
        try {
            outgoingDocumentRepository.saveAll(entityList);
        } catch (BatchUpdateException e) {
            throw new DocflowRuntimeApplicationException("Ошибка сохранения", e);
        }
    }
}
