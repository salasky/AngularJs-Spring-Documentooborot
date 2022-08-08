package com.example.testproject1;

import com.example.testproject1.dao.CrudRepositories;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.staff.Organization;
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
public class TaskDocumentRepositoriesTest {

    @Autowired
    private TaskDocumentFactory taskDocumentFactory;

    @Autowired
    @Qualifier("TaskDocumentRepository")
    private CrudRepositories taskDocumentRepositories;

    @Autowired
    @Qualifier("PersonRepository")
    private CrudRepositories personRepositories;

    @DisplayName("TaskDocumentRepositories create and getById test successful")
    @Test
    void taskDocumentRepositoriesCreateTest() {
        TaskDocument taskDocument= (TaskDocument) taskDocumentFactory.create();
        UUID uuid=taskDocument.getId();
        taskDocumentRepositories.create(taskDocument);
        Assertions.assertNotNull(taskDocumentRepositories.getById(uuid.toString()));
        TaskDocument taskDocumentDB= (TaskDocument) taskDocumentRepositories.getById(uuid.toString()).get();
        //get запрос в taskDocumentRepositories не загружает полные данные об авторах
        Person author= (Person) personRepositories.getById(taskDocumentDB.getAuthor().getId().toString()).get();
        Person responsible= (Person) personRepositories.getById(taskDocumentDB.getResponsible().getId().toString()).get();
        Person controlPerson= (Person) personRepositories.getById(taskDocumentDB.getControlPerson().getId().toString()).get();
        taskDocumentDB.setAuthor(author);
        taskDocumentDB.setResponsible(responsible);
        taskDocumentDB.setControlPerson(controlPerson);
        Assertions.assertEquals(taskDocument,taskDocumentDB);
    }

    @DisplayName("TaskDocumentRepositories deleteAll test successful")
    @Test
    void taskDocumentRepositoriesDeleteAllTest() {
        TaskDocument taskDocument= (TaskDocument) taskDocumentFactory.create();
        UUID uuid=taskDocument.getId();
        taskDocumentRepositories.create(taskDocument);
        taskDocumentRepositories.deleteAll();
        Assertions.assertEquals(0,taskDocumentRepositories.getAll().size());
    }

    @DisplayName("TaskDocumentRepositories deleteById test successful")
    @Test
    void taskDocumentRepositoriesDeleteByIdTest() {
        TaskDocument taskDocument= (TaskDocument) taskDocumentFactory.create();
        UUID uuid=taskDocument.getId();
        taskDocumentRepositories.create(taskDocument);
        taskDocumentRepositories.deleteById(uuid.toString());
        Assertions.assertEquals(Optional.empty(),taskDocumentRepositories.getById(uuid.toString()));
    }

    @DisplayName("TaskDocumentRepositories update test successful")
    @Test
    void taskDocumentRepositoriesUpdateTest() {
        TaskDocument taskDocument=(TaskDocument) taskDocumentFactory.create();
        UUID uuid=taskDocument.getId();
        taskDocumentRepositories.create(taskDocument);
        taskDocument.setName("TestText");
        taskDocumentRepositories.update(taskDocument);
        Assertions.assertNotNull(taskDocumentRepositories.getById(uuid.toString()));
        TaskDocument taskDocumentDB= (TaskDocument) taskDocumentRepositories.getById(uuid.toString()).get();
        Assertions.assertEquals("TestText",taskDocumentDB.getName());
    }
}
