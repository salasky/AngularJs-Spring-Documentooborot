package com.example.testproject1.model.dto.documentdto;

import com.example.testproject1.model.document.TaskDocument;

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
    @NotNull(message = "responsibleId не может быть null")
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


    public Boolean getSignOfControl() {
        return signOfControl;
    }

    public void setSignOfControl(Boolean signOfControl) {
        this.signOfControl = signOfControl;
    }

    public UUID getControlPersonId() {
        return controlPersonId;
    }

    public void setResponsibleId(UUID responsibleId) {
        this.responsibleId = responsibleId;
    }

    public void setControlPersonId(UUID controlPersonId) {
        this.controlPersonId = controlPersonId;
    }
}