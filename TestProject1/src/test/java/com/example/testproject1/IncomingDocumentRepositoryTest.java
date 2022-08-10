package com.example.testproject1;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.docfactory.IncomingDocumentFactory;
import com.example.testproject1.service.importxmltodatabase.Impl.ImportXmlmpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.sql.Timestamp;
import java.util.UUID;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.yaml")
public class IncomingDocumentRepositoryTest {

    @Autowired
    private CrudRepository<IncomingDocument> incomingDocumentCrudRepository;

    @Autowired
    private CrudRepository<Person> personRepository;


    @BeforeAll
    public static void init( @Autowired ImportXmlmpl importXmlmpl){
        importXmlmpl.saveStaffInDb();
    }

    @DisplayName("IncomingDocumentRepository create test successful")
    @Test
    void incomingDocumentRepositoryCreateTest() {
        IncomingDocument incomingDocument =new IncomingDocument();
        incomingDocument.setId(UUID.randomUUID());
        incomingDocument.setText("text");
        incomingDocument.setName("name");
        incomingDocument.setNumber(123l);
        incomingDocument.setRegNumber(24335l);
        incomingDocument.setCreatingDate(new Timestamp(234));
        incomingDocument.setDateOfRegistration(new Timestamp(4354));
        incomingDocument.setAuthor(personRepository.getAll().stream().findFirst().get());
        incomingDocument.setSender(personRepository.getAll().stream().findFirst().get());
        incomingDocument.setDestination(personRepository.getAll().stream().findFirst().get());
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
        if(incomingDocumentCrudRepository.getAll().isEmpty()){
            IncomingDocument incomingDocument =new IncomingDocument();
            incomingDocument.setId(UUID.randomUUID());
            incomingDocument.setText("text");
            incomingDocument.setName("name");
            incomingDocument.setNumber(123l);
            incomingDocument.setRegNumber(2234435l);
            incomingDocument.setCreatingDate(new Timestamp(234));
            incomingDocument.setDateOfRegistration(new Timestamp(4354));
            incomingDocument.setAuthor(personRepository.getAll().stream().findFirst().get());
            incomingDocument.setSender(personRepository.getAll().stream().findFirst().get());
            incomingDocument.setDestination(personRepository.getAll().stream().findFirst().get());
            UUID uuid = incomingDocument.getId();
            incomingDocumentCrudRepository.create(incomingDocument);
        }
        incomingDocumentCrudRepository.deleteAll();
        Assertions.assertTrue(incomingDocumentCrudRepository.getAll().isEmpty());
        Assertions.assertEquals(0, incomingDocumentCrudRepository.getAll().size());
    }

    @DisplayName("IncomingDocumentRepository deleteById test successful")
    @Test
    void incomingDocumentRepositoryDeleteByIdTest() {
        IncomingDocument incomingDocument =new IncomingDocument();
        incomingDocument.setId(UUID.randomUUID());
        incomingDocument.setText("text");
        incomingDocument.setName("name");
        incomingDocument.setNumber(123l);
        incomingDocument.setRegNumber(2454635l);
        incomingDocument.setCreatingDate(new Timestamp(234));
        incomingDocument.setDateOfRegistration(new Timestamp(4354));
        incomingDocument.setAuthor(personRepository.getAll().stream().findFirst().get());
        incomingDocument.setSender(personRepository.getAll().stream().findFirst().get());
        incomingDocument.setDestination(personRepository.getAll().stream().findFirst().get());
        UUID uuid = incomingDocument.getId();
        incomingDocumentCrudRepository.create(incomingDocument);
        incomingDocumentCrudRepository.deleteById(uuid.toString());
        Assertions.assertTrue(incomingDocumentCrudRepository.getById(uuid.toString()).isEmpty());
    }

    @DisplayName("IncomingDocumentRepository update test successful")
    @Test
    void incomingDocumentRepositoryUpdateTest() {
        IncomingDocument incomingDocument =new IncomingDocument();
        incomingDocument.setId(UUID.randomUUID());
        incomingDocument.setText("text");
        incomingDocument.setName("name");
        incomingDocument.setNumber(123l);
        incomingDocument.setRegNumber(2432155l);
        incomingDocument.setCreatingDate(new Timestamp(234));
        incomingDocument.setDateOfRegistration(new Timestamp(4354));
        incomingDocument.setAuthor(personRepository.getAll().stream().findFirst().get());
        incomingDocument.setSender(personRepository.getAll().stream().findFirst().get());
        incomingDocument.setDestination(personRepository.getAll().stream().findFirst().get());
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
