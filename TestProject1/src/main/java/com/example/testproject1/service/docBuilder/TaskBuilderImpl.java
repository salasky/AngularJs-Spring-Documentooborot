package com.example.testproject1.service.docBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.TaskDocument;
import com.example.testproject1.service.DocSave.DocSave;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
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

    @Value("${doc.documentName}")
    private List<String> newDocNameList;

    @Value("${doc.documentText}")
    private List<String> newDocTextList;

    @Value("${doc.documentAuthor}")
    private List<String> newDocAuthorList;

    @Value("${doc.documentControl}")
    private List<String> newDocControlList;

    @Value("${doc.documentControlPerson}")
    private List<String> newDocControlPersonList;

    @Override
    public TaskBuilder fixDocumentName() {
        this.documentName= newDocNameList.get((int) ( Math.random() * newDocNameList.size() ));
        return this;
    }

    @Override
    public TaskBuilder fixDocumentText() {
        this.documentText=newDocTextList.get((int) ( Math.random() * newDocTextList.size())) ;
        return this;
    }

    @Override
    public TaskBuilder fixDocumentRegNumber() throws DocumentExistsException {
        var regNumber= Long.valueOf((int) (Math.random()*100));

        for (BaseDocument t: DocSave.documentList)
        {
            if (t.getDocumentRegNumber() == regNumber) {
                throw new DocumentExistsException(regNumber,"Document number "+regNumber+" exist");
            }
        }
        this.documentRegNumber=regNumber;
        return this;
    }

    @Override
    public TaskBuilder fixDocumentData() {
        this.documentData="2022-"+(int)(Math.random()*12+1)+"-"+(int)(Math.random()*29+1);
        return this;
    }

    @Override
    public TaskBuilder fixDocumentAuthor() {
        this.documentAuthor=newDocAuthorList.get((int) ( Math.random() * newDocAuthorList.size())) ;
        return this;
    }

    @Override
    public TaskBuilder fixTaskOutDate() {
        Date date = new Date();
        this.taskOutDate=date.toString();
        return this;
    }

    @Override
    public TaskBuilder fixTaskExecPeriod() {
        this.taskExecPeriod=((int)(Math.random()*14+1)+" дня");
        return this;
    }

    @Override
    public TaskBuilder fixTaskResponsible() {
        this.taskResponsible=newDocAuthorList.get((int) ( Math.random() * newDocAuthorList.size() )) ;
        return this;
    }

    @Override
    public TaskBuilder fixTaskSignOfControl() {
        this.taskSignOfControl=newDocControlList.get((int) ( Math.random() *newDocControlList.size() )) ;
        return this;
    }

    @Override
    public TaskBuilder fixTaskControlPerson() {
        this.taskControlPerson=newDocControlPersonList.get((int) ( Math.random() *newDocControlPersonList.size() )) ;
        return this;
    }

    @Override
    public TaskDocument build() {
       var taskdoc= new TaskDocument(documentName,documentText,documentRegNumber,documentData,documentAuthor
               ,taskOutDate,taskExecPeriod,taskResponsible,taskSignOfControl,taskControlPerson);
       return taskdoc;
    }
}
