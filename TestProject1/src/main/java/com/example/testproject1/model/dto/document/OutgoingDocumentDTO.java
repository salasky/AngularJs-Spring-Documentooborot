package com.example.testproject1.model.dto.document;

import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.documentenum.DocumentDeliveryType;
import com.example.testproject1.model.staff.Person;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * DTO класс для сущности {@link OutgoingDocument}
 *
 * @author smigranov
 */
public class OutgoingDocumentDTO extends BaseDocumentDTO {
    /**
     * Отправитель
     */
    private UUID senderId;
    /**
     * способ доставки
     */
    @NotNull
    private DocumentDeliveryType deliveryType;

    public UUID getSenderId() {
        return senderId;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public DocumentDeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DocumentDeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }
    /**
     * @return возвращает объект builder
     */
    /**
     * @return возвращает объект builder
     */
    public static OutgoingDocumentDTO.OutgoingDocumentDtoBuilder newBuilder() {
        return new OutgoingDocumentDTO().new OutgoingDocumentDtoBuilder();
    }

    /**
     * Внутренний класс Builder
     *
     * @author smigranov
     */
    public class OutgoingDocumentDtoBuilder extends BaseDocumentDTOBuilder {

        public OutgoingDocumentDtoBuilder setSender(Person sender) {
            OutgoingDocumentDTO.this.senderId = sender.getId();
            return this;
        }

        public OutgoingDocumentDtoBuilder setDocumentDeliveryType(DocumentDeliveryType deliveryType) {
            OutgoingDocumentDTO.this.deliveryType = deliveryType;
            return this;
        }

        /**
         * Метод build
         *
         * @return Возвращает объект класса {@link IncomingDocumentDTO}
         */
        public OutgoingDocumentDTO build() {
            return OutgoingDocumentDTO.this;
        }
    }
}