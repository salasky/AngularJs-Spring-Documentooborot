package com.example.testproject1.service.documentservice.impl;

import com.example.testproject1.exception.DocumentExistsException;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.service.docfactory.Factory;
import com.example.testproject1.service.documentservice.DocumentService;
import com.example.testproject1.service.documentservice.GenerateDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.document.IncomingDocument;

import java.util.List;
import java.util.Random;

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
    private List<Factory<BaseDocument>> documentFactoryList;

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateDocument(Integer count) {
        LOGGER.info("\n         ---------------------Сгенерированные документы---------------------");
        for (int i = 0; i < count; i++) {
            BaseDocument baseDocument = documentFactoryList.get(new Random().nextInt(documentFactoryList.size())).create();
            if (baseDocument != null) {
                try {
                    documentService.add(baseDocument);
                    LOGGER.info(String.valueOf(baseDocument));
                } catch (DocumentExistsException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
    }
}

