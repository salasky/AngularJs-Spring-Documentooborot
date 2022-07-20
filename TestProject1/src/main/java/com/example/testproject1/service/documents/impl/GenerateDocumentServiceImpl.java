package com.example.testproject1.service.documents.impl;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.service.docfactory.Factory;
import com.example.testproject1.service.documents.DocumentService;
import com.example.testproject1.service.documents.GenerateDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Класс реализующий интерфейс {@link GenerateDocumentService}
 *
 * @author smigranov
 */
@Service
public class GenerateDocumentServiceImpl implements GenerateDocumentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateDocumentServiceImpl.class);

    /**
     * Объект класса {@link DocumentService}
     */
    private DocumentService documentService;
    /**
     * Инжектим все бины классов реализующих интерфейс Factory
     */
    private List<Factory<BaseDocument>> documentFactoryList;

    @Autowired
    public GenerateDocumentServiceImpl(DocumentService documentService, List<Factory<BaseDocument>> documentFactoryList) {
        this.documentService = documentService;
        this.documentFactoryList = documentFactoryList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateDocument(Integer count) {
        LOGGER.info("\n         ---------------------Сгенерированные документы---------------------");
        for (int i = 0; i < count; i++) {
            BaseDocument taskDoc = documentFactoryList.get(new Random().nextInt(documentFactoryList.size())).create();
            if (taskDoc != null) {
                try {
                    documentService.documentAdd(taskDoc);
                    LOGGER.info(String.valueOf(taskDoc));
                } catch (DocumentExistsException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
    }

}

