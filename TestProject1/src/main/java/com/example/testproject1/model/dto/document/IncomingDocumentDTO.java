package com.example.testproject1.model.dto.document;

import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.staff.Person;

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
    private UUID senderId;
    /**
     * адресат id
     */
    private UUID destinationId;
    /**
     * исходящий номер
     */
    private Long number;
    /**
     * исходящая дата регистрации
     */
    private Timestamp dateOfRegistration;

    public UUID getSenderId() {
        return senderId;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public UUID getDestinationId() {
        return destinationId;
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

    /**
     * @return возвращает объект builder
     */
    public static IncomingDocumentDtoBuilder newBuilder() {
        return new IncomingDocumentDTO().new IncomingDocumentDtoBuilder();
    }

    /**
     * Внутренний класс Builder
     *
     * @author smigranov
     */
    public class IncomingDocumentDtoBuilder extends BaseDocumentDTOBuilder {
        
        public IncomingDocumentDtoBuilder setIncomingSender(Person sender) {
            IncomingDocumentDTO.this.senderId = sender.getId();
            return this;
        }

        public IncomingDocumentDtoBuilder setIncomingDestination(Person destination) {
            IncomingDocumentDTO.this.destinationId = destination.getId();
            return this;
        }

        public IncomingDocumentDtoBuilder setIncomingDocumentNumber(Long documentNumber) {
            IncomingDocumentDTO.this.number = documentNumber;
            return this;
        }

        public IncomingDocumentDtoBuilder setIncomingDocumentDate(Timestamp date) {
            IncomingDocumentDTO.this.dateOfRegistration = date;
            return this;
        }
        /**
         * Метод build
         *
         * @return Возвращает объект класса {@link IncomingDocumentDTO}
         */
        public IncomingDocumentDTO build() {
            return IncomingDocumentDTO.this;
        }
    }
}

