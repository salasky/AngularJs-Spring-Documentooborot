package com.example.testproject1.model.dto.document;

import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.staff.Person;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * DTO класс для сущности {@link IncomingDocument}
 *
 * @author smigranov
 */
public class IncomingDocumentDTO extends BaseDocumentDTO {
    /**
     * отправитель id
     */
    @NotNull
    private UUID senderId;
    /**
     * адресат id
     */
    @NotNull
    private UUID destinationId;
    /**
     * исходящий номер
     */
    @NotNull
    private Long number;
    /**
     * исходящая дата регистрации
     */
    private Timestamp dateOfRegistration;

    public UUID getSenderId() {
        return senderId;
    }

    public void setSenderId(Person sender) {
        this.senderId = sender.getId();
    }

    public UUID getDestinationId() {
        return destinationId;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public void setDestinationId(UUID destinationId) {
        this.destinationId = destinationId;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Timestamp getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Timestamp dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

}

