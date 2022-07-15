package com.example.testproject1.service.docBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.TaskDocument;
import com.example.testproject1.service.DocSave.DocSave;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Класс реализующий паттерн Builder. Для создания экземпляров {@link TaskDocument}
 * implements {@link TaskBuilder}
 * @author smigranov
 * @version 1.0
 */
@Component
public class TaskBuilderImpl implements TaskBuilder {

    /**
     * {@link TaskDocument#documentName}
     */
    private String documentName;
    /**
     * {@link TaskDocument#documentText}
     */
    private String documentText;
    /**
     * {@link TaskDocument#documentRegNumber}
     */
    private Long documentRegNumber;
    /**
     * {@link TaskDocument#documentDate}
     */
    private String documentDate;
    /**
     * {@link TaskDocument#documentAuthor}
     */
    private String documentAuthor;
    /**
     * {@link TaskDocument#taskOutDate}
     */
    private String taskOutDate;
    /**
     * {@link TaskDocument#taskExecPeriod}
     */
    private String taskExecPeriod;
    /**
     * {@link TaskDocument#taskResponsible}
     */
    private String taskResponsible;
    /**
     * {@link TaskDocument#taskSignOfControl}
     */
    private String taskSignOfControl;
    /**
     * {@link TaskDocument#taskControlPerson}
     */
    private String taskControlPerson;
    /**
     * Лист названий поручений из application.yaml
     */
    @Value("${doc.documentName}")
    private List<String> newDocNameList;
    /**
     * Лист текстов поручений из application.yaml
     */
    @Value("${doc.documentText}")
    private List<String> newDocTextList;
    /**
     * Лист авторов поручений из application.yaml
     */
    @Value("${doc.documentAuthor}")
    private List<String> newDocAuthorList;
    /**
     * Лист признаков контроля поручений из application.yaml
     */
    @Value("${doc.documentControl}")
    private List<String> newDocControlList;
    /**
     * Лист контролеров поручений из application.yaml
     */
    @Value("${doc.documentControlPerson}")
    private List<String> newDocControlPersonList;

    @Override
    public TaskBuilder fixDocumentName() {
        this.documentName = newDocNameList.get((int) (Math.random() * newDocNameList.size()));
        return this;
    }

    @Override
    public TaskBuilder fixDocumentText() {
        this.documentText = newDocTextList.get((int) (Math.random() * newDocTextList.size()));
        return this;
    }

    @Override
    public TaskBuilder fixDocumentRegNumber() throws DocumentExistsException {
        var regNumber = Long.valueOf((int) (Math.random() * 100));

        for (BaseDocument t : DocSave.documentList) {
            if (t.getDocumentRegNumber() == regNumber) {
                throw new DocumentExistsException(regNumber, "Document number " + regNumber + " exist");
            }
        }
        this.documentRegNumber = regNumber;
        return this;
    }

    @Override
    public TaskBuilder fixDocumentData() {
        this.documentDate = "2022-" + (int) (Math.random() * 12 + 1) + "-" + (int) (Math.random() * 29 + 1);
        return this;
    }

    @Override
    public TaskBuilder fixDocumentAuthor() {
        this.documentAuthor = newDocAuthorList.get((int) (Math.random() * newDocAuthorList.size()));
        return this;
    }

    @Override
    public TaskBuilder fixTaskOutDate() {
        Date date = new Date();
        this.taskOutDate = date.toString();
        return this;
    }

    @Override
    public TaskBuilder fixTaskExecPeriod() {
        this.taskExecPeriod = ((int) (Math.random() * 14 + 1) + " дня");
        return this;
    }

    @Override
    public TaskBuilder fixTaskResponsible() {
        this.taskResponsible = newDocAuthorList.get((int) (Math.random() * newDocAuthorList.size()));
        return this;
    }

    @Override
    public TaskBuilder fixTaskSignOfControl() {
        this.taskSignOfControl = newDocControlList.get((int) (Math.random() * newDocControlList.size()));
        return this;
    }

    @Override
    public TaskBuilder fixTaskControlPerson() {
        this.taskControlPerson = newDocControlPersonList.get((int) (Math.random() * newDocControlPersonList.size()));
        return this;
    }

    @Override
    public TaskDocument build() {
        var taskdoc = new TaskDocument(documentName, documentText, documentRegNumber, documentDate, documentAuthor
                , taskOutDate, taskExecPeriod, taskResponsible, taskSignOfControl, taskControlPerson);
        return taskdoc;
    }
}
