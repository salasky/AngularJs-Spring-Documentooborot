package com.example.testproject1;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.docfactory.TaskDocumentFactory;
import com.example.testproject1.service.importxmltodatabase.XmlToDataBaseImporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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

    @BeforeAll
    public static void init( @Autowired XmlToDataBaseImporter xmlToDataBaseImporter){
        xmlToDataBaseImporter.saveStaffInDb();
    }

    @DisplayName("TaskDocumentRepository create test successful")
    @Test
    void taskDocumentRepositoryCreateTest() {
        TaskDocument taskDocument = (TaskDocument) taskDocumentFactory.create();

        UUID uuid = taskDocument.getId();
        taskDocumentCrudRepository.create(taskDocument);
        Assertions.assertTrue(taskDocumentCrudRepository.getById(uuid.toString()).isPresent());
        TaskDocument taskDocumentDB = taskDocumentCrudRepository.getById(uuid.toString()).get();

        Person person=personCrudRepository.getById(taskDocumentDB.getAuthor().getId().toString()).get();

        Assertions.assertEquals(person,taskDocument.getAuthor());

        taskDocumentDB.setAuthor(taskDocument.getAuthor());
        taskDocumentDB.setResponsible(taskDocument.getResponsible());
        taskDocumentDB.setControlPerson(taskDocument.getControlPerson());
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
        try {
            taskDocumentCrudRepository.deleteById(uuid.toString());
        } catch (DeleteByIdException e) {
            throw new RuntimeException(e);
        }
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
