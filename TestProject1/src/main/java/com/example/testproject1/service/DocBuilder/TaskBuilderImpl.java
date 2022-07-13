package com.example.testproject1.service.DocBuilder;

import com.example.testproject1.model.TaskDocument;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskBuilderImpl implements TaskBuilder{

    private String documentName;
    private String documentText;
    private Long documentRegNumber;
    private String documentData;
    private String documentAuthor;
    private String taskOutDate;
    private String taskExecPeriod;
    private String taskResponsible;
    private String taskSignOfControl;
    private String taskControlPerson;

    private static List<String> docNameList=new ArrayList<>(List.of("HelloDocName", "WorldDocName","LogicDocName","CitrosDocName","IamDocName"));

    @Override
    public TaskBuilder fixDocumentName() {
        this.documentName=docNameList.get((int) ( Math.random() * 5 ));
        return this;
    }

    @Override
    public TaskBuilder fixDocumentText() {
        return null;
    }

    @Override
    public TaskBuilder fixDocumentRegNumber() {
        return null;
    }

    @Override
    public TaskBuilder fixDocumentData() {
        return null;
    }

    @Override
    public TaskBuilder fixDocumentAuthor() {
        return null;
    }

    @Override
    public TaskBuilder fixTaskOutDate() {
        return null;
    }

    @Override
    public TaskBuilder fixTaskExecPeriod() {
        return null;
    }

    @Override
    public TaskBuilder fixTaskResponsible() {
        return null;
    }

    @Override
    public TaskBuilder fixTaskSignOfControl() {
        return null;
    }

    @Override
    public TaskBuilder fixTaskControlPerson() {
        return null;
    }

    @Override
    public TaskDocument build() {
       return new TaskDocument(documentName,null,null,null,null,null,null,null,null,null);

    }
}
