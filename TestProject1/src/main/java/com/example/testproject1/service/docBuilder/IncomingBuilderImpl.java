package com.example.testproject1.service.docBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.service.staticList.StaticList;
import com.example.testproject1.shell.TaskDocumentShell;
import org.springframework.stereotype.Component;

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
         this.documentName= StaticList.docNameIncomingList.get((int) ( Math.random() * StaticList.docNameIncomingList.size() ));
         return this;
    }

    @Override
    public IncomingBuilder fixDocumentText() {
        this.documentText=StaticList.docTextList.get((int) ( Math.random() * StaticList.docTextList.size())) ;
        return this;
    }

    @Override
    public IncomingBuilder fixDocumentRegNumber() throws DocumentExistsException {
        var regNumber= Long.valueOf((int) (Math.random()*101));

        for (BaseDocument t:TaskDocumentShell.documentList)
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
        this.documentAuthor=StaticList.docAuthorList.get((int) ( Math.random() * StaticList.docAuthorList.size() )) ;
        return this;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentSender() {
        this.incomingDocumentSender=documentAuthor;
        return this;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentDestination() {
        this.incomingDocumentDestination=StaticList.distinPersonList.get((int) ( Math.random() * StaticList.distinPersonList.size() )) ;
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
