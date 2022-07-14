package com.example.testproject1.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Клас исходящих документов. Наследник {@link BaseDocument}
 *
 */

@Getter
@Setter
public class OutgoingDocument extends BaseDocument{
    /**
     * адресат
     */
    private String outgoingDocumentSender;
    /**
     * способ доставки
     */
    private String outgoingDocumentDeliveryType;

    public OutgoingDocument( String documentName, String documentText, Long documentRegNumber, String documentData, String documentAuthor, String outgoingDocumentSender, String outgoingDocumentDeliveryType) {
        super(BaseDocument.identifikator, documentName, documentText, documentRegNumber, documentData, documentAuthor);
        this.outgoingDocumentSender = outgoingDocumentSender;
        this.outgoingDocumentDeliveryType = outgoingDocumentDeliveryType;
        BaseDocument.identifikator++;
    }

    @Override
    public String toString() {
        return "OutgoingDocument{" +
                ", id=" + id +
                ", documentName='" + documentName + '\'' +
                ", documentText='" + documentText + '\'' +
                ", documentRegNumber=" + documentRegNumber +
                ", documentData='" + documentData + '\'' +
                ", documentAuthor='" + documentAuthor + '\'' +
                "outgoingDocumentSender='" + outgoingDocumentSender + '\'' +
                ", outgoingDocumentDeliveryType='" + outgoingDocumentDeliveryType + '\'' +
                '}';
    }
}
