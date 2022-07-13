package com.example.testproject1.service.DocBuilder;

import com.example.testproject1.model.IncomingDocument;

public interface IncomingBuilder {

    public IncomingBuilder fixDocumentName();

    public IncomingBuilder fixDocumentText();

    public IncomingBuilder fixDocumentRegNumber();

    public IncomingBuilder fixDocumentData();

    public IncomingBuilder fixDocumentAuthor();

    public IncomingBuilder fixIncomingDocumentSender();

    public IncomingBuilder fixIncomingDocumentDestination();

    public IncomingBuilder fixIncomingDocumentNumber();

    public IncomingBuilder fixIncomingDocumentDate();

    public IncomingDocument build();

}
