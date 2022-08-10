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
     * Лог при успешном сохранении
     */
    private final String CREATE_SUCCESS = "IncomingDocument успешно сохранен";
    /**
     * Лог при неудачном сохранении
     */
    private final String CREATE_FAIL = "Неудачная попытка сохранения IncomingDocument";
    /**
     * Лог при выдаче всех IncomingDocument
     */
    private final String GET_ALL_ATTEMPT = "Попытка выдачи всех IncomingDocument";
    /**
     * Лог при выдаче IncomingDocument по id
     */
    private final String GET_BY_ID_ATTEMPT = "Попытка получить IncomingDocument по id";
    /**
     * Лог при успешном обновлении
     */
    private final String UPDATE_SUCCESS = "IncomingDocument успешно обновлен";
    /**
     * Лог при неудачном обновлении
     */
    private final String UPDATE_FAIL = "Неудачная попытка обновления IncomingDocument";
    /**
     * Лог при попытке удаления всех записей
     */
    private final String DELETE_SUCCESS = "Попытка удаления записей из таблицы incoming_document";
    /**
     * Лог при успешном удалении записи по id
     */
    private final String DELETE_BY_ID_SUCCESS = "Запись из таблицы incoming_document успешно удалена";
    /**
     * Лог при неудачном удалении удалении записи по id
     */
    private final String DELETE_BY_ID_FAIL = "Запись из таблицы incoming_document не удалена";

    /**
     * {@inheritDoc}
     */
    @Override
    public IncomingDocument create(IncomingDocument incomingDocument) throws DocflowRuntimeApplicationException {
        IncomingDocument incomingDocumentDB = incomingDocumentRepository.create(incomingDocument);
        if (incomingDocumentDB != null) {
            LOGGER.info(CREATE_SUCCESS);
            return incomingDocumentDB;
        }
        throw new DocflowRuntimeApplicationException(CREATE_FAIL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IncomingDocument> getall() {
        LOGGER.info(GET_ALL_ATTEMPT);
        return incomingDocumentRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<IncomingDocument> getById(String id) {
        LOGGER.info(GET_BY_ID_ATTEMPT);
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
            LOGGER.info(UPDATE_SUCCESS);
            return incomingDocument;
        }
        throw new DocflowRuntimeApplicationException(UPDATE_FAIL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info(DELETE_SUCCESS);
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
            throw new DocflowRuntimeApplicationException(DELETE_BY_ID_FAIL);
        }
    }
}
