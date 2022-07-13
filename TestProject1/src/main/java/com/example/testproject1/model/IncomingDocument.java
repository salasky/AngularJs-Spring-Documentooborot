package com.example.testproject1.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*@Entity
@Table(name = "IncomingDocument")*/
@Getter
@Setter
public class IncomingDocument extends BaseDocument{

    private String incomingDocumentSender;

    private String incomingDocumentDestination;

    private Long incomingDocumentNumber;

    private String incomingDocumentDate;

    public IncomingDocument( String documentName, String documentText, Long documentRegNumber, String documentData, String documentAuthor, String incomingDocumentSender, String incomingDocumentDestination, Long incomingDocumentNumber, String incomingDocumentDate) {
        super(BaseDocument.identifikator, documentName, documentText, documentRegNumber, documentData, documentAuthor);
        this.incomingDocumentSender = incomingDocumentSender;
        this.incomingDocumentDestination = incomingDocumentDestination;
        this.incomingDocumentNumber = incomingDocumentNumber;
        this.incomingDocumentDate = incomingDocumentDate;
        BaseDocument.identifikator++;
    }

    @Override
    public String toString() {
        return "IncomingDocument{" +
                ", id=" + id +
                ", documentName='" + documentName + '\'' +
                ", documentText='" + documentText + '\'' +
                ", documentRegNumber=" + documentRegNumber +
                ", documentData='" + documentData + '\'' +
                ", documentAuthor='" + documentAuthor + '\'' +
                "incomingDocumentSender='" + incomingDocumentSender + '\'' +
                ", incomingDocumentDestination='" + incomingDocumentDestination + '\'' +
                ", incomingDocumentNumber=" + incomingDocumentNumber +
                ", incomingDocumentDate='" + incomingDocumentDate + '\'' +
                '}';
    }
}
