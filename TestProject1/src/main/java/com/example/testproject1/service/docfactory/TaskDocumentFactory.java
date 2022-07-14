package com.example.testproject1.service.docfactory;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.service.docBuilder.TaskBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * класс фабрики для {@link com.example.testproject1.model.TaskDocument}
 */
@Service
public class TaskDocumentFactory extends DocFactory {
    /**
     * Автоваирим объект {@link com.example.testproject1.service.docBuilder.TaskBuilderImpl}
     */
    @Autowired
    private TaskBuilder taskBuilder;

    @Override
    public BaseDocument createDocument()  {
        try {
             return taskBuilder.fixDocumentName().fixDocumentText().fixDocumentRegNumber()
                    .fixDocumentData().fixDocumentAuthor().fixTaskOutDate().fixTaskExecPeriod()
                    .fixTaskResponsible().fixTaskSignOfControl().fixTaskControlPerson().build();
        } catch (DocumentExistsException e) {
           /*throw new RuntimeException(e);//Ломать или продолжить*/
            System.out.println(e.getMessage());
            return null;
        }
    }
}
