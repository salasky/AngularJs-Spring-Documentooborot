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
    private Person sender;
    /**
     * адресат
     */
    private Person destination;
    /**
     * исходящий номер
     */
    private Long number;
    /**
     * исходящая дата регистрации
     */
    private Date dateOfRegistration;

    public IncomingDocument() {
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getDestination() {
        return destination;
    }

    public void setDestination(Person destination) {
        this.destination = destination;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String toString() {
        Object[] taskArgs = {super.toString(), sender, destination, number, dateOfRegistration};
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
        return Objects.equals(sender, that.sender) && Objects.equals(destination, that.destination) && Objects.equals(number, that.number) && Objects.equals(dateOfRegistration, that.dateOfRegistration);
    }

    /**
     * Метод для реализации паттерна посетитель
     * @param documentInspector
     * @return
     */
    @Override
    public String accept(DocumentInspector documentInspector) {
        return documentInspector.visit(this);
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sender, destination, number, dateOfRegistration);
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
            IncomingDocument.this.sender = sender;
            return this;
        }

        public IncomingDocumentBuilder setIncomingDestination(Person destination) {
            IncomingDocument.this.destination = destination;
            return this;
        }

        public IncomingDocumentBuilder setIncomingDocumentNumber(Long documentNumber) {
            IncomingDocument.this.number = documentNumber;
            return this;
        }

        public IncomingDocumentBuilder setIncomingDocumentDate(Date date) {
            IncomingDocument.this.dateOfRegistration = date;
            return this;
        }

        public IncomingDocument build() {
            return IncomingDocument.this;
        }
    }
}
