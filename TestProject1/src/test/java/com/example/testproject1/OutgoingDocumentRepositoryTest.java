package com.example.testproject1;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.documentenum.DocumentDeliveryType;
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
public class OutgoingDocumentRepositoryTest {


    @Autowired
    private CrudRepository<OutgoingDocument> outgoingDocumentCrudRepository;

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


    private OutgoingDocument getOutgoingDocument() {
        Person person = personRepository.getAll().stream().findFirst().get();
        Random random = new Random();
        return (OutgoingDocument) OutgoingDocument.newBuilder()
                .setDocSender(person)
                .setDocDeliveryType(DocumentDeliveryType.EMAIL)
                .setDocId(UUID.randomUUID())
                .setDocText("text")
                .setDocName("name")
                .setDocRegNumber(random.nextLong())
                .setDocDate(new Timestamp(234))
                .setDocAuthor(person).build();
    }


    @DisplayName("OutgoingDocumentRepository create test successful")
    @Test
    void outgoingDocumentRepositoryCreateTest() throws DocflowRuntimeApplicationException {

        OutgoingDocument outgoingDocumentExpected = getOutgoingDocument();
        UUID uuid = outgoingDocumentExpected.getId();
        outgoingDocumentCrudRepository.create(outgoingDocumentExpected);

        Optional<OutgoingDocument> optionalOutgoingDocument = outgoingDocumentCrudRepository.getById(uuid.toString());
        Assertions.assertTrue(optionalOutgoingDocument.isPresent());

        OutgoingDocument outgoingDocumentActual = optionalOutgoingDocument.get();
        Assertions.assertTrue(personRepository.getById(outgoingDocumentActual.getAuthor().getId().toString()).isPresent());
        outgoingDocumentActual.setAuthor(outgoingDocumentExpected.getAuthor());
        outgoingDocumentActual.setSender(outgoingDocumentExpected.getSender());

        Assertions.assertEquals(outgoingDocumentExpected, outgoingDocumentActual);
    }

    @DisplayName("OutgoingDocumentRepository deleteAll test successful")
    @Test
    void outgoingDocumentRepositoryDeleteAllTest() throws DocflowRuntimeApplicationException {
        if (outgoingDocumentCrudRepository.getAll().isEmpty()) {
            outgoingDocumentCrudRepository.create(getOutgoingDocument());
        }
        outgoingDocumentCrudRepository.deleteAll();
        Assertions.assertTrue(outgoingDocumentCrudRepository.getAll().isEmpty());
    }

    @DisplayName("OutgoingDocumentRepository deleteById test successful")
    @Test
    void outgoingDocumentRepositoryDeleteByIdTest() throws DocflowRuntimeApplicationException {
        OutgoingDocument outgoingDocument = getOutgoingDocument();
        UUID uuid = outgoingDocument.getId();
        outgoingDocumentCrudRepository.create(outgoingDocument);
        outgoingDocumentCrudRepository.deleteById(uuid.toString());
        Assertions.assertTrue(outgoingDocumentCrudRepository.getById(uuid.toString()).isEmpty());
    }

    @DisplayName("OutgoingDocumentRepository update test successful")
    @Test
    void outgoingDocumentRepositoryUpdateTest() throws DocflowRuntimeApplicationException {
        if (outgoingDocumentCrudRepository.getAll().isEmpty()) {
            outgoingDocumentCrudRepository.create(getOutgoingDocument());
        }
        OutgoingDocument outgoingDocument = outgoingDocumentCrudRepository.getAll().stream().findFirst().get();
        UUID uuid = outgoingDocument.getId();
        outgoingDocument.setText("TestText");
        outgoingDocumentCrudRepository.update(outgoingDocument);
        Assertions.assertTrue(outgoingDocumentCrudRepository.getById(uuid.toString()).isPresent());
        OutgoingDocument outgoingDocumentDB = outgoingDocumentCrudRepository.getById(uuid.toString()).get();
        Assertions.assertEquals("TestText", outgoingDocumentDB.getText());
    }
}
