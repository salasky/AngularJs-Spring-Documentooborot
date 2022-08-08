package com.example.testproject1;

import com.example.testproject1.dao.CrudRepositories;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.docfactory.IncomingDocumentFactory;
import com.example.testproject1.service.docfactory.TaskDocumentFactory;
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
public class IncomingDocumentRepositoriesTest {
    @Autowired
    private IncomingDocumentFactory incomingDocumentFactory;

    @Autowired
    @Qualifier("IncomingDocumentRepository")
    private CrudRepositories incomingDocumentRepositories;

    @Autowired
    @Qualifier("PersonRepository")
    private CrudRepositories personRepositories;

    @DisplayName("IncomingDocumentRepositories create and getById test successful")
    @Test
    void incomingDocumentRepositoriesCreateTest() {
        IncomingDocument incomingDocument= (IncomingDocument) incomingDocumentFactory.create();
        UUID uuid=incomingDocument.getId();
        incomingDocumentRepositories.create(incomingDocument);
        Assertions.assertNotNull(incomingDocumentRepositories.getById(uuid.toString()));
        IncomingDocument incomingDocumentDB= (IncomingDocument) incomingDocumentRepositories.getById(uuid.toString()).get();
        //get запрос в taskDocumentRepositories не загружает полные данные об авторах
        Person author= (Person) personRepositories.getById(incomingDocumentDB.getAuthor().getId().toString()).get();
        Person sender= (Person) personRepositories.getById(incomingDocument.getSender().getId().toString()).get();
        Person destination= (Person) personRepositories.getById(incomingDocument.getDestination().getId().toString()).get();
        incomingDocumentDB.setAuthor(author);
        incomingDocumentDB.setSender(sender);
        incomingDocumentDB.setDestination(destination);
        Assertions.assertEquals(incomingDocument,incomingDocumentDB);
    }
    @DisplayName("IncomingDocumentRepositories deleteAll test successful")
    @Test
    void incomingDocumentRepositoriesDeleteAllTest() {
        IncomingDocument incomingDocument= (IncomingDocument) incomingDocumentFactory.create();
        UUID uuid=incomingDocument.getId();
        incomingDocumentRepositories.create(incomingDocument);
        incomingDocumentRepositories.deleteAll();
        Assertions.assertEquals(0,incomingDocumentRepositories.getAll().size());
    }
    @DisplayName("IncomingDocumentRepositories deleteById test successful")
    @Test
    void incomingDocumentRepositoriesDeleteByIdTest() {
        IncomingDocument incomingDocument= (IncomingDocument) incomingDocumentFactory.create();
        UUID uuid=incomingDocument.getId();
        incomingDocumentRepositories.create(incomingDocument);
        incomingDocumentRepositories.deleteById(uuid.toString());
        Assertions.assertEquals(Optional.empty(),incomingDocumentRepositories.getById(uuid.toString()));
    }

    @DisplayName("IncomingDocumentRepositories update test successful")
    @Test
    void incomingDocumentRepositoriesUpdateTest() {
        IncomingDocument incomingDocument= (IncomingDocument) incomingDocumentFactory.create();
        UUID uuid=incomingDocument.getId();
        incomingDocumentRepositories.create(incomingDocument);
        incomingDocument.setText("TestText");
        incomingDocument.setNumber(12l);
        incomingDocumentRepositories.update(incomingDocument);
        Assertions.assertNotNull(incomingDocumentRepositories.getById(uuid.toString()));
        IncomingDocument incomingDocumentDB= (IncomingDocument) incomingDocumentRepositories.getById(uuid.toString()).get();
        Assertions.assertEquals("TestText",incomingDocumentDB.getText());
        Assertions.assertEquals(12l,incomingDocumentDB.getNumber());
    }
}
