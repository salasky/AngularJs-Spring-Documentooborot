package com.example.testproject1.model.documents;

import com.example.testproject1.model.enums.DocumentDeliveryType;
import com.example.testproject1.model.staff.Person;
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
    private Person sender;
    /**
     * способ доставки
     */
    private DocumentDeliveryType deliveryType;

    public OutgoingDocument() {
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public DocumentDeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DocumentDeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    /**
     * {@inheritDoc}
     *
     * @return toString класса OutgoingDocument
     */
    @Override
    public String toString() {
        Object[] taskArgs = {super.toString(), sender, deliveryType};
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
        return getSender().equals(that.getSender()) && getDeliveryType().equals(that.getDeliveryType());
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSender(), getDeliveryType());
    }

    /**
     * {@inheritDoc}
     * @param documentInspector принимает объект класса {@link DocumentInspector}
     * @return Возвращает объект класса String, содержащий не полную информацию о документе для отчета
     */
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
            OutgoingDocument.this.sender = sender;
            return this;
        }

        public OutgoingBuilder setDocDeliveryType(DocumentDeliveryType type) {
            OutgoingDocument.this.deliveryType = type;
            return this;
        }
        /**
         * Метод build
         * @return возвращает объект класса {@link OutgoingDocument}
         */
        public OutgoingDocument build() {
            return OutgoingDocument.this;
        }
    }
}