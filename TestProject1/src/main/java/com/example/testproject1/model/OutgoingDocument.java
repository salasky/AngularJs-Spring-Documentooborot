package com.example.testproject1.model;

import com.example.testproject1.model.Enum.DocumentDeliveryType;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Клас исходящих документов. Наследник {@link BaseDocument}
 *
 * @author smigranov
 */
public class OutgoingDocument extends BaseDocument {
    /**
     * адресат
     */
    private String outgoingDocumentSender;
    /**
     * способ доставки
     */
    private DocumentDeliveryType outgoingDocumentDeliveryType;

    private OutgoingDocument() {
    }

    public String getOutgoingDocumentSender() {
        return outgoingDocumentSender;
    }

    public void setOutgoingDocumentSender(String outgoingDocumentSender) {
        this.outgoingDocumentSender = outgoingDocumentSender;
    }

    public DocumentDeliveryType getOutgoingDocumentDeliveryType() {
        return outgoingDocumentDeliveryType;
    }

    public void setOutgoingDocumentDeliveryType(DocumentDeliveryType outgoingDocumentDeliveryType) {
        this.outgoingDocumentDeliveryType = outgoingDocumentDeliveryType;
    }
    /**
     * {@inheritDoc}
     * @return toString класса OutgoingDocument
     */
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

    /**
     * {@inheritDoc}
     *
     * @param o Объект для сравнивания
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutgoingDocument)) return false;
        if (!super.equals(o)) return false;
        OutgoingDocument that = (OutgoingDocument) o;
        return getOutgoingDocumentSender().equals(that.getOutgoingDocumentSender()) && getOutgoingDocumentDeliveryType().equals(that.getOutgoingDocumentDeliveryType());
    }
    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOutgoingDocumentSender(), getOutgoingDocumentDeliveryType());
    }

    public static OutgoingBuilder newBuilder() {
        return new OutgoingDocument().new OutgoingBuilder();
    }

    public class OutgoingBuilder {
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

        public OutgoingBuilder setDocDate(Date docDate) {
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

        public OutgoingBuilder setDocDeliveryType(DocumentDeliveryType type) {
            OutgoingDocument.this.outgoingDocumentDeliveryType = type;
            return this;
        }

        public OutgoingDocument build() {
            return OutgoingDocument.this;
        }
    }
}
