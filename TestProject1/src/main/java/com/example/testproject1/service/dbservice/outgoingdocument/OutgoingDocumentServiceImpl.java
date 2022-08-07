package com.example.testproject1.service.dbservice.outgoingdocument;

import com.example.testproject1.dao.outgoingdocument.OutgoingDocumentRepository;
import com.example.testproject1.model.document.OutgoingDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class OutgoingDocumentServiceImpl implements OutgoingDocumentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OutgoingDocumentServiceImpl.class);

    @Autowired
    private OutgoingDocumentRepository outgoingDocumentRepository;

    @Override
    public Optional<OutgoingDocument> create(OutgoingDocument outgoingDocument) {
        LOGGER.info("Попытка создания OutgoingDocument");
        Optional<OutgoingDocument> optionalOutgoingDocument = outgoingDocumentRepository.create(outgoingDocument);
        if (optionalOutgoingDocument.isPresent()) {
            LOGGER.info("OutgoingDocument успешно сохранен");
            return Optional.ofNullable(outgoingDocument);
        }
        LOGGER.error("Неудачная попытка сохранения OutgoingDocument");
        return Optional.empty();
    }

    @Override
    public List<OutgoingDocument> getall() {
        LOGGER.info("Попытка выдачи всех OutgoingDocument");
        return outgoingDocumentRepository.getAll();
    }

    @Override
    public Optional<OutgoingDocument> getById(String id) {
        LOGGER.info("Попытка получить OutgoingDocument по id");
        return outgoingDocumentRepository.getById(id);
    }

    @Override
    public Optional<OutgoingDocument> update(OutgoingDocument outgoingDocument) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у OutgoingDocument с id {0}", outgoingDocument.getId().toString()));
        int updateCount = outgoingDocumentRepository.update(outgoingDocument);
        if (updateCount == 1) {
            LOGGER.info("OutgoingDocument успешно обновлен");
            return Optional.ofNullable(outgoingDocument);
        }
        LOGGER.error("Неудачная попытка обновления OutgoingDocument");
        return null;
    }

    @Override
    public String deleteAll() {
        LOGGER.info("Попытка удалить все записи в таблице outgoing_document");
        int count = outgoingDocumentRepository.deleteAll();
        if (count > 0) {
            LOGGER.info("Записи из таблицы outgoing_document успешно удалены");
            return "Записи из таблицы outgoing_document успешно удалены";
        }
        LOGGER.error("Не удачная попытка удаления записей из таблицы outgoing_document");
        return "Не удачная попытка удаления записей из таблицы outgoing_document";
    }

    @Override
    public String deleteById(String id) {
        LOGGER.info("Попытка удалить запись из таблицы outgoing_document");
        int count = outgoingDocumentRepository.deleteById(id);
        if (count > 0) {
            LOGGER.info("Запись из таблицы outgoing_document успешно удалена");
            return "Запись из таблицы outgoing_document успешно удалена";
        }
        LOGGER.error("Не удачная попытка удаления записи из таблицы outgoing_document");
        return "Не удачная попытка удаления записи из таблицы outgoing_document";
    }
}
