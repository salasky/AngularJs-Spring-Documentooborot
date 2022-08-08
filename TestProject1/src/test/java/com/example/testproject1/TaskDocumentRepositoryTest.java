package com.example.testproject1;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.docfactory.TaskDocumentFactory;
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
public class TaskDocumentRepositoryTest {

    @Autowired
    private TaskDocumentFactory taskDocumentFactory;

    @Autowired
    private CrudRepository<TaskDocument> taskDocumentCrudRepository;

    @Autowired
    private CrudRepository<Person> personCrudRepository;

    @DisplayName("TaskDocumentRepository create and getById test successful")
    @Test
    void taskDocumentRepositoryCreateTest() {
        TaskDocument taskDocument = (TaskDocument) taskDocumentFactory.create();
        UUID uuid = taskDocument.getId();
        taskDocumentCrudRepository.create(taskDocument);
        Assertions.assertTrue(taskDocumentCrudRepository.getById(uuid.toString()).isPresent());
        TaskDocument taskDocumentDB = taskDocumentCrudRepository.getById(uuid.toString()).get();
        Assertions.assertTrue(personCrudRepository.getById(taskDocumentDB.getAuthor().getId().toString()).isPresent());
        Person author = personCrudRepository.getById(taskDocumentDB.getAuthor().getId().toString()).get();
        Assertions.assertTrue(personCrudRepository.getById(taskDocumentDB.getResponsible().getId().toString()).isPresent());
        Person responsible = personCrudRepository.getById(taskDocumentDB.getResponsible().getId().toString()).get();
        Assertions.assertTrue(personCrudRepository.getById(taskDocumentDB.getControlPerson().getId().toString()).isPresent());
        Person controlPerson = personCrudRepository.getById(taskDocumentDB.getControlPerson().getId().toString()).get();
        taskDocumentDB.setAuthor(author);
        taskDocumentDB.setResponsible(responsible);
        taskDocumentDB.setControlPerson(controlPerson);
        Assertions.assertEquals(taskDocument, taskDocumentDB);
    }

    @DisplayName("TaskDocumentRepository deleteAll test successful")
    @Test
    void taskDocumentRepositoryDeleteAllTest() {
        TaskDocument taskDocument = (TaskDocument) taskDocumentFactory.create();
        UUID uuid = taskDocument.getId();
        taskDocumentCrudRepository.create(taskDocument);
        taskDocumentCrudRepository.deleteAll();
        Assertions.assertTrue(taskDocumentCrudRepository.getAll().isEmpty());
        Assertions.assertEquals(0, taskDocumentCrudRepository.getAll().size());
    }

    @DisplayName("TaskDocumentRepository deleteById test successful")
    @Test
    void taskDocumentRepositoryDeleteByIdTest() {
        TaskDocument taskDocument = (TaskDocument) taskDocumentFactory.create();
        UUID uuid = taskDocument.getId();
        taskDocumentCrudRepository.create(taskDocument);
        taskDocumentCrudRepository.deleteById(uuid.toString());
        Assertions.assertTrue(taskDocumentCrudRepository.getById(uuid.toString()).isEmpty());
    }

    @DisplayName("TaskDocumentRepository update test successful")
    @Test
    void taskDocumentRepositoryUpdateTest() {
        TaskDocument taskDocument = (TaskDocument) taskDocumentFactory.create();
        UUID uuid = taskDocument.getId();
        taskDocumentCrudRepository.create(taskDocument);
        taskDocument.setName("TestText");
        taskDocumentCrudRepository.update(taskDocument);
        Assertions.assertTrue(taskDocumentCrudRepository.getById(uuid.toString()).isPresent());
        TaskDocument taskDocumentDB = taskDocumentCrudRepository.getById(uuid.toString()).get();
        Assertions.assertEquals("TestText", taskDocumentDB.getName());
    }
}
