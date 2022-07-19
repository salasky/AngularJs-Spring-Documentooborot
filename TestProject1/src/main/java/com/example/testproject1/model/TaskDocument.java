package com.example.testproject1.model;


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
     *
     * @return
     */
    @Override
    public String toString() {

        Object[] taskArgs = {id, documentName, documentText, documentRegNumber, documentDate, documentAuthor, taskOutDate, taskExecPeriod, taskResponsible, taskSignOfControl, taskControlPerson};
        MessageFormat form = new MessageFormat(
                "Поручение id= {0} documentName= {1}, documentText= {2}, documentRegNumber= {3}" +
                        ", documentData= {4}, documentAuthor= {5},  taskOutDate= {6}, taskExecPeriod= {7}, taskResponsible= {8}, taskSignOfControl= {9}, taskControlPerson= {10}  ");
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

        public TaskDocument build(BaseDocument baseDocument) {
            return TaskDocument.this;
        }
    }
}
