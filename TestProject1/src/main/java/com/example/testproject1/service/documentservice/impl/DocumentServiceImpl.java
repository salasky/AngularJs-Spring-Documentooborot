package com.example.testproject1.service.documentservice.impl;

import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.dbservice.basedocument.BaseDocumentService;
import com.example.testproject1.service.documentservice.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Класс реализующий интерфейс {@link DocumentService}. Сохраняет переданные документы,
 * перед сохранением проверяет уникаль6ность рег.номера
 *
 * @author smigranov
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    /**
     * Бин сервиса для работы с поручениями
     */
    @Autowired
    private CrudService<TaskDocument> taskDocumentService;
    /**
     * Бин сервиса для работы с входящими документами
     */
    @Autowired
    private CrudService<IncomingDocument> incomingDocumentService;
    /**
     * Бин сервиса для работы с исходящими документами
     */
    @Autowired
    private CrudService<OutgoingDocument> outgoingDocumentService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveTaskInDB(TaskDocument taskDocument) {
        taskDocumentService.create(taskDocument);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveIncomingInDB(IncomingDocument incomingDocument) {
        incomingDocumentService.create(incomingDocument);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOutgoingInDB(OutgoingDocument outgoingDocument) {
        outgoingDocumentService.create(outgoingDocument);
    }
}