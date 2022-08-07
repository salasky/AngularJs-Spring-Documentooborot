package com.example.testproject1.service.dbservice.basedocument;

import com.example.testproject1.dao.basedocument.BaseDocumentRepository;
import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.model.document.BaseDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class BaseDocumentServiceImpl implements BaseDocumentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDocumentServiceImpl.class);
    @Autowired
    private BaseDocumentRepository baseDocumentRepository;
    private final String CREATE_SUCCESS="BaseDocument успешно сохранен";
    private final String CREATE_FAIL="Не удалось сохранить BaseDocument";
    private final String GET_ALL_ATTEMPT="Попытка выдачи всех BaseDocument";
    private final String GET_BY_ID_ATTEMPT="Попытка получить BaseDocument по id";
    private final String UPDATE_SUCCESS="BaseDocument успешно обновлен";
    private final String UPDATE_FAIL="Неудачная попытка сохранения BaseDocument";
    private final String DELETE_ALL_SUCCESS="Записи из таблицы base_document успешно удалены";
    private final String DELETE_BY_ID_SUCCESS="Запись из таблицы base_document успешно удалена";
    private final String FINE_BY_REG_NUMBER="Поиск baseDocument по регистрационному номеру";
    @Override
    public Optional<BaseDocument> create(BaseDocument baseDocument) {
        Optional<BaseDocument> baseDocumentOptional = baseDocumentRepository.create(baseDocument);
        if (baseDocumentOptional.isPresent()) {
            LOGGER.info(CREATE_SUCCESS);
            return Optional.ofNullable(baseDocumentOptional.get());
        }
        LOGGER.error(CREATE_FAIL);
        return Optional.empty();
    }

    @Override
    public List<BaseDocument> getall() {
        LOGGER.info(GET_ALL_ATTEMPT);
        return baseDocumentRepository.getAll();
    }

    @Override
    public Optional<BaseDocument> getById(String id) {
        LOGGER.info(GET_BY_ID_ATTEMPT);
        return baseDocumentRepository.getById(id);
    }

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

    @Override
    public void deleteAll() {
        try {
            if (baseDocumentRepository.deleteAll()) {
                LOGGER.info(DELETE_ALL_SUCCESS);
            }
        } catch (DeletePoorlyException e) {
            LOGGER.error(e.toString());
        }
    }
    @Override
    public void deleteById(String id) {
        try {
            if (baseDocumentRepository.deleteById(id)) {
                LOGGER.info(DELETE_BY_ID_SUCCESS);
            }
        } catch (DeletePoorlyException e) {
            LOGGER.error(e.toString());
        }
    }

    @Override
    public boolean existByRegNumber(Long regNumber) {
        LOGGER.info(FINE_BY_REG_NUMBER);
        return baseDocumentRepository.existByRegNumber(regNumber);
    }
}
