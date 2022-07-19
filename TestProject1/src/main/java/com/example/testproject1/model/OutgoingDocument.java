package com.example.testproject1.model;

import com.example.testproject1.model.enums.DocumentDeliveryType;

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
     *
     * @return toString класса OutgoingDocument
     */
    @Override
    public String toString() {
        Object[] taskArgs = {id, documentName, documentText, documentRegNumber, documentDate, documentAuthor, outgoingDocumentSender, outgoingDocumentDeliveryType};
        MessageFormat form = new MessageFormat(
                "Исходящий документ id= {0} documentName= {1}, documentText= {2}, documentRegNumber= {3}" +
                        ", documentData= {4}, documentAuthor= {5},  outgoingDocumentSender= {6}, outgoingDocumentDeliveryType= {7}");
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
