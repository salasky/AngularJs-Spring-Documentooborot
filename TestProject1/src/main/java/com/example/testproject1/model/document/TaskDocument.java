package com.example.testproject1.model.document;


import com.example.testproject1.model.staff.Person;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.text.MessageFormat;
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
    @Column(name = "out_date")
    private Timestamp outDate;
    /**
     * срок исполнения поручения
     */
    @Column(name = "exec_period")
    private String execPeriod;
    /**
     * ответственный исполнитель
     */
    @Column(name = "responsible")
    private Person responsible;
    /**
     * признак контрольности
     */
    @Column(name = "sign_of_control")
    private Boolean signOfControl;
    /**
     * контролер поручения
     */
    @Column(name = "control_person")
    private Person controlPerson;

    public TaskDocument() {
    }

    public Timestamp getOutDate() {
        return outDate;
    }

    public void setOutDate(Timestamp outDate) {
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
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), outDate, execPeriod, responsible, signOfControl, controlPerson);
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

        public TaskBuilder setTaskDate(Timestamp taskDate) {
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

        /**
         * Метод build
         *
         * @return возвращает объект класса {@link TaskDocument}
         */
        public TaskDocument build(BaseDocument baseDocument) {
            return TaskDocument.this;
        }
    }
}
