package com.example.testproject1;

import com.example.testproject1.dao.CrudRepositories;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.docfactory.TaskDocumentFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

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

}
