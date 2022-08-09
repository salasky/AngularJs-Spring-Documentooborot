package com.example.testproject1.service.dbservice.outgoingdocument;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

/**
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций объектов класса {@link OutgoingDocument} к базе данных .
 *
 * @author smigranov
 */
@Service("OutgoingDocumentService")
public class OutgoingDocumentServiceImpl implements CrudService<OutgoingDocument> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OutgoingDocumentServiceImpl.class);
    /**
     * Бин {@link CrudRepository}
     */
    @Autowired
    private CrudRepository<OutgoingDocument> outgoingDocumentRepository;
    /**
     * Лог при успешном сохранении
     */
    private final String CREATE_SUCCESS = "OutgoingDocument успешно сохранен";
    /**
     * Лог при неудачном сохранении
     */
    private final String CREATE_FAIL = "Неудачная попытка сохранения OutgoingDocument";
    /**
     * Лог при выдаче всех OutgoingDocument
     */
    private final String GET_ALL_ATTEMPT = "Попытка выдачи всех OutgoingDocument";
    /**
     * Лог при выдаче OutgoingDocument по id
     */
    private final String GET_BY_ID_ATTEMPT = "Попытка получить OutgoingDocument по id";
    /**
     * Лог при успешном обновлении
     */
    private final String UPDATE_SUCCESS = "OutgoingDocument успешно обновлен";
    /**
     * Лог при неудачном обновлении
     */
    private final String UPDATE_FAIL = "Неудачная попытка обновления OutgoingDocument";
    /**
     * Лог при попытке удаления всех записей
     */
    private final String DELETE_SUCCESS = "Попытка удаления записей из таблицы OutgoingDocument";
    /**
     * Лог при успешном удалении записи по id
     */
    private final String DELETE_BY_ID_SUCCESS = "Запись из таблицы OutgoingDocument успешно удалена";
    /**
     * Лог при неудачном удалении удалении записи по id
     */
    private final String DELETE_BY_ID_FAIL = "Запись из таблицы OutgoingDocument не удалена";

    /**
     * {@inheritDoc}
     */
    @Override
    public OutgoingDocument create(OutgoingDocument outgoingDocument) {
        OutgoingDocument outgoingDocumentDB = outgoingDocumentRepository.create(outgoingDocument);
        if (outgoingDocumentDB != null) {
            LOGGER.info(CREATE_SUCCESS);
            return outgoingDocumentDB;
        }
        LOGGER.error(CREATE_FAIL);
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OutgoingDocument> getall() {
        LOGGER.info(GET_ALL_ATTEMPT);
        return outgoingDocumentRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<OutgoingDocument> getById(String id) {
        LOGGER.info(GET_BY_ID_ATTEMPT);
        return outgoingDocumentRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<OutgoingDocument> update(OutgoingDocument outgoingDocument) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у OutgoingDocument с id {0}", outgoingDocument.getId().toString()));
        int updateCount = outgoingDocumentRepository.update(outgoingDocument);
        if (updateCount > 0) {
            LOGGER.info(UPDATE_SUCCESS);
            return Optional.ofNullable(outgoingDocument);
        }
        LOGGER.error(UPDATE_FAIL);
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info(DELETE_SUCCESS);
        outgoingDocumentRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(String id) {
        if (outgoingDocumentRepository.deleteById(id)) {
            LOGGER.info(DELETE_BY_ID_SUCCESS);
        } else {
            LOGGER.error(DELETE_BY_ID_FAIL);
        }
    }
}
