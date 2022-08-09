package com.example.testproject1.service.dbservice.basedocument;

import com.example.testproject1.dao.basedocument.BaseDocumentRepository;
import com.example.testproject1.model.document.BaseDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Класс реализующий интерфейс {@link BaseDocumentService}. Для выполнения CRUD операций к базе данных.
 *
 * @author smigranov
 */
@Service
public class BaseDocumentServiceImpl implements BaseDocumentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDocumentServiceImpl.class);
    /**
     * Бин {@link BaseDocumentRepository}
     */
    @Autowired
    private BaseDocumentRepository baseDocumentRepository;
    /**
     * Лог при успешном сохранении
     */
    private final String CREATE_SUCCESS = "BaseDocument успешно сохранен";
    /**
     * Лог при неудачном сохранении
     */
    private final String CREATE_FAIL = "Не удалось сохранить BaseDocument";
    /**
     * Лог при выдаче всех BaseDocument
     */
    private final String GET_ALL_ATTEMPT = "Попытка выдачи всех BaseDocument";
    /**
     * Лог при выдаче BaseDocument по id
     */
    private final String GET_BY_ID_ATTEMPT = "Попытка получить BaseDocument по id";
    /**
     * Лог при успешном обновлении
     */
    private final String UPDATE_SUCCESS = "BaseDocument успешно обновлен";
    /**
     * Лог при неудачном обновлении
     */
    private final String UPDATE_FAIL = "Неудачная попытка сохранения BaseDocument";
    /**
     * Лог при попытке удаления всех записей
     */
    private final String DELETE_ALL_SUCCESS = "Попытка удаления записей из таблицы base_document ";
    /**
     * Лог при успешном удалении записи по id
     */
    private final String DELETE_BY_ID_SUCCESS = "Запись из таблицы base_document успешно удалена";
    /**
     * Лог при неудачном удалении записи по id
     */
    private final String DELETE_BY_ID_FAIL = "Запись из таблицы base_document не удалена";
    /**
     * Лог при поиске baseDocument по регистрационному номеру
     */
    private final String FINE_BY_REG_NUMBER = "Поиск baseDocument по регистрационному номеру";

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseDocument create(BaseDocument baseDocument) {
        BaseDocument baseDocumentDB= baseDocumentRepository.create(baseDocument);
        if(baseDocumentDB!=null){
            LOGGER.info(CREATE_SUCCESS);
            return baseDocumentDB;
        }
        LOGGER.error(CREATE_FAIL);
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BaseDocument> getAll() {
        LOGGER.info(GET_ALL_ATTEMPT);
        return baseDocumentRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<BaseDocument> getById(String id) {
        LOGGER.info(GET_BY_ID_ATTEMPT);
        return baseDocumentRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<BaseDocument> update(BaseDocument baseDocument) {
        int updateCount = baseDocumentRepository.update(baseDocument);
        if (updateCount == 1) {
            LOGGER.info(UPDATE_SUCCESS);
            return Optional.ofNullable(baseDocument);
        }
        LOGGER.error(UPDATE_FAIL);
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info(DELETE_ALL_SUCCESS);
        baseDocumentRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(String id) {
        if (baseDocumentRepository.deleteById(id)) {
            LOGGER.info(DELETE_BY_ID_SUCCESS);
        }else {
            LOGGER.error(DELETE_BY_ID_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existByRegNumber(Long regNumber) {
        LOGGER.info(FINE_BY_REG_NUMBER);
        return baseDocumentRepository.existByRegNumber(regNumber);
    }
}
