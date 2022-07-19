package com.example.testproject1.model;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Класc входящих документов. Наследник {@link BaseDocument}
 *
 * @author smigranov
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
    private Date incomingDocumentDate;

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

    public Date getIncomingDocumentDate() {
        return incomingDocumentDate;
    }

    public void setIncomingDocumentDate(Date incomingDocumentDate) {
        this.incomingDocumentDate = incomingDocumentDate;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String toString() {
        Object[] taskArgs = {id, documentName, documentText, documentRegNumber, documentDate, documentAuthor, incomingDocumentSender, incomingDocumentDestination, incomingDocumentNumber, incomingDocumentDate};
        MessageFormat form = new MessageFormat(
                "Входящий документ id= {0} documentName= {1}, documentText= {2}, documentRegNumber= {3}" +
                        ", documentData= {4}, documentAuthor= {5},  incomingDocumentSender= {6}, incomingDocumentDestination= {7}, incomingDocumentNumber= {8}, incomingDocumentDate= {9}");
        return form.format(taskArgs);
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
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IncomingDocument that = (IncomingDocument) o;
        return Objects.equals(incomingDocumentSender, that.incomingDocumentSender) && Objects.equals(incomingDocumentDestination, that.incomingDocumentDestination) && Objects.equals(incomingDocumentNumber, that.incomingDocumentNumber) && Objects.equals(incomingDocumentDate, that.incomingDocumentDate);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), incomingDocumentSender, incomingDocumentDestination, incomingDocumentNumber, incomingDocumentDate);
    }

    /**
     * @return возвращает объект builder
     */
    public static IncomingDocumentBuilder newBuilder() {
        return new IncomingDocument().new IncomingDocumentBuilder();
    }

    /**
     * Внутренний класс Builder
     *
     * @author smigranov
     */
    public class IncomingDocumentBuilder extends BaseDocumentBuilder {
        private IncomingDocumentBuilder() {
            // private constructor
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

        public IncomingDocumentBuilder setIncomingDocumentDate(Date date) {
            IncomingDocument.this.incomingDocumentDate = date;
            return this;
        }

        public IncomingDocument build() {
            return IncomingDocument.this;
        }
    }
}
