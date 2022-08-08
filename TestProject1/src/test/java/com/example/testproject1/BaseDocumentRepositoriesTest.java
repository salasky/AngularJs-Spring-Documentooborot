package com.example.testproject1;

import com.example.testproject1.dao.CrudRepositories;
import com.example.testproject1.dao.basedocument.BaseDocumentRepository;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.staff.Person;
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
public class BaseDocumentRepositoriesTest {

    @Autowired
    private BaseDocumentRepository baseDocumentRepository;

    @Autowired
    @Qualifier("PersonRepository")
    private CrudRepositories personRepositories;

    @Autowired
    private TaskDocumentFactory taskDocumentFactory;

    @DisplayName("BaseDocumentRepositories create and getById test successful")
    @Test
    void baseDocumentRepositoriesCreateTest() {
        BaseDocument baseDocument = taskDocumentFactory.create();
        baseDocumentRepository.create(baseDocument);
        UUID uuid = baseDocument.getId();
        BaseDocument baseDocumentDB = baseDocumentRepository.getById(uuid.toString()).get();
        //get запрос в basedocument не загружает полные данные об авторах
        Person author = (Person) personRepositories.getById(baseDocumentDB.getAuthor().getId().toString()).get();
        baseDocumentDB.setAuthor(author);
        Assertions.assertEquals(baseDocument.getId(), baseDocumentDB.getId());
        Assertions.assertEquals(baseDocument.getAuthor(), baseDocumentDB.getAuthor());
        Assertions.assertEquals(baseDocument.getName(), baseDocumentDB.getName());
    }

    @DisplayName("BaseDocumentRepositories deleteAll test successful")
    @Test
    void baseDocumentRepositoriesDeleteAllTest() {
        BaseDocument baseDocument = taskDocumentFactory.create();
        baseDocumentRepository.create(baseDocument);
        UUID uuid = baseDocument.getId();
        baseDocumentRepository.deleteAll();
        Assertions.assertEquals(0, baseDocumentRepository.getAll().size());
    }

    @DisplayName("BaseDocumentRepositories deleteById test successful")
    @Test
    void baseDocumentRepositoriesDeleteByIdTest() {
        BaseDocument baseDocument = taskDocumentFactory.create();
        baseDocumentRepository.create(baseDocument);
        UUID uuid = baseDocument.getId();
        baseDocumentRepository.deleteById(uuid.toString());
        Assertions.assertEquals(Optional.empty(), baseDocumentRepository.getById(uuid.toString()));
    }

    @DisplayName("BaseDocumentRepositories update test successful")
    @Test
    void baseDocumentRepositoriesUpdateTest() {
        BaseDocument baseDocument = taskDocumentFactory.create();
        baseDocumentRepository.create(baseDocument);
        UUID uuid = baseDocument.getId();
        baseDocument.setName("TestText");
        baseDocumentRepository.update(baseDocument);
        BaseDocument baseDocumentDB = baseDocumentRepository.getById(uuid.toString()).get();
        Assertions.assertEquals("TestText", baseDocumentDB.getName());
    }
}
