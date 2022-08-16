package com.example.testproject1.model.dto.document;

import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.staff.Person;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * DTO класс для сущности {@link TaskDocument}
 *
 * @author smigranov
 */
public class TaskDocumentDTO extends BaseDocumentDTO {
    /**
     * дата выдачи поручения
     */
    private Timestamp outDate;
    /**
     * срок исполнения поручения
     */
    private String execPeriod;
    /**
     * ответственный исполнитель
     */
    @NotNull
    private UUID responsibleId;
    /**
     * признак контрольности
     */
    private Boolean signOfControl;
    /**
     * контролер поручения
     */
    private UUID controlPersonId;

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

    public UUID getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(UUID responsibleId) {
        this.responsibleId = responsibleId;
    }

    public Boolean getSignOfControl() {
        return signOfControl;
    }

    public void setSignOfControl(Boolean signOfControl) {
        this.signOfControl = signOfControl;
    }

    public UUID getControlPersonId() {
        return controlPersonId;
    }

    public void setControlPersonId(UUID controlPersonId) {
        this.controlPersonId = controlPersonId;
    }
    /**
     * @return возвращает объект builder
     */
    public static TaskDocumentDTO.TaskDocumentDtoBuilder newBuilder() {
        return new TaskDocumentDTO().new TaskDocumentDtoBuilder();
    }

    /**
     * Внутренний класс Builder
     *
     * @author smigranov
     */
    public class TaskDocumentDtoBuilder extends BaseDocumentDTOBuilder {
        private TaskDocumentDtoBuilder() {
            // private constructor
        }

        public TaskDocumentDtoBuilder setTaskDate(Timestamp taskDate) {
            TaskDocumentDTO.this.outDate = taskDate;
            return this;
        }

        public TaskDocumentDtoBuilder setTaskExecPeriod(String taskExecPeriod) {
            TaskDocumentDTO.this.execPeriod = taskExecPeriod;
            return this;
        }

        public TaskDocumentDtoBuilder setTaskResponsPerson(Person taskResponsPerson) {
            TaskDocumentDTO.this.responsibleId = taskResponsPerson.getId();
            return this;
        }

        public TaskDocumentDtoBuilder setTaskSignOfControl(Boolean taskSignOfControl) {
            TaskDocumentDTO.this.signOfControl = taskSignOfControl;
            return this;
        }

        public TaskDocumentDtoBuilder setTaskControlPerson(Person taskControlPerson) {
            TaskDocumentDTO.this.controlPersonId = taskControlPerson.getId();
            return this;
        }

        /**
         * Метод build
         *
         * @return Возвращает объект класса {@link TaskDocumentDTO}
         */
        public TaskDocumentDTO build() {
            return TaskDocumentDTO.this;
        }
    }
}