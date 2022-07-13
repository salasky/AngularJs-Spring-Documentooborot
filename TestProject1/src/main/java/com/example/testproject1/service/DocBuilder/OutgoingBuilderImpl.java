package com.example.testproject1.service.DocBuilder;

import com.example.testproject1.model.OutgoingDocument;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class OutgoingBuilderImpl implements OutgoingBuilder{
    private String documentName;
    private String documentText;
    private Long documentRegNumber;
    private String documentData;
    private String documentAuthor;

    private String outgoingDocumentSender;
    private String outgoingDocumentDeliveryType;

    @Override
    public OutgoingBuilder fixDocumentName() {
        return null;
    }

    @Override
    public OutgoingBuilder fixDocumentText() {
        return null;
    }

    @Override
    public OutgoingBuilder fixDocumentRegNumber() {
        return null;
    }

    @Override
    public OutgoingBuilder fixDocumentData() {
        return null;
    }

    @Override
    public OutgoingBuilder fixDocumentAuthor() {
        return null;
    }

    @Override
    public OutgoingBuilder fixOutgoingDocumentSender() {
        return null;
    }

    @Override
    public OutgoingBuilder fixOutgoingDocumentDeliveryType() {
        return null;
    }

    @Override
    public OutgoingDocument build() {
        return null;
    }
}
