package com.example.testproject1.model;

import com.example.testproject1.model.person.Person;
import com.example.testproject1.service.visitorPatternRelase.DocumentInspector;

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
    private Person incomingDocumentSender;
    /**
     * адресат
     */
    private Person incomingDocumentDestination;
    /**
     * исходящий номер
     */
    private Long incomingDocumentNumber;
    /**
     * исходящая дата регистрации
     */
    private Date incomingDocumentDate;

    public IncomingDocument() {
    }

    public Person getIncomingDocumentSender() {
        return incomingDocumentSender;
    }

    public void setIncomingDocumentSender(Person incomingDocumentSender) {
        this.incomingDocumentSender = incomingDocumentSender;
    }

    public Person getIncomingDocumentDestination() {
        return incomingDocumentDestination;
    }

    public void setIncomingDocumentDestination(Person incomingDocumentDestination) {
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
        Object[] taskArgs = {super.toString(), incomingDocumentSender, incomingDocumentDestination, incomingDocumentNumber, incomingDocumentDate};
        MessageFormat form = new MessageFormat(
                "Входящий документ {0}, incomingDocumentSender= {1}, incomingDocumentDestination= {2}, incomingDocumentNumber= {3}, incomingDocumentDate= {4}");
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

    @Override
    public String accept(DocumentInspector documentInspector) {
        return documentInspector.visit(this);
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

        public IncomingDocumentBuilder setIncomingSender(Person sender) {
            IncomingDocument.this.incomingDocumentSender = sender;
            return this;
        }

        public IncomingDocumentBuilder setIncomingDestination(Person destination) {
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
