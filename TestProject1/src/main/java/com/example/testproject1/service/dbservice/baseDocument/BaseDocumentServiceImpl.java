package com.example.testproject1.service.dbservice.baseDocument;

import com.example.testproject1.dao.basedocument.BaseDocumentRepository;
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

    @Override
    public Optional<BaseDocument> create(BaseDocument baseDocument) {
        LOGGER.info("Попытка создания BaseDocument");
        int updateCount = baseDocumentRepository.create(baseDocument);
        if (updateCount == 1) {
            LOGGER.info("BaseDocument успешно сохранен");
            return Optional.ofNullable(baseDocument);
        }
        LOGGER.error("Неудачная попытка сохранения BaseDocument");
        return null;
    }

    @Override
    public List<BaseDocument> getall() {
        LOGGER.info("Попытка выдачи всех BaseDocument");
        return baseDocumentRepository.getAll();
    }

    @Override
    public Optional<BaseDocument> getById(String id) {
        LOGGER.info("Попытка получить BaseDocument по id");
        return baseDocumentRepository.getById(id);
    }

    @Override
    public Optional<BaseDocument> update(BaseDocument baseDocument) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у BaseDocument с id {0}", baseDocument.getId().toString()));
        int updateCount = baseDocumentRepository.update(baseDocument);
        if (updateCount == 1) {
            LOGGER.info("BaseDocument успешно обновлен");
            return Optional.ofNullable(baseDocument);
        }
        LOGGER.error("Неудачная попытка сохранения BaseDocument");
        return null;
    }

    @Override
    public String deleteAll() {
        LOGGER.info("Попытка удалить все записи в таблице base_document");
        int count = baseDocumentRepository.deleteAll();
        if (count > 0) {
            LOGGER.info("Записи из таблицы base_document успешно удалены");
            return "Записи из таблицы base_document успешно удалены";
        }
        LOGGER.error("Не удачная попытка удаления записей из таблицы base_document");
        return "Не удачная попытка удаления записей из таблицы base_document";
    }

    @Override
    public String deleteById(String id) {
        LOGGER.info("Попытка удалить запись из таблицы base_document");
        int count = baseDocumentRepository.deleteById(id);
        if (count > 0) {
            LOGGER.info("Запись из таблицы base_document успешно удалена");
            return "Запись из таблицы base_document успешно удалена";
        }
        LOGGER.error("Не удачная попытка удаления записи из таблицы base_document");
        return "Не удачная попытка удаления записи из таблицы base_document";
    }

    @Override
    public boolean existByRegNumber(Long regNumber) {
        LOGGER.info("Поиск baseDocument по регистрационному номеру");
        return baseDocumentRepository.existByRegNumber(regNumber);
    }
}
