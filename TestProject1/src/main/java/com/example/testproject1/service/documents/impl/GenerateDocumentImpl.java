package com.example.testproject1.service.documents.impl;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.service.docfactory.Factory;
import com.example.testproject1.service.documents.AddDocumentService;
import com.example.testproject1.service.documents.GenerateDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Класс реализующий интерфейс {@link GenerateDocument}
 *
 * @author smigranov
 */
@Service
public class GenerateDocumentImpl implements GenerateDocument {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateDocumentImpl.class);

    /**
     * Объект класса {@link AddDocumentService}
     */
    private AddDocumentService addDocumentService;
    /**
     * Инжектим все бины классов реализующих интерфейс Factory
     */
    private List<Factory<BaseDocument>> documentFactoryList;

    @Autowired
    public GenerateDocumentImpl(AddDocumentService addDocumentService, List<Factory<BaseDocument>> documentFactoryList) {
        this.addDocumentService = addDocumentService;
        this.documentFactoryList = documentFactoryList;
    }


    /**
     * Вспомогательный метод генерации документов
     *
     * @param task количество документов
     */

    @Override
    public void generateDocument(Integer task) {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("---------------------Сгенерированные документы---------------------");
        for (int i = 0; i < task; i++) {
            BaseDocument taskDoc = documentFactoryList.get(new Random().nextInt(documentFactoryList.size())).create();
            if (taskDoc != null) {
                try {
                    addDocumentService.documentAdd(taskDoc);
                    LOGGER.info(String.valueOf(taskDoc));
                } catch (DocumentExistsException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
    }
}

