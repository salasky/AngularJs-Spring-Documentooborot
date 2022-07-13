package com.example.testproject1.service.DocBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.model.TaskDocument;
import com.example.testproject1.shell.TaskDocumentShell;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.ArrayList;
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


    @Override
    public IncomingBuilder fixDocumentName() {
         this.documentName=TaskBuilderImpl.docNameIncomingList.get((int) ( Math.random() * 5 ));
         return this;
    }

    @Override
    public IncomingBuilder fixDocumentText() {
        this.documentText=TaskBuilderImpl.docTextList.get((int) ( Math.random() * 5 )) ;
        return this;
    }

    @Override
    public IncomingBuilder fixDocumentRegNumber() throws DocumentExistsException {
        var regNumber= Long.valueOf((int) (Math.random()*100)+100);//первые 100 номера для поручений, 100-200 для входящих

        for (BaseDocument t: TaskDocumentShell.documentList)
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
        this.documentAuthor=TaskBuilderImpl.docAuthorList.get((int) ( Math.random() * 5 )) ;
        return this;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentSender() {
        this.incomingDocumentSender=documentAuthor;
        return this;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentDestination() {
        this.incomingDocumentDestination=TaskBuilderImpl.distinPersonList.get((int) ( Math.random() * 5 )) ;
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
        TaskDocumentShell.documentList.add(incomingDoc);
        return incomingDoc;
    }
}
