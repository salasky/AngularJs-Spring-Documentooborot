package com.example.testproject1.service.Docfactory;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.TaskDocument;
import com.example.testproject1.service.DocBuilder.TaskBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskDocumentFactory extends DocFactory {

    @Autowired
    private TaskBuilder taskBuilder;

    @Override
    public BaseDocument createDocument() {
        /*taskBuilder.fixDocumentAuthor().fixDocumentData().fixDocumentName().build();*/
        return taskBuilder.fixDocumentName().build();

    }
}
