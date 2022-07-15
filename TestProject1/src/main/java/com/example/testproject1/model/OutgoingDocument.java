package com.example.testproject1.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

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

    public OutgoingDocument(UUID id,String documentName, String documentText, Long documentRegNumber, String documentData, String documentAuthor, String outgoingDocumentSender, String outgoingDocumentDeliveryType) {
        super(id, documentName, documentText, documentRegNumber, documentData, documentAuthor);
        this.outgoingDocumentSender = outgoingDocumentSender;
        this.outgoingDocumentDeliveryType = outgoingDocumentDeliveryType;
    }

    private OutgoingDocument() {
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

    public static OutgoingBuilder newBuilder() {
        return new OutgoingDocument().new OutgoingBuilder();
    }

    public class OutgoingBuilder{
        private OutgoingBuilder() {
            // private constructor
        }

        public OutgoingBuilder setDocId(UUID Id) {
            OutgoingDocument.this.id = Id;
            return this;
        }

        public OutgoingBuilder setDocName(String docName) {
            OutgoingDocument.this.documentName = docName;
            return this;
        }

        public OutgoingBuilder setDocText(String docText) {
            OutgoingDocument.this.documentText = docText;
            return this;
        }

        public OutgoingBuilder setDocRegNumber(Long docRegNumber) {
            OutgoingDocument.this.documentRegNumber = docRegNumber;
            return this;
        }

        public OutgoingBuilder setDocDate(String docDate) {
            OutgoingDocument.this.documentDate = docDate;
            return this;
        }
        public OutgoingBuilder setDocAuthor(String docAuthor) {
            OutgoingDocument.this.documentAuthor = docAuthor;
            return this;
        }

        public OutgoingBuilder setDocSender(String sender) {
            OutgoingDocument.this.outgoingDocumentSender = sender;
            return this;
        }

        public OutgoingBuilder setDocDeliveryType(String type) {
            OutgoingDocument.this.outgoingDocumentDeliveryType = type;
            return this;
        }

        public OutgoingDocument build() {
            return OutgoingDocument.this;
        }
    }
}
