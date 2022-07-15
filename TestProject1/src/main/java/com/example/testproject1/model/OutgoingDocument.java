package com.example.testproject1.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Клас исходящих документов. Наследник {@link BaseDocument}
 */


public class OutgoingDocument extends BaseDocument {
    /**
     * адресат
     */
    private String outgoingDocumentSender;
    /**
     * способ доставки
     */
    private String outgoingDocumentDeliveryType;

    public OutgoingDocument(String documentName, String documentText, Long documentRegNumber, String documentData, String documentAuthor, String outgoingDocumentSender, String outgoingDocumentDeliveryType) {
        super(BaseDocument.identifikator, documentName, documentText, documentRegNumber, documentData, documentAuthor);
        this.outgoingDocumentSender = outgoingDocumentSender;
        this.outgoingDocumentDeliveryType = outgoingDocumentDeliveryType;
        BaseDocument.identifikator++;
    }

    public String getOutgoingDocumentSender() {
        return outgoingDocumentSender;
    }

    public void setOutgoingDocumentSender(String outgoingDocumentSender) {
        this.outgoingDocumentSender = outgoingDocumentSender;
    }

    public String getOutgoingDocumentDeliveryType() {
        return outgoingDocumentDeliveryType;
    }

    public void setOutgoingDocumentDeliveryType(String outgoingDocumentDeliveryType) {
        this.outgoingDocumentDeliveryType = outgoingDocumentDeliveryType;
    }

    @Override
    public String toString() {
        return "OutgoingDocument{" +
                ", id=" + id +
                ", documentName='" + documentName + '\'' +
                ", documentText='" + documentText + '\'' +
                ", documentRegNumber=" + documentRegNumber +
                ", documentData='" + documentDate + '\'' +
                ", documentAuthor='" + documentAuthor + '\'' +
                "outgoingDocumentSender='" + outgoingDocumentSender + '\'' +
                ", outgoingDocumentDeliveryType='" + outgoingDocumentDeliveryType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutgoingDocument)) return false;
        if (!super.equals(o)) return false;
        OutgoingDocument that = (OutgoingDocument) o;
        return getOutgoingDocumentSender().equals(that.getOutgoingDocumentSender()) && getOutgoingDocumentDeliveryType().equals(that.getOutgoingDocumentDeliveryType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOutgoingDocumentSender(), getOutgoingDocumentDeliveryType());
    }
}
