package com.example.testproject1.controller;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.dto.staffdto.PersonDTO;
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
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:application.yaml")
public class PersonControllerTest {
    @Autowired
    private CrudRepository<Person> personRepository;
    @Autowired
    private CrudRepository<Organization> organizationRepository;
    @Autowired
    private CrudRepository<JobTittle> jobTittleRepository;
    @Autowired
    private CrudRepository<Department> departmentRepository;

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
                .setUuid(UUID.fromString("6271b589-bda1-49a0-b7e9-d04c92027d48"))
                .setName("JobName").build();
        jobTittleRepository.create(jobTittle);

        Department department = Department.newBuilder()
                .setId(UUID.fromString("22e50031-1b4f-45e3-a2a9-3e53e146c176"))
                .setFullName("departmentFullName")
                .setShortName("departmentShortName")
                .setSupervisor("VVP")
                .setContactNumber("89654345678")
                .setOrganization(organization).build();
        departmentRepository.create(department);

        Person person = Person.newBuilder()
                .setId(UUID.fromString("29dd3379-399f-4807-a4fa-e3bd51ba0588"))
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

    @AfterEach
    public void resetDb() {
        personRepository.deleteAll();
        departmentRepository.deleteAll();
        organizationRepository.deleteAll();
        jobTittleRepository.deleteAll();
    }

    @Test
    @DisplayName("Test person getById")
    void testGetById() throws Exception {
        ResponseEntity<PersonDTO> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/persons/29dd3379-399f-4807-a4fa-e3bd51ba0588").toString(), PersonDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("FirstName", response.getBody().getFirstName());
    }

    @Test
    @DisplayName("Test person getAll")
    void testGetAll() {
        ResponseEntity<List<PersonDTO>> response = restTemplate.exchange("/persons", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<PersonDTO>>() {
                });
        List<PersonDTO> taskDocumentDTOList = response.getBody();
        Assertions.assertEquals(taskDocumentDTOList.size(), 1);
        Assertions.assertEquals(taskDocumentDTOList.stream().findFirst().get().getFirstName(), "FirstName");
    }

    @Test
    @DisplayName("Test person create")
    void testCreate() throws MalformedURLException {
        Department department = Department.newBuilder().setId(UUID.fromString("22e50031-1b4f-45e3-a2a9-3e53e146c176")).build();
        JobTittle jobTittle = JobTittle.newBuilder().setUuid(UUID.fromString("6271b589-bda1-49a0-b7e9-d04c92027d48")).build();

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("FirstNameNew");
        personDTO.setSecondName("SecondNewName");
        personDTO.setBirthDay(new Date(5678l));
        personDTO.setDepartmentId(department.getId());
        personDTO.setJobTittleId(jobTittle.getId());

        ResponseEntity<PersonDTO> response = restTemplate
                .postForEntity(new URL("http://localhost:" + port + "/persons/add").toString(),
                        personDTO, PersonDTO.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(response.getBody().getSecondName(), "SecondNewName");
    }

    @Test
    @DisplayName("Test person update")
    void testUpdate() {
        Department department = Department.newBuilder().setId(UUID.fromString("22e50031-1b4f-45e3-a2a9-3e53e146c176")).build();
        JobTittle jobTittle = JobTittle.newBuilder().setUuid(UUID.fromString("6271b589-bda1-49a0-b7e9-d04c92027d48")).build();

        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(UUID.fromString("29dd3379-399f-4807-a4fa-e3bd51ba0588"));
        personDTO.setFirstName("FirstNameNew");
        personDTO.setSecondName("SecondNewNameUpdate");
        personDTO.setBirthDay(new Date(5678l));
        personDTO.setDepartmentId(department.getId());
        personDTO.setJobTittleId(jobTittle.getId());

        restTemplate.put("http://localhost:" + port + "/persons/update", personDTO);
        Optional<Person> person = personRepository.getById(UUID.fromString("29dd3379-399f-4807-a4fa-e3bd51ba0588"));
        Assertions.assertEquals(person.get().getSecondName(), "SecondNewNameUpdate");
    }

    @Test
    @DisplayName("Test person delete")
    void testDelete() {
        restTemplate.delete("http://localhost:" + port + "/persons/deleteAll");
        Optional<Person> person = personRepository.getById(UUID.fromString("29dd3379-399f-4807-a4fa-e3bd51ba0588"));
        Assertions.assertFalse(person.isPresent());
    }
}
