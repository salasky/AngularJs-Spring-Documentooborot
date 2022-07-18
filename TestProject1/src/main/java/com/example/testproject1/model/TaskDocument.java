package com.example.testproject1.model;


import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Клас поручений. Наследник {@link BaseDocument}
 *
 * @author smigranov
 */
public class TaskDocument extends BaseDocument {
    /**
     * дата выдачи поручения
     */
    private Date taskOutDate;
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
    private Boolean taskSignOfControl;
    /**
     * контролер поручения
     */
    private String taskControlPerson;

    private TaskDocument() {
    }

    public Date getTaskOutDate() {
        return taskOutDate;
    }

    public void setTaskOutDate(Date taskOutDate) {
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

    public Boolean getTaskSignOfControl() {
        return taskSignOfControl;
    }

    public void setTaskSignOfControl(Boolean taskSignOfControl) {
        this.taskSignOfControl = taskSignOfControl;
    }

    public String getTaskControlPerson() {
        return taskControlPerson;
    }

    public void setTaskControlPerson(String taskControlPerson) {
        this.taskControlPerson = taskControlPerson;
    }

    /**
     * {@inheritDoc}
     * @return
     */
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

    /**
     * {@inheritDoc}
     * @param o Объект для сравнивания
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDocument)) return false;
        if (!super.equals(o)) return false;
        TaskDocument that = (TaskDocument) o;
        return Objects.equals(taskOutDate, that.taskOutDate) && Objects.equals(taskExecPeriod, that.taskExecPeriod) && Objects.equals(taskResponsible, that.taskResponsible) && Objects.equals(taskSignOfControl, that.taskSignOfControl) && Objects.equals(taskControlPerson, that.taskControlPerson);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), taskOutDate, taskExecPeriod, taskResponsible, taskSignOfControl, taskControlPerson);
    }
    public static TaskBuilder newBuilder() {
        return new TaskDocument().new TaskBuilder();
    }

    public class TaskBuilder{
        private TaskBuilder() {
            // private constructor
        }

        public TaskBuilder setDocId(UUID Id) {
            TaskDocument.this.id = Id;
            return this;
        }

        public TaskBuilder setDocName(String docName) {
            TaskDocument.this.documentName = docName;
            return this;
        }

        public TaskBuilder setDocText(String docText) {
            TaskDocument.this.documentText = docText;
            return this;
        }

        public TaskBuilder setDocRegNumber(Long docRegNumber) {
            TaskDocument.this.documentRegNumber = docRegNumber;
            return this;
        }

        public TaskBuilder setDocDate(Date docDate) {
            TaskDocument.this.documentDate = docDate;
            return this;
        }
        public TaskBuilder setDocAuthor(String docAuthor) {
            TaskDocument.this.documentAuthor = docAuthor;
            return this;
        }

        public TaskBuilder setTaskDate(Date taskDate) {
            TaskDocument.this.taskOutDate = taskDate;
            return this;
        }

        public TaskBuilder setTaskExecPeriod(String taskExecPeriod) {
            TaskDocument.this.taskExecPeriod = taskExecPeriod;
            return this;
        }

        public TaskBuilder setTaskResponsPerson(String taskResponsPerson) {
            TaskDocument.this.taskResponsible = taskResponsPerson;
            return this;
        }

        public TaskBuilder setTaskSignOfControl(Boolean taskSignOfControl) {
            TaskDocument.this.taskSignOfControl = taskSignOfControl;
            return this;
        }

        public TaskBuilder setTaskControlPerson(String taskControlPerson) {
            TaskDocument.this.taskControlPerson = taskControlPerson;
            return this;
        }
        public TaskDocument build() {
            return TaskDocument.this;
        }
    }
}
