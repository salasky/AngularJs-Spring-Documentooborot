package com.example.testproject1.service.dbService.incomingDocument;

import com.example.testproject1.dao.incomingDocumrnt.IncomingDocumentRepository;
import com.example.testproject1.model.document.IncomingDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class IncomingDocumentServiceImpl implements IncomingDocumentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IncomingDocumentServiceImpl.class);

    @Autowired
    private IncomingDocumentRepository incomingDocumentRepository;

    @Override
    public Optional<IncomingDocument> create(IncomingDocument incomingDocument) {
        LOGGER.info("Попытка создания IncomingDocument");
        int updateCount = incomingDocumentRepository.create(incomingDocument);
        if (updateCount == 1) {
            LOGGER.info("IncomingDocument успешно сохранен");
            return Optional.ofNullable(incomingDocument);
        }
        LOGGER.error("Неудачная попытка сохранения IncomingDocument");
        return null;
    }

    @Override
    public List<IncomingDocument> getall() {
        LOGGER.info("Попытка выдачи всех IncomingDocument");
        return incomingDocumentRepository.getAll();
    }

    @Override
    public Optional<IncomingDocument> getById(String id) {
        LOGGER.info("Попытка получить IncomingDocument по id");
        return incomingDocumentRepository.getById(id);
    }

    @Override
    public Optional<IncomingDocument> update(IncomingDocument incomingDocument) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у IncomingDocument с id {0}", incomingDocument.getId().toString()));
        int updateCount = incomingDocumentRepository.update(incomingDocument);
        if (updateCount == 1) {
            LOGGER.info("IncomingDocument успешно обновлен");
            return Optional.ofNullable(incomingDocument);
        }
        LOGGER.error("Неудачная попытка обновления IncomingDocument");
        return null;
    }

    @Override
    public String deleteAll() {
        LOGGER.info("Попытка удалить все записи в таблице incoming_document");
        int count = incomingDocumentRepository.deleteAll();
        if (count > 0) {
            LOGGER.info("Записи из таблицы incoming_document успешно удалены");
            return "Записи из таблицы incoming_document успешно удалены";
        }
        LOGGER.error("Не удачная попытка удаления записей из таблицы incoming_document");
        return "Не удачная попытка удаления записей из таблицы incoming_document";
    }

    @Override
    public String deleteById(String id) {
        LOGGER.info("Попытка удалить запись из таблицы incoming_document");
        int count = incomingDocumentRepository.deleteById(id);
        if (count > 0) {
            LOGGER.info("Запись из таблицы incoming_document успешно удалена");
            return "Запись из таблицы incoming_document успешно удалена";
        }
        LOGGER.error("Не удачная попытка удаления записи из таблицы incoming_document");
        return "Не удачная попытка удаления записи из таблицы incoming_document";
    }
}
