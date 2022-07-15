package com.example.testproject1.model;


/**
 * Клас поручений. Наследник {@link BaseDocument}
 */

public class TaskDocument extends BaseDocument {
    /**
     * дата выдачи поручения
     */
    private String taskOutDate;
    /**
     * срок исполнения поручения
     */
    private String taskExecPeriod;
    /**
     * ответственный исполнитель
     */
    private String taskResponsible;
    /**
     * признак контрольности
     */
    private String taskSignOfControl;
    /**
     * контролер поручения
     */
    private String taskControlPerson;

    public TaskDocument(String documentName, String documentText, Long documentRegNumber, String documentData, String documentAuthor, String taskOutDate, String taskExecPeriod, String taskResponsible, String taskSignOfControl, String taskControlPerson) {
        super(BaseDocument.identifikator, documentName, documentText, documentRegNumber, documentData, documentAuthor);
        this.taskOutDate = taskOutDate;
        this.taskExecPeriod = taskExecPeriod;
        this.taskResponsible = taskResponsible;
        this.taskSignOfControl = taskSignOfControl;
        this.taskControlPerson = taskControlPerson;
        BaseDocument.identifikator++;
    }

    public String getTaskOutDate() {
        return taskOutDate;
    }

    public void setTaskOutDate(String taskOutDate) {
        this.taskOutDate = taskOutDate;
    }

    public String getTaskExecPeriod() {
        return taskExecPeriod;
    }

    public void setTaskExecPeriod(String taskExecPeriod) {
        this.taskExecPeriod = taskExecPeriod;
    }

    public String getTaskResponsible() {
        return taskResponsible;
    }

    public void setTaskResponsible(String taskResponsible) {
        this.taskResponsible = taskResponsible;
    }

    public String getTaskSignOfControl() {
        return taskSignOfControl;
    }

    public void setTaskSignOfControl(String taskSignOfControl) {
        this.taskSignOfControl = taskSignOfControl;
    }

    public String getTaskControlPerson() {
        return taskControlPerson;
    }

    public void setTaskControlPerson(String taskControlPerson) {
        this.taskControlPerson = taskControlPerson;
    }

    @Override
    public String toString() {
        return "TaskDocument{" +
                ", id=" + id +
                ", documentName='" + documentName + '\'' +
                ", documentText='" + documentText + '\'' +
                ", documentRegNumber=" + documentRegNumber +
                ", documentData='" + documentDate + '\'' +
                ", documentAuthor='" + documentAuthor + '\'' +
                "taskOutDate='" + taskOutDate + '\'' +
                ", taskExecPeriod='" + taskExecPeriod + '\'' +
                ", taskResponsible='" + taskResponsible + '\'' +
                ", taskSignOfControl='" + taskSignOfControl + '\'' +
                ", taskControlPerson='" + taskControlPerson + '\'' +
                '}';
    }
}
