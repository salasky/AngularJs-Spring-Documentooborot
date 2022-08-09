package com.example.testproject1;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.dao.basedocument.BaseDocumentRepository;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.docfactory.TaskDocumentFactory;
import org.junit.jupiter.api.Assertions;
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
public class BaseDocumentRepositoryTest {

    @Autowired
    private BaseDocumentRepository baseDocumentRepository;

    @Autowired
    private CrudRepository<Person> personCrudRepository;

    @Autowired
    private TaskDocumentFactory taskDocumentFactory;

    @DisplayName("BaseDocumentRepository create test successful")
    @Test
    void baseDocumentRepositoryCreateTest() {
        BaseDocument baseDocument = taskDocumentFactory.create();
        UUID uuid = baseDocument.getId();
        baseDocumentRepository.create(baseDocument);
        BaseDocument baseDocumentDB = baseDocumentRepository.getById(uuid.toString()).get();
        Assertions.assertTrue(personCrudRepository.getById(baseDocumentDB.getAuthor().getId().toString()).isPresent());
        Person author = personCrudRepository.getById(baseDocumentDB.getAuthor().getId().toString()).get();
        baseDocumentDB.setAuthor(author);
        Assertions.assertEquals(baseDocument.getId(), baseDocumentDB.getId());
        Assertions.assertEquals(baseDocument.getAuthor(), baseDocumentDB.getAuthor());
        Assertions.assertEquals(baseDocument.getName(), baseDocumentDB.getName());
    }

    @DisplayName("BaseDocumentRepository deleteAll test successful")
    @Test
    void baseDocumentRepositoryDeleteAllTest() {
        BaseDocument baseDocument = taskDocumentFactory.create();
        baseDocumentRepository.create(baseDocument);
        UUID uuid = baseDocument.getId();
        baseDocumentRepository.deleteAll();
        Assertions.assertTrue( baseDocumentRepository.getAll().isEmpty());
        Assertions.assertEquals(0, baseDocumentRepository.getAll().size());
    }

    @DisplayName("BaseDocumentRepository deleteById test successful")
    @Test
    void baseDocumentRepositoryDeleteByIdTest() {
        BaseDocument baseDocument = taskDocumentFactory.create();
        baseDocumentRepository.create(baseDocument);
        UUID uuid = baseDocument.getId();
        baseDocumentRepository.deleteById(uuid.toString());
        Assertions.assertTrue(baseDocumentRepository.getById(uuid.toString()).isEmpty());
        Assertions.assertEquals(Optional.empty(), baseDocumentRepository.getById(uuid.toString()));
    }

    @DisplayName("BaseDocumentRepository update test successful")
    @Test
    void baseDocumentRepositoryUpdateTest() {
        BaseDocument baseDocument = taskDocumentFactory.create();
        baseDocumentRepository.create(baseDocument);
        UUID uuid = baseDocument.getId();
        baseDocument.setName("TestText");
        baseDocumentRepository.update(baseDocument);
        Assertions.assertTrue(baseDocumentRepository.getById(uuid.toString()).isPresent());
        BaseDocument baseDocumentDB = baseDocumentRepository.getById(uuid.toString()).get();
        Assertions.assertEquals("TestText", baseDocumentDB.getName());
    }
}
