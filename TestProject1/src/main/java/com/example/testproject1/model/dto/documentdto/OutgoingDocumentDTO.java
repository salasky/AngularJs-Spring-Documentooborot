package com.example.testproject1.model.dto.documentdto;

import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.documentenum.DocumentDeliveryType;

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
    @NotNull(message = "deliveryType не может быть null")
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
}