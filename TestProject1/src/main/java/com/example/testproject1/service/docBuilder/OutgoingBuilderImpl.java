package com.example.testproject1.service.docBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.OutgoingDocument;
import com.example.testproject1.service.DocSave.DocSave;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Класс реализующий паттерн Builder. Для создания экземпляров {@link OutgoingDocument}
 * implements {@link OutgoingBuilder}
 * @author smigranov
 * @version 1.0
 */
@Component
public class OutgoingBuilderImpl implements OutgoingBuilder {
    /**
     * {@link OutgoingDocument#documentName}
     */
    private String documentName;
    /**
     * {@link OutgoingDocument#documentText}
     */
    private String documentText;
    /**
     * {@link OutgoingDocument#documentRegNumber}
     */
    private Long documentRegNumber;
    /**
     * {@link OutgoingDocument#documentDate}
     */
    private String documentDate;
    /**
     * {@link OutgoingDocument#documentAuthor}
     */
    private String documentAuthor;
    /**
     * {@link OutgoingDocument#outgoingDocumentSender}
     */
    private String outgoingDocumentSender;
    /**
     * {@link OutgoingDocument#outgoingDocumentDeliveryType}
     */
    private String outgoingDocumentDeliveryType;

    /**
     * Лист текстов исходящих документов из application.yaml
     */
    @Value("${doc.documentText}")
    private List<String> newDocTextList;
    /**
     * Лист авторов исходящих документов из application.yaml
     */
    @Value("${doc.documentAuthor}")
    private List<String> newDocAuthorList;
    /**
     * Лист адресатов исходящих документов из application.yaml
     */
    @Value("${doc.documentDistPerson}")
    private List<String> newDocDistPerson;
    /**
     * Лист названий исходящих документов из application.yaml
     */
    @Value("${doc.documentOutName}")
    private List<String> newdocNameOutgoingList;
    /**
     * Лист типа доставки исходящих документов из application.yaml
     */
    @Value("${doc.docDeliveryType}")
    private List<String> newdocDeliveryTypeList;


    @Override
    public OutgoingBuilder fixDocumentName() {
        this.documentName = newdocNameOutgoingList.get((int) (Math.random() * newdocNameOutgoingList.size()));
        return this;
    }

    @Override
    public OutgoingBuilder fixDocumentText() {
        this.documentText = newDocTextList.get((int) (Math.random() * newDocTextList.size()));
        return this;
    }

    @Override
    public OutgoingBuilder fixDocumentRegNumber() throws DocumentExistsException {
        var regNumber = Long.valueOf((int) (Math.random() * 102));

        for (BaseDocument t : DocSave.documentList) {
            if (t.getDocumentRegNumber() == regNumber) {
                throw new DocumentExistsException(regNumber, "Document number " + regNumber + " exist");
            }
        }
        this.documentRegNumber = regNumber;
        return this;
    }

    @Override
    public OutgoingBuilder fixDocumentData() {
        this.documentDate = "2022-" + (int) (Math.random() * 12 + 1) + "-" + (int) (Math.random() * 29 + 1);
        return this;
    }

    @Override
    public OutgoingBuilder fixDocumentAuthor() {
        this.documentAuthor = newDocAuthorList.get((int) (Math.random() * newDocAuthorList.size()));
        return this;
    }

    @Override
    public OutgoingBuilder fixOutgoingDocumentSender() {
        this.outgoingDocumentSender = newDocDistPerson.get((int) (Math.random() * newDocDistPerson.size()));
        return this;
    }

    @Override
    public OutgoingBuilder fixOutgoingDocumentDeliveryType() {
        this.outgoingDocumentDeliveryType = newdocDeliveryTypeList.get((int) (Math.random() * newdocDeliveryTypeList.size()));
        return this;
    }

    @Override
    public OutgoingDocument build() {
        var outgoingDock = new OutgoingDocument(documentName, documentText, documentRegNumber, documentDate
                , documentAuthor, outgoingDocumentSender, outgoingDocumentDeliveryType);
        return outgoingDock;
    }
}
