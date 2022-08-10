package com.example.testproject1;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.docfactory.OutgoingDocumentFactory;
import com.example.testproject1.service.importxmltodatabase.Impl.XmllToDataBaseImporterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.yaml")
public class OutgoingDocumentRepositoryTest {
    @Autowired
    private OutgoingDocumentFactory outgoingDocumentFactory;

    @Autowired
    private CrudRepository<OutgoingDocument> outgoingDocumentCrudRepository;

    @Autowired
    private CrudRepository<Person> personCrudRepository;

    @BeforeAll
    public static void init( @Autowired XmllToDataBaseImporterImpl importXmlmpl){
        importXmlmpl.saveStaffInDb();
    }

    @DisplayName("OutgoingDocumentRepository create test successful")
    @Test
    void outgoingDocumentRepositoryCreateTest() {
        OutgoingDocument outgoingDocument = (OutgoingDocument) outgoingDocumentFactory.create();
        UUID uuid = outgoingDocument.getId();
        outgoingDocumentCrudRepository.create(outgoingDocument);
        Assertions.assertTrue(outgoingDocumentCrudRepository.getById(uuid.toString()).isPresent());
        OutgoingDocument outgoingDocumentDB = outgoingDocumentCrudRepository.getById(uuid.toString()).get();
        Assertions.assertTrue(personCrudRepository.getById(outgoingDocumentDB.getAuthor().getId().toString()).isPresent());
        Person author = personCrudRepository.getById(outgoingDocumentDB.getAuthor().getId().toString()).get();
        Assertions.assertTrue(personCrudRepository.getById(outgoingDocumentDB.getSender().getId().toString()).isPresent());
        Person sender = personCrudRepository.getById(outgoingDocumentDB.getSender().getId().toString()).get();
        outgoingDocumentDB.setAuthor(author);
        outgoingDocumentDB.setSender(sender);
        Assertions.assertEquals(outgoingDocument, outgoingDocumentDB);
    }

    @DisplayName("OutgoingDocumentRepository deleteAll test successful")
    @Test
    void outgoingDocumentRepositoryDeleteAllTest() {
        OutgoingDocument outgoingDocument = (OutgoingDocument) outgoingDocumentFactory.create();
        UUID uuid = outgoingDocument.getId();
        outgoingDocumentCrudRepository.create(outgoingDocument);
        outgoingDocumentCrudRepository.deleteAll();
        Assertions.assertTrue(outgoingDocumentCrudRepository.getAll().isEmpty());
        Assertions.assertEquals(0, outgoingDocumentCrudRepository.getAll().size());
    }

    @DisplayName("OutgoingDocumentRepository deleteById test successful")
    @Test
    void outgoingDocumentRepositoryDeleteByIdTest() {
        OutgoingDocument outgoingDocument = (OutgoingDocument) outgoingDocumentFactory.create();
        UUID uuid = outgoingDocument.getId();
        outgoingDocumentCrudRepository.create(outgoingDocument);
        outgoingDocumentCrudRepository.deleteById(uuid.toString());
        Assertions.assertTrue(outgoingDocumentCrudRepository.getById(uuid.toString()).isEmpty());
        Assertions.assertEquals(Optional.empty(), outgoingDocumentCrudRepository.getById(uuid.toString()));
    }

    @DisplayName("OutgoingDocumentRepository update test successful")
    @Test
    void outgoingDocumentRepositoryUpdateTest() {
        OutgoingDocument outgoingDocument = (OutgoingDocument) outgoingDocumentFactory.create();
        UUID uuid = outgoingDocument.getId();
        outgoingDocumentCrudRepository.create(outgoingDocument);
        outgoingDocument.setName("TestText");
        outgoingDocumentCrudRepository.update(outgoingDocument);
        Assertions.assertTrue(outgoingDocumentCrudRepository.getById(uuid.toString()).isPresent());
        OutgoingDocument outgoingDocumentDB = outgoingDocumentCrudRepository.getById(uuid.toString()).get();
        Assertions.assertEquals("TestText", outgoingDocumentDB.getName());
    }
}
