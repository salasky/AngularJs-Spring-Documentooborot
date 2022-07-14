package com.example.testproject1.service.docBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.TaskDocument;
import com.example.testproject1.service.staticList.StaticList;
import com.example.testproject1.shell.TaskDocumentShell;
import org.springframework.stereotype.Component;

import java.util.Date;

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



    @Override
    public TaskBuilder fixDocumentName() {
        this.documentName= StaticList.docNameList.get((int) ( Math.random() * StaticList.docNameList.size() ));
        return this;
    }

    @Override
    public TaskBuilder fixDocumentText() {
        this.documentText=StaticList.docTextList.get((int) ( Math.random() * StaticList.docTextList.size())) ;
        return this;
    }

    @Override
    public TaskBuilder fixDocumentRegNumber() throws DocumentExistsException {
        var regNumber= Long.valueOf((int) (Math.random()*100));

        for (BaseDocument t:TaskDocumentShell.documentList)
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
        this.documentAuthor=StaticList.docAuthorList.get((int) ( Math.random() * StaticList.docAuthorList.size())) ;
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
        this.taskResponsible=StaticList.docAuthorList.get((int) ( Math.random() * StaticList.docAuthorList.size() )) ;
        return this;
    }

    @Override
    public TaskBuilder fixTaskSignOfControl() {
        this.taskSignOfControl=StaticList.controlList.get((int) ( Math.random() *StaticList.controlList.size() )) ;
        return this;
    }

    @Override
    public TaskBuilder fixTaskControlPerson() {
        this.taskControlPerson=StaticList.controlPersonList.get((int) ( Math.random() *StaticList.controlPersonList.size() )) ;
        return this;
    }

    @Override
    public TaskDocument build() {
       var taskdoc= new TaskDocument(documentName,documentText,documentRegNumber,documentData,documentAuthor
               ,taskOutDate,taskExecPeriod,taskResponsible,taskSignOfControl,taskControlPerson);
       TaskDocumentShell.documentList.add(taskdoc);
       return taskdoc;
    }
}
