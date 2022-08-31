package com.example.testproject1.controller;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.documentenum.DocumentDeliveryType;
import com.example.testproject1.model.dto.documentdto.OutgoingDocumentDTO;
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
public class OutgoingDocumentControllerTest {
    @Autowired
    private CrudRepository<Person> personRepository;
    @Autowired
    private CrudRepository<Organization> organizationRepository;
    @Autowired
    private CrudRepository<JobTittle> jobTittleRepository;
    @Autowired
    private CrudRepository<Department> departmentRepository;
    @Autowired
    private CrudRepository<OutgoingDocument> outgoingDocumentCrudRepository;

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
                .setId(UUID.fromString("95c338c7-e7ea-4943-a7d3-2b38a4b461cb"))
                .setFirstName("FirstName")
                .setSecondName("SecondName")
                .setLastName("LastName")
                .setDepartment(department)
                .setJobTittle(jobTittle)
                .setBirthDay(new Date(System.currentTimeMillis()))
                .setPhoneNumber("98765678903")
                .setPhoto("https://www.baeldung.com/spring-boot-testing").build();
        personRepository.create(person);

        OutgoingDocument outgoingDocument = (OutgoingDocument) OutgoingDocument.newBuilder()
                .setDocSender(person)
                .setDocDeliveryType(DocumentDeliveryType.EMAIL)
                .setDocId(UUID.fromString("86841a35-ca25-479d-8a33-c0c87694242e"))
                .setDocName("Name")
                .setDocText("Text")
                .setDocRegNumber(new Random().nextLong())
                .setDocAuthor(person)
                .build();
        outgoingDocumentCrudRepository.create(outgoingDocument);
    }

    @AfterEach
    public void resetDb() {
        outgoingDocumentCrudRepository.deleteAll();
        personRepository.deleteAll();
        departmentRepository.deleteAll();
        organizationRepository.deleteAll();
        jobTittleRepository.deleteAll();
    }

    @Test
    @DisplayName("Test outgoingDocument getById")
    void testGetById() throws Exception {
        ResponseEntity<OutgoingDocumentDTO> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/outgoingdocuments/86841a35-ca25-479d-8a33-c0c87694242e").toString(), OutgoingDocumentDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Name", response.getBody().getName());
    }


    @Test
    @DisplayName("Test outgoingDocument getAll")
    void testGetAll() {
        ResponseEntity<List<OutgoingDocumentDTO>> response = restTemplate.exchange("/outgoingdocuments", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<OutgoingDocumentDTO>>() {
                });
        List<OutgoingDocumentDTO> outgoingDocumentDTOS = response.getBody();
        Assertions.assertEquals(outgoingDocumentDTOS.size(), 1);
        Assertions.assertEquals(outgoingDocumentDTOS.stream().findFirst().get().getName(), "Name");
    }

    @Test
    @DisplayName("Test outgoingDocument create")
    void testCreate() throws MalformedURLException {
        Person person = Person.newBuilder().setId(UUID.fromString("95c338c7-e7ea-4943-a7d3-2b38a4b461cb")).build();
        OutgoingDocumentDTO outgoingDocumentDTO = new OutgoingDocumentDTO();
        outgoingDocumentDTO.setSenderId(person.getId());
        outgoingDocumentDTO.setDeliveryType(DocumentDeliveryType.MESSENGER);
        outgoingDocumentDTO.setAuthorId(person.getId());
        outgoingDocumentDTO.setCreatingDate(new Timestamp(4567l));
        outgoingDocumentDTO.setName("NameNew");
        outgoingDocumentDTO.setText("TextNew");
        outgoingDocumentDTO.setRegNumber(new Random().nextLong());

        ResponseEntity<OutgoingDocumentDTO> response = restTemplate
                .postForEntity(new URL("http://localhost:" + port + "/outgoingdocuments/add").toString(),
                        outgoingDocumentDTO, OutgoingDocumentDTO.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(response.getBody().getName(), "NameNew");
    }

    @Test
    @DisplayName("Test outgoingDocument update")
    void testUpdate() {
        Person person = Person.newBuilder().setId(UUID.fromString("95c338c7-e7ea-4943-a7d3-2b38a4b461cb")).build();

        OutgoingDocumentDTO outgoingDocumentDTO = new OutgoingDocumentDTO();
        outgoingDocumentDTO.setSenderId(person.getId());
        outgoingDocumentDTO.setDeliveryType(DocumentDeliveryType.MESSENGER);
        outgoingDocumentDTO.setId(UUID.fromString("86841a35-ca25-479d-8a33-c0c87694242e"));
        outgoingDocumentDTO.setAuthorId(person.getId());
        outgoingDocumentDTO.setCreatingDate(new Timestamp(4567l));
        outgoingDocumentDTO.setName("NameNew");
        outgoingDocumentDTO.setText("TextUPDATE");
        outgoingDocumentDTO.setRegNumber(new Random().nextLong());
        restTemplate.put("http://localhost:" + port + "/outgoingdocuments/update", outgoingDocumentDTO);
        Optional<OutgoingDocument> outgoingDocument = outgoingDocumentCrudRepository.getById(UUID.fromString("86841a35-ca25-479d-8a33-c0c87694242e"));
        Assertions.assertTrue(outgoingDocument.isPresent());
        Assertions.assertEquals(outgoingDocument.get().getText(), "TextUPDATE");
    }

    @Test
    @DisplayName("Test outgoingDocument delete")
    void testDelete() {
        restTemplate.delete("http://localhost:" + port + "/outgoingdocuments/deleteAll");
        Optional<OutgoingDocument> optionalOutgoingDocument = outgoingDocumentCrudRepository.getById(UUID.fromString("86841a35-ca25-479d-8a33-c0c87694242e"));
        Assertions.assertFalse(optionalOutgoingDocument.isPresent());
    }
}
