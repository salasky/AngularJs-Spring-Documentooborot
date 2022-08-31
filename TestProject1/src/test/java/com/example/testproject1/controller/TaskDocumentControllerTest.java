package com.example.testproject1.controller;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.dto.documentdto.TaskDocumentDTO;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:application.yaml")
public class TaskDocumentControllerTest {

    @Autowired
    private CrudRepository<Person> personRepository;
    @Autowired
    private CrudRepository<Organization> organizationRepository;
    @Autowired
    private CrudRepository<JobTittle> jobTittleRepository;
    @Autowired
    private CrudRepository<Department> departmentRepository;
    @Autowired
    private CrudRepository<TaskDocument> taskDocumentCrudRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void init() throws DocflowRuntimeApplicationException {
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
                .setId(UUID.fromString("6c3869d1-0ced-4c06-afbe-bd46ddeac1e2"))
                .setFirstName("FirstName")
                .setSecondName("SecondName")
                .setLastName("LastName")
                .setDepartment(department)
                .setJobTittle(jobTittle)
                .setBirthDay(new Date(System.currentTimeMillis()))
                .setPhoneNumber("98765678903")
                .setPhoto("https://www.baeldung.com/spring-boot-testing").build();
        personRepository.create(person);

        TaskDocument taskDocument = (TaskDocument) TaskDocument.newBuilder()
                .setTaskResponsPerson(person)
                .setTaskControlPerson(person)
                .setTaskExecPeriod("2day")
                .setDocId(UUID.fromString("81a1085d-97d0-4249-9132-5f3c989ae74b"))
                .setDocName("Name")
                .setDocText("Text")
                .setDocRegNumber(new Random().nextLong())
                .setDocAuthor(person)
                .build();
        taskDocumentCrudRepository.create(taskDocument);
    }

    @AfterEach
    public void resetDb() {
        taskDocumentCrudRepository.deleteAll();
        personRepository.deleteAll();
        departmentRepository.deleteAll();
        organizationRepository.deleteAll();
        jobTittleRepository.deleteAll();
    }

    @Test
    @DisplayName("Test taskDocument getById")
    void testGetById() throws Exception {
        ResponseEntity<TaskDocumentDTO> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/taskdocuments/81a1085d-97d0-4249-9132-5f3c989ae74b").toString(), TaskDocumentDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Name", response.getBody().getName());
    }

    @Test
    @DisplayName("Test taskDocument getAll")
    void testGetAll() {
        ResponseEntity<List<TaskDocumentDTO>> response = restTemplate.exchange("/taskdocuments", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TaskDocumentDTO>>() {
                });
        List<TaskDocumentDTO> taskDocumentDTOList = response.getBody();
        Assertions.assertEquals(taskDocumentDTOList.size(), 1);
        Assertions.assertEquals(taskDocumentDTOList.stream().findFirst().get().getName(), "Name");
    }

    @Test
    @DisplayName("Test taskDocument create")
    void testCreate() throws MalformedURLException {
        Person person = Person.newBuilder().setId(UUID.fromString("6c3869d1-0ced-4c06-afbe-bd46ddeac1e2")).build();
        TaskDocumentDTO taskDocumentDTO = new TaskDocumentDTO();
        taskDocumentDTO.setResponsibleId(person.getId());
        taskDocumentDTO.setControlPersonId(person.getId());
        taskDocumentDTO.setExecPeriod("2day");
        taskDocumentDTO.setId(UUID.fromString("46d1d3e4-1512-4b8c-a755-494367e78965"));
        taskDocumentDTO.setName("NameNew");
        taskDocumentDTO.setText("TextNew");
        taskDocumentDTO.setRegNumber(new Random().nextLong());
        taskDocumentDTO.setAuthorId(person.getId());
        ResponseEntity<TaskDocumentDTO> response = restTemplate
                .postForEntity(new URL("http://localhost:" + port + "/taskdocuments/add").toString(),
                        taskDocumentDTO, TaskDocumentDTO.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(response.getBody().getName(), "NameNew");
    }

    @Test
    @DisplayName("Test taskDocument update")
    void testUpdate() {
        Person person = Person.newBuilder().setId(UUID.fromString("6c3869d1-0ced-4c06-afbe-bd46ddeac1e2")).build();
        TaskDocumentDTO taskDocumentDTO = new TaskDocumentDTO();
        taskDocumentDTO.setResponsibleId(person.getId());
        taskDocumentDTO.setControlPersonId(person.getId());
        taskDocumentDTO.setExecPeriod("2day");
        taskDocumentDTO.setId(UUID.fromString("81a1085d-97d0-4249-9132-5f3c989ae74b"));
        taskDocumentDTO.setName("Name");
        taskDocumentDTO.setText("TextUPDATE");
        taskDocumentDTO.setRegNumber(new Random().nextLong());
        taskDocumentDTO.setAuthorId(person.getId());
        restTemplate.put("http://localhost:" + port + "/taskdocuments/update", taskDocumentDTO);
        Optional<TaskDocument> taskDocumentDTO1 = taskDocumentCrudRepository.getById(UUID.fromString("81a1085d-97d0-4249-9132-5f3c989ae74b"));
        Assertions.assertEquals(taskDocumentDTO1.get().getText(), "TextUPDATE");
    }

    @Test
    @DisplayName("Test taskDocument delete")
    void testDelete() {
        restTemplate.delete("http://localhost:" + port + "/taskdocuments/deleteAll");
        Optional<TaskDocument> taskDocumentDTO1 = taskDocumentCrudRepository.getById(UUID.fromString("81a1085d-97d0-4249-9132-5f3c989ae74b"));
        Assertions.assertFalse(taskDocumentDTO1.isPresent());
    }
}
