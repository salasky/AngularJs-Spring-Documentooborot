package com.example.testproject1.service.DocBuilder;

import com.example.testproject1.model.IncomingDocument;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class IncomingBuilderImpl implements IncomingBuilder{
    private String documentName;
    private String documentText;
    private Long documentRegNumber;
    private String documentData;
    private String documentAuthor;

    private String incomingDocumentSender;
    private String incomingDocumentDestination;
    private Long incomingDocumentNumber;
    private String incomingDocumentDate;

    @Override
    public IncomingBuilder fixDocumentName() {
        return null;
    }

    @Override
    public IncomingBuilder fixDocumentText() {
        return null;
    }

    @Override
    public IncomingBuilder fixDocumentRegNumber() {
        return null;
    }

    @Override
    public IncomingBuilder fixDocumentData() {
        return null;
    }

    @Override
    public IncomingBuilder fixDocumentAuthor() {
        return null;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentSender() {
        return null;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentDestination() {
        return null;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentNumber() {
        return null;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentDate() {
        return null;
    }

    @Override
    public IncomingDocument build() {
        return null;
    }
}
