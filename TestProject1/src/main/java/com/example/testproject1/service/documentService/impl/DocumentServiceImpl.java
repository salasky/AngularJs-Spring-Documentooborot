package com.example.testproject1.service.documentService.impl;

import com.example.testproject1.exception.DocumentExistsException;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.service.dbService.baseDocument.BaseDocumentService;
import com.example.testproject1.service.dbService.incomingDocument.IncomingDocumentService;
import com.example.testproject1.service.dbService.outgoingDocument.OutgoingDocumentService;
import com.example.testproject1.service.dbService.taskDocument.TaskDocumentService;
import com.example.testproject1.service.documentService.DocumentService;
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
     * Объект класса для хранения документов
     */
    @Autowired
    private BaseDocumentService baseDocumentService;
    @Autowired
    private TaskDocumentService taskDocumentService;
    @Autowired
    private IncomingDocumentService incomingDocumentService;
    @Autowired
    private OutgoingDocumentService outgoingDocumentService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveTaskInDB(TaskDocument taskDocument) throws DocumentExistsException {
        if(baseDocumentService.existByRegNumber(taskDocument.getRegNumber())){
            throw new DocumentExistsException(taskDocument.getRegNumber(),
                    MessageFormat.format("Document number {0} exist", taskDocument.getRegNumber()));
        }
        else {
           taskDocumentService.create(taskDocument);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void saveIncomingInDB(IncomingDocument incomingDocument) throws DocumentExistsException {
        if(baseDocumentService.existByRegNumber(incomingDocument.getRegNumber())){
            throw new DocumentExistsException(incomingDocument.getRegNumber(),
                    MessageFormat.format("Document number {0} exist", incomingDocument.getRegNumber()));
        }
        else {
            incomingDocumentService.create(incomingDocument);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOutgoingInDB(OutgoingDocument outgoingDocument) throws DocumentExistsException {
        if(baseDocumentService.existByRegNumber(outgoingDocument.getRegNumber())){
            throw new DocumentExistsException(outgoingDocument.getRegNumber(),
                    MessageFormat.format("Document number {0} exist", outgoingDocument.getRegNumber()));
        }
        else {
            outgoingDocumentService.create(outgoingDocument);
        }
    }
}