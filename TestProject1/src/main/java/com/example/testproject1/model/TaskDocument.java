package com.example.testproject1.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*@Entity
@Table(name = "TaskDocument")*/
@Getter
@Setter
public class TaskDocument extends BaseDocument{

    private String taskOutDate;
    private String taskExecPeriod;
    private String taskResponsible;
    private String taskSignOfControl;
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

    @Override
    public String toString() {
        return "TaskDocument{" +
                ", id=" + id +
                ", documentName='" + documentName + '\'' +
                ", documentText='" + documentText + '\'' +
                ", documentRegNumber=" + documentRegNumber +
                ", documentData='" + documentData + '\'' +
                ", documentAuthor='" + documentAuthor + '\'' +
                "taskOutDate='" + taskOutDate + '\'' +
                ", taskExecPeriod='" + taskExecPeriod + '\'' +
                ", taskResponsible='" + taskResponsible + '\'' +
                ", taskSignOfControl='" + taskSignOfControl + '\'' +
                ", taskControlPerson='" + taskControlPerson + '\'' +
                '}';
    }
}
