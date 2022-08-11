package com.example.testproject1;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.model.document.IncomingDocument;
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
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.yaml")
public class IncomingDocumentRepositoryTest {

    @Autowired
    private CrudRepository<IncomingDocument> incomingDocumentCrudRepository;

    @Autowired
    private CrudRepository<Person> personRepository;

    @BeforeAll
    public static void init(@Autowired CrudRepository<Person> personRepository,
                            @Autowired CrudRepository<Organization> organizationRepository,
                            @Autowired CrudRepository<JobTittle> jobTittleRepository,
                            @Autowired CrudRepository<Department> departmentRepository) {
        if (personRepository.getAll().size() == 0) {
            Organization organization = Organization.newBuilder()
                    .setId(UUID.randomUUID())
                    .setFullName("FullName")
                    .setShortName("organizationShortName")
                    .setSupervisor("orgSupervisor")
                    .setContactNumber("897643567895").build();
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


    private IncomingDocument getIncomingDocument() {
        Person person = personRepository.getAll().stream().findFirst().get();
        Random random = new Random();
        return (IncomingDocument) IncomingDocument.newBuilder()
                .setIncomingSender(person)
                .setIncomingDestination(person)
                .setIncomingDocumentNumber(132l)
                .setIncomingDocumentDate(new Timestamp(System.currentTimeMillis()))
                .setDocId(UUID.randomUUID())
                .setDocText("text")
                .setDocName("name")
                .setDocRegNumber(random.nextLong())
                .setDocDate(new Timestamp(234))
                .setDocAuthor(person).build();
    }


    @DisplayName("IncomingDocumentRepository create test successful")
    @Test
    void incomingDocumentRepositoryCreateTest() {

        IncomingDocument incomingDocumentExpected = getIncomingDocument();
        UUID uuid = incomingDocumentExpected.getId();
        incomingDocumentCrudRepository.create(incomingDocumentExpected);

        Optional<IncomingDocument> optionalIncomingDocument = incomingDocumentCrudRepository.getById(uuid.toString());
        Assertions.assertTrue(optionalIncomingDocument.isPresent());
        IncomingDocument incomingDocumentActual = optionalIncomingDocument.get();
        Assertions.assertTrue(personRepository.getById(incomingDocumentActual.getAuthor().getId().toString()).isPresent());
        incomingDocumentActual.setAuthor(incomingDocumentExpected.getAuthor());
        incomingDocumentActual.setSender(incomingDocumentExpected.getSender());
        incomingDocumentActual.setDestination(incomingDocumentExpected.getDestination());
        Assertions.assertEquals(incomingDocumentExpected, incomingDocumentActual);
    }

    @DisplayName("IncomingDocumentRepository deleteAll test successful")
    @Test
    void incomingDocumentRepositoryDeleteAllTest() {
        if (incomingDocumentCrudRepository.getAll().isEmpty()) {
            incomingDocumentCrudRepository.create(getIncomingDocument());
        }
        incomingDocumentCrudRepository.deleteAll();
        Assertions.assertTrue(incomingDocumentCrudRepository.getAll().isEmpty());
    }

    @DisplayName("IncomingDocumentRepository deleteById test successful")
    @Test
    void incomingDocumentRepositoryDeleteByIdTest() {
        IncomingDocument incomingDocument = getIncomingDocument();
        UUID uuid = incomingDocument.getId();
        incomingDocumentCrudRepository.create(incomingDocument);
        try {
            incomingDocumentCrudRepository.deleteById(uuid.toString());
        } catch (DeleteByIdException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(incomingDocumentCrudRepository.getById(uuid.toString()).isEmpty());
    }

    @DisplayName("IncomingDocumentRepository update test successful")
    @Test
    void incomingDocumentRepositoryUpdateTest() {
        if (incomingDocumentCrudRepository.getAll().isEmpty()) {
            incomingDocumentCrudRepository.create(getIncomingDocument());
        }
        IncomingDocument incomingDocument = incomingDocumentCrudRepository.getAll().stream().findFirst().get();
        UUID uuid = incomingDocument.getId();
        incomingDocument.setText("TestText");
        incomingDocument.setNumber(12l);
        incomingDocumentCrudRepository.update(incomingDocument);
        Assertions.assertTrue(incomingDocumentCrudRepository.getById(uuid.toString()).isPresent());
        IncomingDocument incomingDocumentDB = incomingDocumentCrudRepository.getById(uuid.toString()).get();
        Assertions.assertEquals("TestText", incomingDocumentDB.getText());
        Assertions.assertEquals(12l, incomingDocumentDB.getNumber());
    }
}
