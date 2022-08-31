package com.example.testproject1.controller;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.dto.documentdto.IncomingDocumentDTO;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:application.yaml")
public class IncomingDocumentControllerTest {
    @Autowired
    private CrudRepository<Person> personRepository;
    @Autowired
    private CrudRepository<Organization> organizationRepository;
    @Autowired
    private CrudRepository<JobTittle> jobTittleRepository;
    @Autowired
    private CrudRepository<Department> departmentRepository;
    @Autowired
    private CrudRepository<IncomingDocument> incomingDocumentCrudRepository;

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
                .setId(UUID.fromString("9cdd3ed9-b5dc-47b7-8c9c-2c80d0bb08c5"))
                .setFirstName("FirstName")
                .setSecondName("SecondName")
                .setLastName("LastName")
                .setDepartment(department)
                .setJobTittle(jobTittle)
                .setBirthDay(new Date(System.currentTimeMillis()))
                .setPhoneNumber("98765678903")
                .setPhoto("https://www.baeldung.com/spring-boot-testing").build();
        personRepository.create(person);

        IncomingDocument incomingDocument = (IncomingDocument) IncomingDocument.newBuilder()
                .setIncomingDestination(person)
                .setIncomingSender(person)
                .setIncomingDocumentNumber(new Random().nextLong())
                .setIncomingDocumentDate(new Timestamp(1234l))
                .setDocId(UUID.fromString("841d042d-93e3-4b5b-a6e0-08c55f824322"))
                .setDocName("Name")
                .setDocText("Text")
                .setDocRegNumber(new Random().nextLong())
                .setDocAuthor(person)
                .build();
        incomingDocumentCrudRepository.create(incomingDocument);
    }

    @AfterEach
    public void resetDb() {
        incomingDocumentCrudRepository.deleteAll();
        personRepository.deleteAll();
        departmentRepository.deleteAll();
        organizationRepository.deleteAll();
        jobTittleRepository.deleteAll();
    }

    @Test
    @DisplayName("Test incomingDocument getById")
    void testGetById() throws Exception {
        ResponseEntity<IncomingDocumentDTO> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/incomingdocuments/841d042d-93e3-4b5b-a6e0-08c55f824322").toString(), IncomingDocumentDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Name", response.getBody().getName());
    }

    @Test
    @DisplayName("Test incomingDocument getAll")
    void testGetAll() {
        ResponseEntity<List<IncomingDocumentDTO>> response = restTemplate.exchange("/incomingdocuments", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<IncomingDocumentDTO>>() {
                });
        List<IncomingDocumentDTO> taskDocumentDTOList = response.getBody();
        Assertions.assertEquals(taskDocumentDTOList.size(), 1);
        Assertions.assertEquals(taskDocumentDTOList.stream().findFirst().get().getName(), "Name");
    }

    @Test
    @DisplayName("Test incomingDocument create")
    void testCreate() throws MalformedURLException {
        Person person = Person.newBuilder().setId(UUID.fromString("9cdd3ed9-b5dc-47b7-8c9c-2c80d0bb08c5")).build();

        IncomingDocumentDTO incomingDocumentDTO = new IncomingDocumentDTO();
        incomingDocumentDTO.setAuthorId(person.getId());
        incomingDocumentDTO.setSenderId(person.getId());
        incomingDocumentDTO.setDestinationId(person.getId());
        incomingDocumentDTO.setDateOfRegistration(new Timestamp(67890l));
        incomingDocumentDTO.setCreatingDate(new Timestamp(4567l));
        incomingDocumentDTO.setNumber(4567l);
        incomingDocumentDTO.setName("NameNew");
        incomingDocumentDTO.setText("TextNew");
        incomingDocumentDTO.setRegNumber(new Random().nextLong());

        ResponseEntity<IncomingDocumentDTO> response = restTemplate
                .postForEntity(new URL("http://localhost:" + port + "/incomingdocuments/add").toString(),
                        incomingDocumentDTO, IncomingDocumentDTO.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(response.getBody().getName(), "NameNew");
    }

    @Test
    @DisplayName("Test incomingDocument update")
    void testUpdate() {
        Person person = Person.newBuilder().setId(UUID.fromString("9cdd3ed9-b5dc-47b7-8c9c-2c80d0bb08c5")).build();

        IncomingDocumentDTO incomingDocumentDTO = new IncomingDocumentDTO();
        incomingDocumentDTO.setAuthorId(person.getId());
        incomingDocumentDTO.setSenderId(person.getId());
        incomingDocumentDTO.setDestinationId(person.getId());
        incomingDocumentDTO.setId(UUID.fromString("841d042d-93e3-4b5b-a6e0-08c55f824322"));
        incomingDocumentDTO.setName("NameNew");
        incomingDocumentDTO.setText("TextUPDATE");
        incomingDocumentDTO.setRegNumber(new Random().nextLong());
        incomingDocumentDTO.setAuthorId(person.getId());
        incomingDocumentDTO.setNumber(4567l);
        restTemplate.put("http://localhost:" + port + "/incomingdocuments/update", incomingDocumentDTO);
        Optional<IncomingDocument> incomingDocument = incomingDocumentCrudRepository.getById(UUID.fromString("841d042d-93e3-4b5b-a6e0-08c55f824322"));
        Assertions.assertTrue(incomingDocument.isPresent());
        Assertions.assertEquals(incomingDocument.get().getText(), "TextUPDATE");
    }

    @Test
    @DisplayName("Test incomingDocument delete")
    void testDelete() {
        restTemplate.delete("http://localhost:" + port + "/incomingdocuments/deleteAll");
        Optional<IncomingDocument> incomingDocument = incomingDocumentCrudRepository.getById(UUID.fromString("81a1085d-97d0-4249-9132-5f3c989ae74b"));
        Assertions.assertFalse(incomingDocument.isPresent());
    }
}
