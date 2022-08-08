package com.example.testproject1;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.docfactory.IncomingDocumentFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.yaml")
public class IncomingDocumentRepositoryTest {
    @Autowired
    private IncomingDocumentFactory incomingDocumentFactory;

    @Autowired
    private CrudRepository<IncomingDocument> incomingDocumentCrudRepository;

    @Autowired
    private CrudRepository<Person> personRepository;

    @DisplayName("IncomingDocumentRepository create and getById test successful")
    @Test
    void incomingDocumentRepositoryCreateTest() {
        IncomingDocument incomingDocument = (IncomingDocument) incomingDocumentFactory.create();
        UUID uuid = incomingDocument.getId();
        incomingDocumentCrudRepository.create(incomingDocument);
        Assertions.assertTrue(incomingDocumentCrudRepository.getById(uuid.toString()).isPresent());
        IncomingDocument incomingDocumentDB = incomingDocumentCrudRepository.getById(uuid.toString()).get();
        Assertions.assertTrue(personRepository.getById(incomingDocumentDB.getAuthor().getId().toString()).isPresent());
        Person author = personRepository.getById(incomingDocumentDB.getAuthor().getId().toString()).get();
        Assertions.assertTrue( personRepository.getById(incomingDocumentDB.getSender().getId().toString()).isPresent());
        Person sender = personRepository.getById(incomingDocument.getSender().getId().toString()).get();
        Assertions.assertTrue( personRepository.getById(incomingDocumentDB.getDestination().getId().toString()).isPresent());
        Person destination = personRepository.getById(incomingDocument.getDestination().getId().toString()).get();
        incomingDocumentDB.setAuthor(author);
        incomingDocumentDB.setSender(sender);
        incomingDocumentDB.setDestination(destination);
        Assertions.assertEquals(incomingDocument, incomingDocumentDB);
    }

    @DisplayName("IncomingDocumentRepository deleteAll test successful")
    @Test
    void incomingDocumentRepositoryDeleteAllTest() {
        IncomingDocument incomingDocument = (IncomingDocument) incomingDocumentFactory.create();
        UUID uuid = incomingDocument.getId();
        incomingDocumentCrudRepository.create(incomingDocument);
        incomingDocumentCrudRepository.deleteAll();
        Assertions.assertTrue(incomingDocumentCrudRepository.getAll().isEmpty());
        Assertions.assertEquals(0, incomingDocumentCrudRepository.getAll().size());
    }

    @DisplayName("IncomingDocumentRepository deleteById test successful")
    @Test
    void incomingDocumentRepositoryDeleteByIdTest() {
        IncomingDocument incomingDocument = (IncomingDocument) incomingDocumentFactory.create();
        UUID uuid = incomingDocument.getId();
        incomingDocumentCrudRepository.create(incomingDocument);
        incomingDocumentCrudRepository.deleteById(uuid.toString());
        Assertions.assertTrue(incomingDocumentCrudRepository.getById(uuid.toString()).isEmpty());
    }

    @DisplayName("IncomingDocumentRepository update test successful")
    @Test
    void incomingDocumentRepositoryUpdateTest() {
        IncomingDocument incomingDocument = (IncomingDocument) incomingDocumentFactory.create();
        UUID uuid = incomingDocument.getId();
        incomingDocumentCrudRepository.create(incomingDocument);
        incomingDocument.setText("TestText");
        incomingDocument.setNumber(12l);
        incomingDocumentCrudRepository.update(incomingDocument);
        Assertions.assertTrue(incomingDocumentCrudRepository.getById(uuid.toString()).isPresent());
        IncomingDocument incomingDocumentDB = incomingDocumentCrudRepository.getById(uuid.toString()).get();
        Assertions.assertEquals("TestText", incomingDocumentDB.getText());
        Assertions.assertEquals(12l, incomingDocumentDB.getNumber());
    }
}
