package com.example.testproject1.model;


import com.example.testproject1.model.person.Person;
import com.example.testproject1.service.visitorPatternRelase.DocumentInspector;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Класc поручений. Наследник {@link BaseDocument}
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
    private Person taskResponsible;
    /**
     * признак контрольности
     */
    private Boolean taskSignOfControl;
    /**
     * контролер поручения
     */
    private Person taskControlPerson;

    public TaskDocument() {
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

    public Person getTaskResponsible() {
        return taskResponsible;
    }

    public void setTaskResponsible(Person taskResponsible) {
        this.taskResponsible = taskResponsible;
    }

    public Boolean getTaskSignOfControl() {
        return taskSignOfControl;
    }

    public void setTaskSignOfControl(Boolean taskSignOfControl) {
        this.taskSignOfControl = taskSignOfControl;
    }

    public Person getTaskControlPerson() {
        return taskControlPerson;
    }

    public void setTaskControlPerson(Person taskControlPerson) {
        this.taskControlPerson = taskControlPerson;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String toString() {
        Object[] taskArgs = {super.toString(), taskOutDate, taskExecPeriod, taskResponsible, taskSignOfControl, taskControlPerson};
        MessageFormat form = new MessageFormat(
                "Поручение {0} , taskOutDate= {1}, taskExecPeriod= {2}, taskResponsible= {3}, taskSignOfControl= {4}, taskControlPerson= {5}");
        return form.format(taskArgs);
    }

    /**
     * {@inheritDoc}
     *
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
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), taskOutDate, taskExecPeriod, taskResponsible, taskSignOfControl, taskControlPerson);
    }

    @Override
    public String accept(DocumentInspector documentInspector) {
        return documentInspector.visit(this);
    }

    /**
     * @return возвращает объект builder
     */
    public static TaskBuilder newBuilder() {
        return new TaskDocument().new TaskBuilder();
    }

    /**
     * Внутренний класс Builder
     *
     * @author smigranov
     */
    public class TaskBuilder extends BaseDocumentBuilder {
        private TaskBuilder() {
            // private constructor
        }

        public TaskBuilder setTaskDate(Date taskDate) {
            TaskDocument.this.taskOutDate = taskDate;
            return this;
        }

        public TaskBuilder setTaskExecPeriod(String taskExecPeriod) {
            TaskDocument.this.taskExecPeriod = taskExecPeriod;
            return this;
        }

        public TaskBuilder setTaskResponsPerson(Person taskResponsPerson) {
            TaskDocument.this.taskResponsible = taskResponsPerson;
            return this;
        }

        public TaskBuilder setTaskSignOfControl(Boolean taskSignOfControl) {
            TaskDocument.this.taskSignOfControl = taskSignOfControl;
            return this;
        }

        public TaskBuilder setTaskControlPerson(Person taskControlPerson) {
            TaskDocument.this.taskControlPerson = taskControlPerson;
            return this;
        }

        public TaskDocument build(BaseDocument baseDocument) {
            return TaskDocument.this;
        }
    }
}
