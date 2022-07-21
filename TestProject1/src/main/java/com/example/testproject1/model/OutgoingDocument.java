package com.example.testproject1.model;

import com.example.testproject1.model.enums.DocumentDeliveryType;
import com.example.testproject1.model.person.Person;
import com.example.testproject1.service.visitorPatternRelase.DocumentInspector;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * Класc исходящих документов. Наследник {@link BaseDocument}
 *
 * @author smigranov
 */
public class OutgoingDocument extends BaseDocument {
    /**
     * адресат
     */
    private Person outgoingDocumentSender;
    /**
     * способ доставки
     */
    private DocumentDeliveryType outgoingDocumentDeliveryType;

    public OutgoingDocument() {
    }

    public Person getOutgoingDocumentSender() {
        return outgoingDocumentSender;
    }

    public void setOutgoingDocumentSender(Person outgoingDocumentSender) {
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
     *
     * @return toString класса OutgoingDocument
     */
    @Override
    public String toString() {
        Object[] taskArgs = {super.toString(), outgoingDocumentSender, outgoingDocumentDeliveryType};
        MessageFormat form = new MessageFormat(
                "Исходящий документ {0}, outgoingDocumentSender= {1}, outgoingDocumentDeliveryType= {2}");
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

    @Override
    public String accept(DocumentInspector documentInspector) {
        return documentInspector.visit(this);
    }

    /**
     * @return возвращает объект builder
     */
    public static OutgoingBuilder newBuilder() {
        return new OutgoingDocument().new OutgoingBuilder();
    }

    /**
     * Внутренний класс Builder
     *
     * @author smigranov
     */
    public class OutgoingBuilder extends BaseDocumentBuilder {
        private OutgoingBuilder() {
            // private constructor
        }

        public OutgoingBuilder setDocSender(Person sender) {
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
