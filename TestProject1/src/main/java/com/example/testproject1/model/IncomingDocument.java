package com.example.testproject1.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Клас входящих документов. Наследник {@link BaseDocument}
 */


public class IncomingDocument extends BaseDocument {
    /**
     * отправитель
     */
    private String incomingDocumentSender;
    /**
     * адресат
     */
    private String incomingDocumentDestination;
    /**
     * исходящий номер
     */
    private Long incomingDocumentNumber;
    /**
     * исходящая дата регистрации
     */
    private String incomingDocumentDate;

    public IncomingDocument(UUID id, String documentName, String documentText, Long documentRegNumber, String documentData, String documentAuthor, String incomingDocumentSender, String incomingDocumentDestination, Long incomingDocumentNumber, String incomingDocumentDate) {
        super(id, documentName, documentText, documentRegNumber, documentData, documentAuthor);
        this.incomingDocumentSender = incomingDocumentSender;
        this.incomingDocumentDestination = incomingDocumentDestination;
        this.incomingDocumentNumber = incomingDocumentNumber;
        this.incomingDocumentDate = incomingDocumentDate;
    }

    private IncomingDocument() {

    }

    public String getIncomingDocumentSender() {
        return incomingDocumentSender;
    }

    public void setIncomingDocumentSender(String incomingDocumentSender) {
        this.incomingDocumentSender = incomingDocumentSender;
    }

    public String getIncomingDocumentDestination() {
        return incomingDocumentDestination;
    }

    public void setIncomingDocumentDestination(String incomingDocumentDestination) {
        this.incomingDocumentDestination = incomingDocumentDestination;
    }

    public Long getIncomingDocumentNumber() {
        return incomingDocumentNumber;
    }

    public void setIncomingDocumentNumber(Long incomingDocumentNumber) {
        this.incomingDocumentNumber = incomingDocumentNumber;
    }

    public String getIncomingDocumentDate() {
        return incomingDocumentDate;
    }

    public void setIncomingDocumentDate(String incomingDocumentDate) {
        this.incomingDocumentDate = incomingDocumentDate;
    }

    @Override
    public String toString() {
        return "IncomingDocument{" +
                ", id=" + id +
                ", documentName='" + documentName + '\'' +
                ", documentText='" + documentText + '\'' +
                ", documentRegNumber=" + documentRegNumber +
                ", documentData='" + documentDate + '\'' +
                ", documentAuthor='" + documentAuthor + '\'' +
                "incomingDocumentSender='" + incomingDocumentSender + '\'' +
                ", incomingDocumentDestination='" + incomingDocumentDestination + '\'' +
                ", incomingDocumentNumber=" + incomingDocumentNumber +
                ", incomingDocumentDate='" + incomingDocumentDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IncomingDocument that = (IncomingDocument) o;
        return Objects.equals(incomingDocumentSender, that.incomingDocumentSender) && Objects.equals(incomingDocumentDestination, that.incomingDocumentDestination) && Objects.equals(incomingDocumentNumber, that.incomingDocumentNumber) && Objects.equals(incomingDocumentDate, that.incomingDocumentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), incomingDocumentSender, incomingDocumentDestination, incomingDocumentNumber, incomingDocumentDate);
    }
    public static IncomingDocumentBuilder newBuilder() {
        return new IncomingDocument().new IncomingDocumentBuilder();
    }

    public class IncomingDocumentBuilder{
        private IncomingDocumentBuilder() {
            // private constructor
        }

        public IncomingDocumentBuilder setDocId(UUID Id) {
            IncomingDocument.this.id = Id;
            return this;
        }

        public IncomingDocumentBuilder setDocName(String docName) {
            IncomingDocument.this.documentName = docName;
            return this;
        }

        public IncomingDocumentBuilder setDocText(String docText) {
            IncomingDocument.this.documentText = docText;
            return this;
        }

        public IncomingDocumentBuilder setDocRegNumber(Long docRegNumber) {
            IncomingDocument.this.documentRegNumber = docRegNumber;
            return this;
        }

        public IncomingDocumentBuilder setDocDate(String docDate) {
            IncomingDocument.this.documentDate = docDate;
            return this;
        }
        public IncomingDocumentBuilder setDocAuthor(String docAuthor) {
            IncomingDocument.this.documentAuthor = docAuthor;
            return this;
        }

        public IncomingDocumentBuilder setIncomingSender(String sender) {
            IncomingDocument.this.incomingDocumentSender = sender;
            return this;
        }

        public IncomingDocumentBuilder setIncomingDestination(String destination) {
            IncomingDocument.this.incomingDocumentDestination = destination;
            return this;
        }

        public IncomingDocumentBuilder setIncomingDocumentNumber(Long documentNumber) {
            IncomingDocument.this.incomingDocumentNumber = documentNumber;
            return this;
        }

        public IncomingDocumentBuilder setIncomingDocumentDate(String date) {
            IncomingDocument.this.incomingDocumentDate = date;
            return this;
        }

        public IncomingDocument build() {
          return IncomingDocument.this;
        }
    }
}
