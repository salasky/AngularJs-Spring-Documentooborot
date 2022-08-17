package com.example.testproject1.service.documentservice.impl;

import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.documentservice.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Класс реализующий интерфейс {@link DocumentService}. Сохраняет переданные документы,
 * перед сохранением проверяет уникаль6ность рег.номера
 *
 * @author smigranov
 */
@Service
public class DocumentServiceImpl implements DocumentService {


    @Autowired
    private CrudService<TaskDocument> taskDocumentService;

    @Autowired
    private CrudService<IncomingDocument> incomingDocumentService;

    @Autowired
    private CrudService<OutgoingDocument> outgoingDocumentService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveTaskInDB(TaskDocument taskDocument) throws DocflowRuntimeApplicationException {
        taskDocumentService.create(taskDocument);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveIncomingInDB(IncomingDocument incomingDocument) throws DocflowRuntimeApplicationException {
        incomingDocumentService.create(incomingDocument);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOutgoingInDB(OutgoingDocument outgoingDocument) throws DocflowRuntimeApplicationException {
        outgoingDocumentService.create(outgoingDocument);
    }
}