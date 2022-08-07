package com.example.testproject1.service.dbservice.incomingdocument;

import com.example.testproject1.dao.CrudRepositories;
import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.model.document.IncomingDocument;
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
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций объектов класса {@link  IncomingDocument} к базе данных .
 *
 * @author smigranov
 */
@Service("IncomingDocumentService")
public class IncomingDocumentServiceImpl implements CrudService<IncomingDocument> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IncomingDocumentServiceImpl.class);
    /**
     * Бин {@link CrudRepositories}
     */
    @Autowired
    @Qualifier("IncomingDocumentRepository")
    private CrudRepositories incomingDocumentRepository;
    /**
     * Лог при успешном сохранении
     */
    private final String CREATE_SUCCESS="IncomingDocument успешно сохранен";
    /**
     * Лог при неудачном сохранении
     */
    private final String CREATE_FAIL="Неудачная попытка сохранения IncomingDocument";
    /**
     * Лог при выдаче всех IncomingDocument
     */
    private final String GET_ALL_ATTEMPT="Попытка выдачи всех IncomingDocument";
    /**
     * Лог при выдаче IncomingDocument по id
     */
    private final String GET_BY_ID_ATTEMPT="Попытка получить IncomingDocument по id";
    /**
     * Лог при успешном обновлении
     */
    private final String UPDATE_SUCCESS="IncomingDocument успешно обновлен";
    /**
     * Лог при неудачном обновлении
     */
    private final String UPDATE_FAIL="Неудачная попытка обновления IncomingDocument";
    /**
     * Лог при успешном удалении всех записей
     */
    private final String DELETE_SUCCESS="Записи из таблицы incoming_document успешно удалены";
    /**
     * Лог при успешном удалении записи по id
     */
    private final String DELETE_BY_ID_SUCCESS="Запись из таблицы incoming_document успешно удалена";

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<IncomingDocument> create(IncomingDocument incomingDocument) {
        Optional<IncomingDocument> incomingDocumentOptional = incomingDocumentRepository.create(incomingDocument);
        if (incomingDocumentOptional.isPresent()) {
            LOGGER.info(CREATE_SUCCESS);
            return Optional.ofNullable(incomingDocument);
        }
        LOGGER.error(CREATE_FAIL);
        return Optional.empty();
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
    public Optional<IncomingDocument> update(IncomingDocument incomingDocument) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у IncomingDocument с id {0}", incomingDocument.getId().toString()));
        int updateCount = incomingDocumentRepository.update(incomingDocument);
        if (updateCount == 1) {
            LOGGER.info(UPDATE_SUCCESS);
            return Optional.ofNullable(incomingDocument);
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
            if (incomingDocumentRepository.deleteAll()) {
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
            if (incomingDocumentRepository.deleteById(id)) {
                LOGGER.info(DELETE_BY_ID_SUCCESS);
            }
        } catch (DeletePoorlyException e) {
            LOGGER.error(e.toString());
        }
    }
}
