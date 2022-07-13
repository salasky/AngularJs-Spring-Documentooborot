package com.example.testproject1.service.DocBuilder;

import com.example.testproject1.model.OutgoingDocument;

public interface OutgoingBuilder {


    public OutgoingBuilder fixDocumentName();

    public OutgoingBuilder fixDocumentText();

    public OutgoingBuilder fixDocumentRegNumber();

    public OutgoingBuilder fixDocumentData();

    public OutgoingBuilder fixDocumentAuthor();

    public OutgoingBuilder fixOutgoingDocumentSender();

    public OutgoingBuilder fixOutgoingDocumentDeliveryType();

    public OutgoingDocument build();

}
