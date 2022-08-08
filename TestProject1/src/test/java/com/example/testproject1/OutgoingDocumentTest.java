package com.example.testproject1;

import com.example.testproject1.dao.CrudRepositories;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.docfactory.OutgoingDocumentFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.yaml")
public class OutgoingDocumentTest {
    @Autowired
    private OutgoingDocumentFactory outgoingDocumentFactory;

    @Autowired
    @Qualifier("OutgoingDocumentRepository")
    private CrudRepositories outgoingDocumentRepositories;

    @Autowired
    @Qualifier("PersonRepository")
    private CrudRepositories personRepositories;

    @DisplayName("OutgoingDocumentRepositories create and getById test successful")
    @Test
    void outgoingDocumentRepositoriesCreateTest() {
        OutgoingDocument outgoingDocument = (OutgoingDocument) outgoingDocumentFactory.create();
        UUID uuid = outgoingDocument.getId();
        outgoingDocumentRepositories.create(outgoingDocument);
        OutgoingDocument outgoingDocumentDB = (OutgoingDocument) outgoingDocumentRepositories.getById(uuid.toString()).get();
        //get запрос в outgoingdocument не загружает полные данные об авторах
        Person author = (Person) personRepositories.getById(outgoingDocumentDB.getAuthor().getId().toString()).get();
        Person sender = (Person) personRepositories.getById(outgoingDocumentDB.getSender().getId().toString()).get();
        outgoingDocumentDB.setAuthor(author);
        outgoingDocumentDB.setSender(sender);
        Assertions.assertEquals(outgoingDocument, outgoingDocumentDB);
    }

    @DisplayName("OutgoingDocumentRepositories deleteAll test successful")
    @Test
    void outgoingDocumentRepositoriesDeleteAllTest() {
        OutgoingDocument outgoingDocument = (OutgoingDocument) outgoingDocumentFactory.create();
        UUID uuid = outgoingDocument.getId();
        outgoingDocumentRepositories.create(outgoingDocument);
        outgoingDocumentRepositories.deleteAll();
        Assertions.assertEquals(0, outgoingDocumentRepositories.getAll().size());
    }

    @DisplayName("OutgoingDocumentRepositories deleteById test successful")
    @Test
    void outgoingDocumentRepositoriesDeleteByIdTest() {
        OutgoingDocument outgoingDocument = (OutgoingDocument) outgoingDocumentFactory.create();
        UUID uuid = outgoingDocument.getId();
        outgoingDocumentRepositories.create(outgoingDocument);
        outgoingDocumentRepositories.deleteById(uuid.toString());
        Assertions.assertEquals(Optional.empty(), outgoingDocumentRepositories.getById(uuid.toString()));
    }

    @DisplayName("OutgoingDocumentRepositories update test successful")
    @Test
    void outgoingDocumentRepositoriesUpdateTest() {
        OutgoingDocument outgoingDocument = (OutgoingDocument) outgoingDocumentFactory.create();
        UUID uuid = outgoingDocument.getId();
        outgoingDocumentRepositories.create(outgoingDocument);
        outgoingDocument.setName("TestText");
        outgoingDocumentRepositories.update(outgoingDocument);
        OutgoingDocument outgoingDocumentDB = (OutgoingDocument) outgoingDocumentRepositories.getById(uuid.toString()).get();
        Assertions.assertEquals("TestText", outgoingDocumentDB.getName());
    }
}
