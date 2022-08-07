package com.example.testproject1.service.dbservice.outgoingdocument;

import com.example.testproject1.dao.outgoingdocument.OutgoingDocumentRepository;
import com.example.testproject1.exception.DeletePoorlyException;
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

    private final String CREATE_SUCCESS="OutgoingDocument успешно сохранен";
    private final String CREATE_FAIL="Неудачная попытка сохранения OutgoingDocument";
    private final String GET_ALL_ATTEMPT="Попытка выдачи всех OutgoingDocument";
    private final String GET_BY_ID_ATTEMPT="Попытка получить OutgoingDocument по id";
    private final String UPDATE_SUCCESS="OutgoingDocument успешно обновлен";
    private final String UPDATE_FAIL="Неудачная попытка обновления OutgoingDocument";
    private final String DELETE_SUCCESS="Записи из таблицы OutgoingDocument успешно удалены";
    private final String DELETE_BY_ID_SUCCESS="Запись из таблицы OutgoingDocument успешно удалена";

    @Override
    public Optional<OutgoingDocument> create(OutgoingDocument outgoingDocument) {
        Optional<OutgoingDocument> optionalOutgoingDocument = outgoingDocumentRepository.create(outgoingDocument);
        if (optionalOutgoingDocument.isPresent()) {
            LOGGER.info(CREATE_SUCCESS);
            return Optional.ofNullable(outgoingDocument);
        }
        LOGGER.error(CREATE_FAIL);
        return Optional.empty();
    }

    @Override
    public List<OutgoingDocument> getall() {
        LOGGER.info(GET_ALL_ATTEMPT);
        return outgoingDocumentRepository.getAll();
    }

    @Override
    public Optional<OutgoingDocument> getById(String id) {
        LOGGER.info(GET_BY_ID_ATTEMPT);
        return outgoingDocumentRepository.getById(id);
    }

    @Override
    public Optional<OutgoingDocument> update(OutgoingDocument outgoingDocument) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у OutgoingDocument с id {0}", outgoingDocument.getId().toString()));
        int updateCount = outgoingDocumentRepository.update(outgoingDocument);
        if (updateCount == 1) {
            LOGGER.info(UPDATE_SUCCESS);
            return Optional.ofNullable(outgoingDocument);
        }
        LOGGER.error(UPDATE_FAIL);
        return Optional.empty();
    }

    @Override
    public void deleteAll() {
        try {
            if (outgoingDocumentRepository.deleteAll()) {
                LOGGER.info(DELETE_SUCCESS);
            }
        } catch (DeletePoorlyException e) {
            LOGGER.error(e.toString());
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            if (outgoingDocumentRepository.deleteById(id)) {
                LOGGER.info(DELETE_BY_ID_SUCCESS);
            }
        } catch (DeletePoorlyException e) {
            LOGGER.error(e.toString());
        }
    }
}
