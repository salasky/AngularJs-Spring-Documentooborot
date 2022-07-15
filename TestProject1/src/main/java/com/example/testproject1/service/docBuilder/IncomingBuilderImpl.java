package com.example.testproject1.service.docBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.service.DocSave.DocSave;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Класс реализующий паттерн Builder. Для создания экземпляров {@link IncomingDocument}
 * implements {@link IncomingBuilder}
 * @author smigranov
 * @version 1.0
 */
@Component
public class IncomingBuilderImpl implements IncomingBuilder {
    /**
     * {@link IncomingDocument#id}
     */
    private UUID id;
    /**
     * {@link IncomingDocument#documentName}
     */
    private String documentName;
    /**
     * {@link IncomingDocument#documentText}
     */
    private String documentText;
    /**
     * {@link IncomingDocument#documentRegNumber}
     */
    private Long documentRegNumber;
    /**
     * {@link IncomingDocument#documentDate}
     */
    private String documentDate;
    /**
     * {@link IncomingDocument#documentAuthor}
     */
    private String documentAuthor;
    /**
     * {@link IncomingDocument#incomingDocumentSender}
     */
    private String incomingDocumentSender;
    /**
     * {@link IncomingDocument#incomingDocumentDestination}
     */
    private String incomingDocumentDestination;
    /**
     * {@link IncomingDocument#incomingDocumentNumber}
     */
    private Long incomingDocumentNumber;
    /**
     * {@link IncomingDocument#incomingDocumentDate}
     */
    private String incomingDocumentDate;

    /**
     * Лист значений текстов из application.yaml
     */
    @Value("${doc.documentText}")
    private List<String> newDocTextList;
    /**
     * Лист значений авторов из application.yaml
     */
    @Value("${doc.documentAuthor}")
    private List<String> newDocAuthorList;
    /**
     * Лист значений названий входящих документов из application.yaml
     */
    @Value("${doc.documentIncomingList}")
    private List<String> newDocIncomingList;
    /**
     * Лист значений адресатов из application.yaml
     */
    @Value("${doc.documentDistPerson}")
    private List<String> newDocDistPerson;


    @Override
    public IncomingBuilder fixDocumentId() {
        this.id=UUID.randomUUID();
        return this;
    }

    @Override
    public IncomingBuilder fixDocumentName() {
        this.documentName = newDocIncomingList.get((int) (Math.random() * newDocIncomingList.size()));
        return this;
    }

    @Override
    public IncomingBuilder fixDocumentText() {
        this.documentText = newDocTextList.get((int) (Math.random() * newDocTextList.size()));
        return this;
    }

    @Override
    public IncomingBuilder fixDocumentRegNumber() throws DocumentExistsException {
        var regNumber = Long.valueOf((int) (Math.random() * 101));

        for (BaseDocument t : DocSave.documentList) {
            if (t.getDocumentRegNumber() == regNumber) {
                throw new DocumentExistsException(regNumber, "Document number " + regNumber + " exist");
            }
        }
        this.documentRegNumber = regNumber;
        return this;
    }

    @Override
    public IncomingBuilder fixDocumentData() {
        this.documentDate = "2022-" + (int) (Math.random() * 12 + 1) + "-" + (int) (Math.random() * 29 + 1);
        return this;
    }

    @Override
    public IncomingBuilder fixDocumentAuthor() {
        this.documentAuthor = newDocAuthorList.get((int) (Math.random() * newDocAuthorList.size()));
        return this;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentSender() {
        this.incomingDocumentSender = documentAuthor;
        return this;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentDestination() {
        this.incomingDocumentDestination = newDocDistPerson.get((int) (Math.random() * newDocDistPerson.size()));
        return this;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentNumber() {
        this.incomingDocumentNumber = Long.valueOf((int) (Math.random() * 100));
        return this;
    }

    @Override
    public IncomingBuilder fixIncomingDocumentDate() {
        this.incomingDocumentDate = documentDate;
        return this;
    }

    @Override
    public IncomingDocument build() {
        var incomingDoc = new IncomingDocument(id,documentName, documentText, documentRegNumber,
                documentDate, documentAuthor, incomingDocumentSender, incomingDocumentDestination, incomingDocumentNumber, incomingDocumentDate);
        return incomingDoc;
    }
}
