package com.example.testproject1.service.docBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.OutgoingDocument;
import com.example.testproject1.service.DocSave.DocSave;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OutgoingBuilderImpl implements OutgoingBuilder{
    private String documentName;
    private String documentText;
    private Long documentRegNumber;
    private String documentData;
    private String documentAuthor;
    private String outgoingDocumentSender;
    private String outgoingDocumentDeliveryType;
    @Value("${doc.documentText}")
    private List<String> newDocTextList;

    @Value("${doc.documentAuthor}")
    private List<String> newDocAuthorList;

    @Value("${doc.documentDistPerson}")
    private List<String> newDocDistPerson;

    @Value("${doc.documentOutName}")
    private List<String> newdocNameOutgoingList;

    @Value("${doc.docDeliveryType}")
    private List<String> newdocDeliveryTypeList;


    @Override
    public OutgoingBuilder fixDocumentName() {
        this.documentName= newdocNameOutgoingList.get((int) ( Math.random() *newdocNameOutgoingList.size() )) ;
        return this;
    }

    @Override
    public OutgoingBuilder fixDocumentText() {
        this.documentText=newDocTextList.get((int) ( Math.random() * newDocTextList.size() )) ;
        return this;
    }

    @Override
    public OutgoingBuilder fixDocumentRegNumber() throws DocumentExistsException {
        var regNumber= Long.valueOf((int) (Math.random()*102));

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
    public OutgoingBuilder fixDocumentData() {
        this.documentData="2022-"+(int)(Math.random()*12+1)+"-"+(int)(Math.random()*29+1);
        return this;
    }

    @Override
    public OutgoingBuilder fixDocumentAuthor() {
        this.documentAuthor=newDocAuthorList.get((int) ( Math.random() * newDocAuthorList.size() )) ;
        return this;
    }

    @Override
    public OutgoingBuilder fixOutgoingDocumentSender() {
        this.outgoingDocumentSender=newDocDistPerson.get((int) ( Math.random() * newDocDistPerson.size() )) ;
        return this;
    }

    @Override
    public OutgoingBuilder fixOutgoingDocumentDeliveryType() {
        this.outgoingDocumentDeliveryType=newdocDeliveryTypeList.get((int) ( Math.random() * newdocDeliveryTypeList.size())) ;
        return this;
    }

    @Override
    public OutgoingDocument build() {
        var outgoingDock=new OutgoingDocument(documentName,documentText,documentRegNumber,documentData
                ,documentAuthor,outgoingDocumentSender,outgoingDocumentDeliveryType);
        return outgoingDock;
    }
}
