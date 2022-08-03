package com.example.testproject1.service.documentservice.impl;

import com.example.testproject1.exception.DocumentExistsException;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.service.dbService.baseDocument.BaseDocumentService;
import com.example.testproject1.service.dbService.incomingDocument.IncomingDocumentService;
import com.example.testproject1.service.dbService.outgoingDocument.OutgoingDocumentService;
import com.example.testproject1.service.dbService.taskDocument.TaskDocumentService;
import com.example.testproject1.service.documentservice.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * Класс реализующий интерфейс {@link DocumentService}. Сохраняет переданные документы,
 * перед сохранением проверяет уникаль6ность рег.номера
 *
 * @author smigranov
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    /**
     * Бин сервиса для работы с базовыми документами
     */
    @Autowired
    private BaseDocumentService baseDocumentService;
    /**
     * Бин сервиса для работы с поручениями
     */
    @Autowired
    private TaskDocumentService taskDocumentService;
    /**
     * Бин сервиса для работы с входящими документами
     */
    @Autowired
    private IncomingDocumentService incomingDocumentService;
    /**
     * Бин сервиса для работы с исходящими документами
     */
    @Autowired
    private OutgoingDocumentService outgoingDocumentService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveTaskInDB(TaskDocument taskDocument) throws DocumentExistsException {
        isNotExitByRegNumberOrElseThrow(taskDocument.getRegNumber());
        taskDocumentService.create(taskDocument);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void saveIncomingInDB(IncomingDocument incomingDocument) throws DocumentExistsException {
        isNotExitByRegNumberOrElseThrow(incomingDocument.getRegNumber());
        incomingDocumentService.create(incomingDocument);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOutgoingInDB(OutgoingDocument outgoingDocument) throws DocumentExistsException {
        isNotExitByRegNumberOrElseThrow(outgoingDocument.getRegNumber());
        outgoingDocumentService.create(outgoingDocument);
    }

    /**
     * Метод проверки существования документа с принимаемым регистрационным номером
     * @param regNumber регистрационный номер документа
     * @throws DocumentExistsException выбрасывает исключение, если существет документ с переданным регистрационным номером
     */
    private void isNotExitByRegNumberOrElseThrow(Long regNumber) throws DocumentExistsException {
        if(baseDocumentService.existByRegNumber(regNumber)){
            throw new DocumentExistsException(regNumber,
                    MessageFormat.format("Document number {0} exist", regNumber));
        }
    }
}