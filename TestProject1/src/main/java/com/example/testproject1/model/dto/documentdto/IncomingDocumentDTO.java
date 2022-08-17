package com.example.testproject1.model.dto.documentdto;

import com.example.testproject1.model.document.IncomingDocument;

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
    @NotNull(message = "senderId не может быть null")
    private UUID senderId;
    /**
     * адресат id
     */
    @NotNull(message = "destinationId не может быть null")
    private UUID destinationId;
    /**
     * исходящий номер
     */
    @NotNull(message = "number не может быть null")
    private Long number;
    /**
     * исходящая дата регистрации
     */
    private Timestamp dateOfRegistration;

    public UUID getSenderId() {
        return senderId;
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

