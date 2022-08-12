package com.example.testproject1.shell;


import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.documentservice.GenerateDocumentService;
import com.example.testproject1.service.documentservice.GenerateReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Класс для работы с терминалом shell и запуска генерации документов и отчетов
 *
 * @author smigranov
 */
@ShellComponent
public class DocumentShell {
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentShell.class);
    /**
     * Autowired бина класса {@link GenerateDocumentService}
     */
    @Autowired
    private GenerateDocumentService generateDocumentService;
    /**
     * Autowired бина класса {@link GenerateReportService}
     */
    @Autowired
    private GenerateReportService generateReportService;
    @Autowired
    private CrudRepository<Organization> organizationCrudRepository;
    @Autowired
    private CrudRepository<Department> departmentCrudRepository;
    @Autowired
    private CrudRepository<JobTittle> jobTittleCrudRepository;
    @Autowired
    private CrudRepository<Person> personCrudRepository;
    @Autowired
    private CrudRepository<IncomingDocument> incomingDocumentCrudRepository;

    /**
     * Shell метод генерации документов и создания отчетов по ним
     *
     * @param a Генерируем заданное количество документов
     */
    @ShellMethod("Cmd: generate --a  (Int DocumentCount each type (default = 100)")
    public void generate(@ShellOption(defaultValue = "1000") int a) {
        int countDocument = Integer.valueOf(a);
        LOGGER.info("Попытка сгенерировать документы");
        generateDocumentService.generateDocument(countDocument);
        LOGGER.info("Попытка сформировать отчет по документам");
        generateReportService.saveReportByAuthor();
    }
    @ShellMethod
    public void  get(){
        Organization organization=Organization.newBuilder()
                .setId(UUID.randomUUID())
                .setFullName("FullName")
                .setShortName("organizationShortName")
                .setSupervisor("orgSupervisor")
                .setContactNumber("897643567895").build();
        UUID orgUid=organization.getId();
        List<Organization> organizationList=new ArrayList<>();
        organizationList.add(organization);
        organizationList.stream().forEach(System.out::println);
        organizationCrudRepository.saveAll(organizationList);
        Department department=Department.newBuilder()
                .setId(UUID.randomUUID())
                .setFullName("DepFwullName")
                .setShortName("DeporganizationShortName")
                .setSupervisor("DeporgSupervisor")
                .setContactNumber("Dep897643567895")
                .setOrganization(organization).build();
        List<Department> departmentList=new ArrayList<>();
        departmentList.add(department);
        departmentCrudRepository.saveAll(departmentList);

        JobTittle jobTittle=JobTittle.newBuilder()
                .setUuid(UUID.randomUUID())
                .setName("JobName")
                .build();
        JobTittle jobTittle2=JobTittle.newBuilder()
                .setUuid(UUID.randomUUID())
                .setName("Job2Name")
                .build();
        List<JobTittle> jobTittleList=new ArrayList<>();
        jobTittleList.add(jobTittle);
        jobTittleList.add(jobTittle2);
        jobTittleCrudRepository.saveAll(jobTittleList);

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
        List<Person> personList=new ArrayList<>();
        personList.add(person);
        personCrudRepository.saveAll(personList);

        Random random = new Random();
        IncomingDocument incomingDocument= (IncomingDocument) IncomingDocument.newBuilder()
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
        List<IncomingDocument> incomingDocumentList=new ArrayList<>();
        incomingDocumentList.add(incomingDocument);
        incomingDocumentCrudRepository.saveAll(incomingDocumentList);
    }
}
