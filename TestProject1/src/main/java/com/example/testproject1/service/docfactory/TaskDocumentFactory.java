package com.example.testproject1.service.docfactory;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.service.docBuilder.TaskBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Класс фабрики для {@link com.example.testproject1.model.TaskDocument}
 * @author smigranov
 * @version 1.0
 */
@Service
public class TaskDocumentFactory extends DocFactory {
    Logger logger = LoggerFactory.getLogger(TaskDocumentFactory.class);
    /**
     * Автоваирим объект {@link com.example.testproject1.service.docBuilder.TaskBuilderImpl}
     *
     */
    @Autowired
    private TaskBuilder taskBuilder;

    @Override
    public BaseDocument createDocument() {
        try {
            return taskBuilder.fixDocumentId().fixDocumentName().fixDocumentText().fixDocumentRegNumber()
                    .fixDocumentData().fixDocumentAuthor().fixTaskOutDate().fixTaskExecPeriod()
                    .fixTaskResponsible().fixTaskSignOfControl().fixTaskControlPerson().build();
        } catch (DocumentExistsException e) {
            /*throw new RuntimeException(e);//Ломать или продолжить*/
            logger.error(e.getMessage());
            return null;
        }
    }
}
