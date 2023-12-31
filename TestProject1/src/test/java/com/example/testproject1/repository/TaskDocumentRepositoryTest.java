package com.example.testproject1.repository;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.yaml")
public class TaskDocumentRepositoryTest {

    @Autowired
    private CrudRepository<TaskDocument> taskDocumentCrudRepository;

    @Autowired
    private CrudRepository<Person> personRepository;

    @BeforeAll
    public static void init(@Autowired CrudRepository<Person> personRepository,
                            @Autowired CrudRepository<Organization> organizationRepository,
                            @Autowired CrudRepository<JobTittle> jobTittleRepository,
                            @Autowired CrudRepository<Department> departmentRepository) throws DocflowRuntimeApplicationException {
        if (personRepository.getAll().size() == 0) {
            Organization organization = Organization.newBuilder()
                    .setId(UUID.randomUUID())
                    .setFullName("FullName")
                    .setShortName("organizationShortName")
                    .setSupervisor("orgSupervisor")
                    .setContactNumber(List.of("897643567895")).build();
            organizationRepository.create(organization);

            JobTittle jobTittle = JobTittle.newBuilder()
                    .setUuid(UUID.randomUUID())
                    .setName("JobName").build();
            jobTittleRepository.create(jobTittle);

            Department department = Department.newBuilder()
                    .setId(UUID.randomUUID())
                    .setFullName("departmentFullName")
                    .setShortName("departmentShortName")
                    .setSupervisor("VVP")
                    .setContactNumber("89654345678")
                    .setOrganization(organization).build();
            departmentRepository.create(department);

            Person person = Person.newBuilder()
                    .setId(UUID.randomUUID())
                    .setFirstName("FirstName")
                    .setSecondName("SecondName")
                    .setLastName("LastName")
                    .setDepartment(department)
                    .setJobTittle(jobTittle)
                    .setBirthDay(new Date(System.currentTimeMillis()))
                    .setPhoneNumber("98765678903")
                    .setPhoto("https://www.baeldung.com/spring-boot-testing").build();
            personRepository.create(person);
        }
    }


    private TaskDocument getTaskDocument() {
        Person person = personRepository.getAll().stream().findFirst().get();
        Random random = new Random();
        return (TaskDocument) TaskDocument.newBuilder()
                .setTaskControlPerson(person)
                .setTaskResponsPerson(person)
                .setTaskDate(new Timestamp(System.currentTimeMillis()))
                .setTaskSignOfControl(true)
                .setTaskExecPeriod("2 day")
                .setDocId(UUID.randomUUID())
                .setDocText("text")
                .setDocName("name")
                .setDocRegNumber(random.nextLong())
                .setDocDate(new Timestamp(234))
                .setDocAuthor(person).build();
    }

    @DisplayName("TaskDocumentRepository create test")
    @Test
    void taskDocumentRepositoryCreateTest() throws DocflowRuntimeApplicationException {

        TaskDocument taskDocumentExpected = getTaskDocument();
        UUID uuid = taskDocumentExpected.getId();
        taskDocumentCrudRepository.create(taskDocumentExpected);

        Optional<TaskDocument> optionalTaskDocument = taskDocumentCrudRepository.getById(uuid);
        Assertions.assertTrue(optionalTaskDocument.isPresent());
        TaskDocument taskDocumentActual = optionalTaskDocument.get();
        Assertions.assertTrue(personRepository.getById(taskDocumentActual.getAuthor().getId()).isPresent());
        taskDocumentActual.setAuthor(taskDocumentExpected.getAuthor());
        taskDocumentActual.setControlPerson(taskDocumentExpected.getControlPerson());
        taskDocumentActual.setResponsible(taskDocumentExpected.getResponsible());
        Assertions.assertEquals(taskDocumentExpected, taskDocumentActual);
    }

    @DisplayName("TaskDocumentRepository deleteAll test")
    @Test
    void taskDocumentRepositoryDeleteAllTest() throws DocflowRuntimeApplicationException {
        if (taskDocumentCrudRepository.getAll().isEmpty()) {
            taskDocumentCrudRepository.create(getTaskDocument());
        }
        taskDocumentCrudRepository.deleteAll();
        Assertions.assertTrue(taskDocumentCrudRepository.getAll().isEmpty());
    }

    @DisplayName("TaskDocumentRepository deleteById test")
    @Test
    void taskDocumentRepositoryDeleteByIdTest() throws DocflowRuntimeApplicationException {
        TaskDocument taskDocument = getTaskDocument();
        UUID uuid = taskDocument.getId();
        taskDocumentCrudRepository.create(taskDocument);
        taskDocumentCrudRepository.deleteById(uuid);
        Assertions.assertTrue(taskDocumentCrudRepository.getById(uuid).isEmpty());
    }

    @DisplayName("TaskDocumentRepository update test")
    @Test
    void taskDocumentRepositoryUpdateTest() throws DocflowRuntimeApplicationException {
        if (taskDocumentCrudRepository.getAll().isEmpty()) {
            taskDocumentCrudRepository.create(getTaskDocument());
        }
        TaskDocument taskDocument = taskDocumentCrudRepository.getAll().stream().findFirst().get();
        UUID uuid = taskDocument.getId();
        taskDocument.setText("TestText");
        taskDocumentCrudRepository.update(taskDocument);
        Assertions.assertTrue(taskDocumentCrudRepository.getById(uuid).isPresent());
        TaskDocument taskDocumentDB = taskDocumentCrudRepository.getById(uuid).get();
        Assertions.assertEquals("TestText", taskDocumentDB.getText());
    }
}
