package com.example.testproject1.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OutgoingDocument extends BaseDocument{

    private String outgoingDocumentSender;

    private String outgoingDocumentDeliveryType;

    public OutgoingDocument( String documentName, String documentText, Long documentRegNumber, String documentData, String documentAuthor, String outgoingDocumentSender, String outgoingDocumentDeliveryType) {
        super(BaseDocument.identifikator, documentName, documentText, documentRegNumber, documentData, documentAuthor);
        this.outgoingDocumentSender = outgoingDocumentSender;
        this.outgoingDocumentDeliveryType = outgoingDocumentDeliveryType;
        BaseDocument.identifikator++;
    }

    @Override
    public String toString() {
        return "OutgoingDocument{" +
                ", id=" + id +
                ", documentName='" + documentName + '\'' +
                ", documentText='" + documentText + '\'' +
                ", documentRegNumber=" + documentRegNumber +
                ", documentData='" + documentData + '\'' +
                ", documentAuthor='" + documentAuthor + '\'' +
                "outgoingDocumentSender='" + outgoingDocumentSender + '\'' +
                ", outgoingDocumentDeliveryType='" + outgoingDocumentDeliveryType + '\'' +
                '}';
    }
}
