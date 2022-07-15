package com.example.testproject1.model;


import java.util.Objects;
import java.util.UUID;

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

    public TaskDocument(UUID id,String documentName, String documentText, Long documentRegNumber, String documentData, String documentAuthor, String taskOutDate, String taskExecPeriod, String taskResponsible, String taskSignOfControl, String taskControlPerson) {
        super(id, documentName, documentText, documentRegNumber, documentData, documentAuthor);
        this.taskOutDate = taskOutDate;
        this.taskExecPeriod = taskExecPeriod;
        this.taskResponsible = taskResponsible;
        this.taskSignOfControl = taskSignOfControl;
        this.taskControlPerson = taskControlPerson;
        ;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDocument)) return false;
        if (!super.equals(o)) return false;
        TaskDocument that = (TaskDocument) o;
        return Objects.equals(taskOutDate, that.taskOutDate) && Objects.equals(taskExecPeriod, that.taskExecPeriod) && Objects.equals(taskResponsible, that.taskResponsible) && Objects.equals(taskSignOfControl, that.taskSignOfControl) && Objects.equals(taskControlPerson, that.taskControlPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), taskOutDate, taskExecPeriod, taskResponsible, taskSignOfControl, taskControlPerson);
    }


    public class TaskBuilder{
        private TaskBuilder() {
            // private constructor
        }

    }
}
