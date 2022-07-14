package com.example.testproject1.service.docBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.service.DocSave.DocSave;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Value("${doc.documentText}")
    private List<String> newDocTextList;

    @Value("${doc.documentAuthor}")
    private List<String> newDocAuthorList;

    @Value("${doc.documentIncomingList}")
    private List<String> newDocIncomingList;

    @Value("${doc.documentDistPerson}")
    private List<String> newDocDistPerson;


    @Override
    public IncomingBuilder fixDocumentName() {
         this.documentName= newDocIncomingList.get((int) ( Math.random() *newDocIncomingList.size() ));
         return this;
    }

    @Override
    public IncomingBuilder fixDocumentText() {
        this.documentText=newDocTextList.get((int) ( Math.random() * newDocTextList.size())) ;
        return this;
    }

    @Override
    public IncomingBuilder fixDocumentRegNumber() throws DocumentExistsException {
        var regNumber= Long.valueOf((int) (Math.random()*101));

        for (BaseDocument t: DocSave.documentList)
        {
            if (t.getDocumentRegNumber() == regNumber) {
                throw new DocumentExistsException(regNumber,"Document number "+regNumber+" exist");
            }
        }
        this.documentRegNumber=regNumber;
        return this;
    }

    @Override
    public IncomingBuilder fixDocumentData() {
        this.documentData="2022-"+(int)(Math.random()*12+1)+"-"+(int)(Math.random()*29+1);
        return this;
    }

    @Override
    public IncomingBuilder fixDocumentAuthor() {
        this.documentAuthor=newDocAuthorList.get((int) ( Math.random() * newDocAuthorList.size() )) ;
        return this;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentSender() {
        this.incomingDocumentSender=documentAuthor;
        return this;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentDestination() {
        this.incomingDocumentDestination=newDocDistPerson.get((int) ( Math.random() * newDocDistPerson.size() )) ;
        return this;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentNumber() {
        this.incomingDocumentNumber= Long.valueOf((int) ( Math.random() * 100));
        return this;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentDate() {
        this.incomingDocumentDate=documentData;
        return this;
    }

    @Override
    public IncomingDocument build() {
        var incomingDoc=new IncomingDocument(documentName,documentText,documentRegNumber,
                documentData,documentAuthor,incomingDocumentSender,incomingDocumentDestination,incomingDocumentNumber,incomingDocumentDate);
        return incomingDoc;
    }
}
