package com.example.testproject1.model.documents;


import com.example.testproject1.model.staff.Person;
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
    private Date outDate;
    /**
     * срок исполнения поручения
     */
    private String execPeriod;
    /**
     * ответственный исполнитель
     */
    private Person responsible;
    /**
     * признак контрольности
     */
    private Boolean signOfControl;
    /**
     * контролер поручения
     */
    private Person controlPerson;

    public TaskDocument() {
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getExecPeriod() {
        return execPeriod;
    }

    public void setExecPeriod(String execPeriod) {
        this.execPeriod = execPeriod;
    }

    public Person getResponsible() {
        return responsible;
    }

    public void setResponsible(Person responsible) {
        this.responsible = responsible;
    }

    public Boolean getSignOfControl() {
        return signOfControl;
    }

    public void setSignOfControl(Boolean signOfControl) {
        this.signOfControl = signOfControl;
    }

    public Person getControlPerson() {
        return controlPerson;
    }

    public void setControlPerson(Person controlPerson) {
        this.controlPerson = controlPerson;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String toString() {
        Object[] taskArgs = {super.toString(), outDate, execPeriod, responsible, signOfControl, controlPerson};
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
        return Objects.equals(outDate, that.outDate) && Objects.equals(execPeriod, that.execPeriod) && Objects.equals(responsible, that.responsible) && Objects.equals(signOfControl, that.signOfControl) && Objects.equals(controlPerson, that.controlPerson);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), outDate, execPeriod, responsible, signOfControl, controlPerson);
    }
    /**
     *  Метод для реализации паттерна посетитель
     */
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
            TaskDocument.this.outDate = taskDate;
            return this;
        }

        public TaskBuilder setTaskExecPeriod(String taskExecPeriod) {
            TaskDocument.this.execPeriod = taskExecPeriod;
            return this;
        }

        public TaskBuilder setTaskResponsPerson(Person taskResponsPerson) {
            TaskDocument.this.responsible = taskResponsPerson;
            return this;
        }

        public TaskBuilder setTaskSignOfControl(Boolean taskSignOfControl) {
            TaskDocument.this.signOfControl = taskSignOfControl;
            return this;
        }

        public TaskBuilder setTaskControlPerson(Person taskControlPerson) {
            TaskDocument.this.controlPerson = taskControlPerson;
            return this;
        }

        public TaskDocument build(BaseDocument baseDocument) {
            return TaskDocument.this;
        }
    }
}
