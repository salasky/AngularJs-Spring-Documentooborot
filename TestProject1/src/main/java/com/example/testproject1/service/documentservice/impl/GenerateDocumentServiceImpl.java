package com.example.testproject1.service.documentservice.impl;

import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.service.docfactory.IncomingDocumentFactory;
import com.example.testproject1.service.docfactory.OutgoingDocumentFactory;
import com.example.testproject1.service.docfactory.TaskDocumentFactory;
import com.example.testproject1.service.documentservice.DocumentService;
import com.example.testproject1.service.documentservice.GenerateDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Класс реализующий интерфейс {@link GenerateDocumentService}
 * Рандомно создает документ трех классов: {@link TaskDocument},{@link OutgoingDocument},{@link IncomingDocument}
 *
 * @author smigranov
 */
@Service
public class GenerateDocumentServiceImpl implements GenerateDocumentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateDocumentServiceImpl.class);

    /**
     * Объект класса {@link DocumentService}
     */
    @Autowired
    private DocumentService documentService;
    /**
     * Инжектим все бины классов реализующих интерфейс Factory
     */
    @Autowired
    private TaskDocumentFactory taskDocumentFactory;
    @Autowired
    private IncomingDocumentFactory incomingDocumentFactory;
    @Autowired
    private OutgoingDocumentFactory outgoingDocumentFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateDocument(Integer count) {
        LOGGER.info("\n         ---------------------Сгенерированные документы---------------------");
        for (int i = 0; i < count; i++) {
            BaseDocument taskDocument = taskDocumentFactory.create();
            BaseDocument incomingDocument = incomingDocumentFactory.create();
            BaseDocument outgoingDocument = outgoingDocumentFactory.create();
            documentService.saveTaskInDB((TaskDocument) taskDocument);
            documentService.saveIncomingInDB((IncomingDocument) incomingDocument);
            documentService.saveOutgoingInDB((OutgoingDocument) outgoingDocument);
        }
    }
}
