package com.example.testproject1.service.DocBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.TaskDocument;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.ArrayList;
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

    private List<TaskDocument> taskDocumentsList=new ArrayList<>();
    private static List<String> docNameList=new ArrayList<>(List.of("HelloDocName", "WorldDocName","LogicDocName","CitrosDocName","IamDocName"));
    private static List<String> docTextList=new ArrayList<>(List.of("IamDocText-Hello worl!", "IamDocText-Java","IamDocText-Text","IamDocText-Kotlin","IamDocText-Spring"));

    private static List<String> docAuthorList=new ArrayList<>(List.of("Бабошин Игорь Сергеевич", "Дацюк Павел Иванович","Кучеров Никита Сергеевич","Кросби Сидни Иванович","Джобс Стив Олегович"));

    private static List<String> controlList=new ArrayList<>(List.of("Подтверждение автора","Проверка тестировщика","Не назначено"));

    private static List<String> controlPersonList=new ArrayList<>(List.of("Иванов Игорь Сергеевич", "Кавальчук Алексей Иванович","Гвардиола Пеп Сергеевич"));

    @Override
    public TaskBuilder fixDocumentName() {
        this.documentName=docNameList.get((int) ( Math.random() * 5 ));
        return this;
    }

    @Override
    public TaskBuilder fixDocumentText() {
        this.documentText=docTextList.get((int) ( Math.random() * 5 )) ;
        return this;
    }

    @Override
    public TaskBuilder fixDocumentRegNumber() throws DocumentExistsException {
        var regNumber= Long.valueOf((int) (Math.random()*100));

        for (TaskDocument t:taskDocumentsList)
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
        this.documentAuthor=docAuthorList.get((int) ( Math.random() * 5 )) ;
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
        this.taskResponsible=docAuthorList.get((int) ( Math.random() * 5 )) ;
        return this;
    }

    @Override
    public TaskBuilder fixTaskSignOfControl() {
        this.taskSignOfControl=controlList.get((int) ( Math.random() *3 )) ;
        return this;
    }

    @Override
    public TaskBuilder fixTaskControlPerson() {
        this.taskControlPerson=controlPersonList.get((int) ( Math.random() *3 )) ;
        return this;
    }

    @Override
    public TaskDocument build() {
       var taskdoc= new TaskDocument(documentName,documentText,documentRegNumber,documentData,documentAuthor
               ,taskOutDate,taskExecPeriod,taskResponsible,taskSignOfControl,taskControlPerson);
       taskDocumentsList.add(taskdoc);
       return taskdoc;
    }
}
