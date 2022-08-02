package com.example.testproject1.service.documentService;

import com.example.testproject1.exception.DocumentExistsException;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.document.TaskDocument;

/**
 * Интерфейс добавления документов
 */
public interface DocumentService {

    void saveTaskInDB(TaskDocument taskDocument) throws DocumentExistsException;

    void saveIncomingInDB(IncomingDocument incomingDocument) throws DocumentExistsException;

    void saveOutgoingInDB(OutgoingDocument outgoingDocument) throws DocumentExistsException;
}
