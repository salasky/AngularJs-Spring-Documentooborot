package com.example.testproject1.service.DocBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.TaskDocument;
import com.example.testproject1.shell.TaskDocumentShell;
import org.springframework.stereotype.Component;

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


    public static List<String> docNameList=new ArrayList<>(List.of("HelloDocName", "WorldDocName","LogicDocName","CitrosDocName","IamDocName"));

    public static List<String> docNameIncomingList=new ArrayList<>(List.of("Главный документ", "Отпуск","Больничный","Выходной","Увольнение"));

    public static List<String> docNameOutgoingList=new ArrayList<>(List.of("Архив", "Отдел кадров","Отдел программирования","Отдел производства","Маркетинг"));
    public static List<String> docTextList=new ArrayList<>(List.of("IamDocText-Hello worl!", "IamDocText-Java","IamDocText-Text","IamDocText-Kotlin","IamDocText-Spring"));

    public static List<String> docAuthorList=new ArrayList<>(List.of("Бабошин Игорь Сергеевич", "Дацюк Павел Иванович","Кучеров Никита Сергеевич","Кросби Сидни Иванович","Джобс Стив Олегович"));

    public static List<String> controlList=new ArrayList<>(List.of("Подтверждение автора","Проверка тестировщика","Не назначено"));

    public static List<String> controlPersonList=new ArrayList<>(List.of("Иванов Игорь Сергеевич", "Кавальчук Алексей Иванович","Гвардиола Пеп Сергеевич"));

    public static List<String> distinPersonList =new ArrayList<>(List.of("Иванов Иван Иванович", "Админ Админ Админовчи","Гвардиола Пеп Сергеевич","Rik Nikols Pool","Gleen Satoshi Nikamota"));

    public static List<String> deliveryTypeList =new ArrayList<>(List.of("Голубями", "Email","RocketChat","Гонцом","Почтой России"));
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
       TaskDocumentShell.documentList.add(taskdoc);
       return taskdoc;
    }
}
