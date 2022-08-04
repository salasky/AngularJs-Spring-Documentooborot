package com.example.testproject1.shell;



import com.example.testproject1.dao.baseDocument.BaseDocumentRepositoryImpl;
import com.example.testproject1.dao.department.DepartmentRepositoryImpl;
import com.example.testproject1.dao.incomingDocumrnt.IncomingDocumentRepositoryImpl;
import com.example.testproject1.dao.jobtittle.JobTittleRepositoryImpl;
import com.example.testproject1.dao.organization.OrganizationRepositoryImpl;
import com.example.testproject1.dao.outgoingDocument.OutgoingDocumentRepositoryImpl;
import com.example.testproject1.dao.person.PersonRepositoryImpl;
import com.example.testproject1.dao.taskDocument.TaskDocumentRepositoryImpl;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.documentenum.DocumentDeliveryType;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbService.taskDocument.TaskDocumentService;
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
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

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
    private OrganizationRepositoryImpl organizationRepositoryImpl;
    @Autowired
    private DepartmentRepositoryImpl departmentRepositoryImpl;
    @Autowired
    private JobTittleRepositoryImpl jobTittleRepositoryImpl;

    @Autowired
    private PersonRepositoryImpl personRepositoryImpl;

    @Autowired
    private BaseDocumentRepositoryImpl baseDocumentRepositoryImpl;

    @Autowired
    TaskDocumentRepositoryImpl taskDocumentRepositoryImpl;

    @Autowired
    IncomingDocumentRepositoryImpl incomingDocumentRepositoryImpl;

    @Autowired
    OutgoingDocumentRepositoryImpl outgoingDocumentRepositoryImpl;
    @Autowired
    TaskDocumentService taskDocumentService;
    /**
     * Shell метод генерации документов и создания отчетов по ним
     *
     * @param a Генерируем заданное количество документов
     */
    @ShellMethod("Cmd: generate --a  (Int DocumentCount each type (default = 100)")
    public void generate(@ShellOption(defaultValue = "1000") int a) {
        Integer countDocument = Integer.valueOf(a);
        LOGGER.info("Попытка сгенерировать документы");
        generateDocumentService.generateDocument(countDocument);
        LOGGER.info("Попытка сформировать отчет по документам");
        generateReportService.generateReport();
    }
    @ShellMethod()
    public void createorg() {
        Organization organization=new Organization();
        organization.setId(UUID.randomUUID());
        organization.setFullName("organizationName");
        organization.setShortName("organizationShortName");
        organization.setSupervisor("organizationSupervisor");
        organization.setContactNumber("organizationContactNumber");
        organizationRepositoryImpl.create(organization);
        System.out.println(organization +" создан");
    }
    @ShellMethod()
    public void updateorg() {
        Optional<Organization> organizations= organizationRepositoryImpl.getAll().stream().findFirst();
        if(organizations.isPresent()){
            Organization organization=organizations.get();
            organization.setFullName("organizationNameUpdate");
            organization.setShortName("organizationShortNameU");
            organization.setSupervisor("organizationSupervisorU");
            organization.setContactNumber("organizationContactNumberU");
            organizationRepositoryImpl.update(organization);
            System.out.println(organization +" обнавлен");
        }
        else
        System.out.println("Нет организации с id="+organizations.get().getId());
    }
    @ShellMethod()
    public void getallorg() {
        organizationRepositoryImpl.getAll().stream().forEach(System.out::println);
    }
    @ShellMethod()
    public void getbyidorg(String id) {
        Optional<Organization> organization= organizationRepositoryImpl.getById(id);
        if(organization.isPresent()){
            System.out.println(organization.get());
        }
        else System.out.println("не найдено");
    }
    @ShellMethod()
    public void deleteallorg() {
        organizationRepositoryImpl.deleteAll();
    }
    @ShellMethod()
    public void deletebyidorg(String id) {
        System.out.println(organizationRepositoryImpl.deleteById(id));
    }
    @ShellMethod()
    public void createdep() {
        Department department=new Department();
        department.setId(UUID.randomUUID());
        department.setFullName("departmentName");
        department.setShortName("departmentShortName");
        department.setSupervisor("departmentSupervisor");
        department.setContactNumber("departmentContactNumber");
        department.setOrganization(organizationRepositoryImpl.getAll().get(0));
        departmentRepositoryImpl.create(department);
        System.out.println(department +" создан");
    }

    @ShellMethod()
    public void updatedep() {
        Optional<Department> departments= departmentRepositoryImpl.getAll().stream().findFirst();
        if(departments.isPresent()){
            Department department=departments.get();
            department.setFullName("departmentNameUpdate2");
            department.setShortName("departmentShortNameU");
            department.setSupervisor("departmentSupervisorU");
            department.setContactNumber("departmentContactNumberU");
            department.setOrganization(organizationRepositoryImpl.getAll().stream().findFirst().get());
            departmentRepositoryImpl.update(department);
            System.out.println(department +" обнавлен");
        }
        else
            System.out.println("Нет department с id="+departments.get().getId());
    }
    @ShellMethod()
    public void getalldep() {
        departmentRepositoryImpl.getAll().stream().forEach(System.out::println);
    }

    @ShellMethod()
    public void getbyiddep(String id) {
        System.out.println( departmentRepositoryImpl.getById(id).get());
    }

    @ShellMethod()
    public void deletealldep() {
        departmentRepositoryImpl.deleteAll();
    }

    @ShellMethod()
    public Integer deletebyiddep(String id) {
        return departmentRepositoryImpl.deleteById(id);
    }

    @ShellMethod()
    public void createjob() {
        JobTittle jobTittle=new JobTittle();
        jobTittle.setUuid(UUID.randomUUID());
        jobTittle.setName("JobName");
        jobTittleRepositoryImpl.create(jobTittle);
        System.out.println(jobTittle +" создан");
    }
    @ShellMethod()
    public void updatejob() {
        Optional<JobTittle> jobTittles= jobTittleRepositoryImpl.getAll().stream().findFirst();
        if(jobTittles.isPresent()){
            JobTittle jobTittle=jobTittles.get();
            jobTittle.setName("JobNameU");
            jobTittleRepositoryImpl.update(jobTittle);
            System.out.println(jobTittle +" обнавлен");
        }
        else
            System.out.println("Нет department с id="+jobTittles.get().getUuid());
    }
    @ShellMethod()
    public void getalljob() {
        jobTittleRepositoryImpl.getAll().stream().forEach(System.out::println);
    }
    @ShellMethod()
    public void getbyidjob(String id) {
        Optional<JobTittle> jobTittle= jobTittleRepositoryImpl.getById(id);
        if(jobTittle.isPresent()){
            System.out.println(jobTittle.get());
        }
        else System.out.println("не найдено");
    }
    @ShellMethod()
    public void deletealljob() {
        jobTittleRepositoryImpl.deleteAll();
    }
    @ShellMethod()
    public void deletebyidjob(String id) {
        System.out.println(jobTittleRepositoryImpl.deleteById(id));
    }

    @ShellMethod()
    public void createpers() {
        Person person=new Person();
        person.setId(UUID.randomUUID());
        person.setFirstName("personsetFirstName");
        person.setSecondName("personsetSecondName");
        person.setLastName("personsetLastName");
        person.setPhoto("personsetPhoto");
        JobTittle jobTittle= jobTittleRepositoryImpl.getAll().stream().findFirst().get();
        person.setJobTittle(jobTittle);
        Department department= departmentRepositoryImpl.getAll().stream().findFirst().get();
        person.setDepartment(department);
        person.setPhoneNumber("personsetPhoneNumber22");
        person.setBirthDay(new Date(System.currentTimeMillis()));
        personRepositoryImpl.create(person);
        System.out.println(person +" создан");
    }

    @ShellMethod()
    public void getallper() {
       personRepositoryImpl.getAll().stream().forEach(System.out::println);
    }

    @ShellMethod()
    public void getbyidper(String id) {
        Optional<Person> person= personRepositoryImpl.getById(id);
        if(person.isPresent()){
            System.out.println(person.get());
        }
        else System.out.println("не найдено");
    }
    @ShellMethod()
    public void updatepers() {
        Optional<Person> persons= personRepositoryImpl.getAll().stream().findFirst();
        if(persons.isPresent()){
            Person person=persons.get();
            person.setFirstName("FirstUpdateName");
            personRepositoryImpl.update(person);
            System.out.println(person +" обнавлен");
        }
        else
            System.out.println("Нет person с id="+persons.get().getId().toString());
    }
    @ShellMethod()
    public void deleteallpers() {
        personRepositoryImpl.deleteAll();
    }
    @ShellMethod()
    public void deletebyidpers(String id) {
        System.out.println(personRepositoryImpl.deleteById(id));
    }

    @ShellMethod()
    public void createbdoc() {
        BaseDocument baseDocument=new BaseDocument();
        baseDocument.setId(UUID.fromString("00505b60-9031-4068-b5f6-d7e5fa1b7d1c"));
        baseDocument.setName("BaseDocName");
        baseDocument.setText("BaseDocText");
        baseDocument.setRegNumber(ThreadLocalRandom.current().nextLong(1000));
        long offset = Timestamp.valueOf("2015-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2022-08-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
        baseDocument.setCreatingDate(rand);
        Person person= personRepositoryImpl.getAll().stream().findFirst().get();
        baseDocument.setAuthor(person);
        baseDocumentRepositoryImpl.create(baseDocument);
        System.out.println(baseDocumentRepositoryImpl.getById("00505b60-9031-4068-b5f6-d7e5fa1b7d1c") +" создан");
    }

    @ShellMethod()
    public void getallbdoc() {
        baseDocumentRepositoryImpl.getAll().stream().forEach(System.out::println);
    }

    @ShellMethod()
    public void getbyidbdoc(String id) {
        Optional<BaseDocument> baseDocument= baseDocumentRepositoryImpl.getById(id);
        if(baseDocument.isPresent()){
            System.out.println(baseDocument.get());
        }
        else System.out.println("не найдено");
    }
    @ShellMethod()
    public void updatebdoc() {
        Optional<BaseDocument> baseDocuments= baseDocumentRepositoryImpl.getAll().stream().findFirst();
        if(baseDocuments.isPresent()){
            BaseDocument baseDocument=baseDocuments.get();
            baseDocument.setName("BaseNameU");
            baseDocumentRepositoryImpl.update(baseDocument);
            System.out.println(baseDocument +" обнавлен");
        }
        else
            System.out.println("Нет department с id="+baseDocuments.get().getId());
    }

    @ShellMethod()
    public void deleteallbdoc() {
        baseDocumentRepositoryImpl.deleteAll();
    }
    @ShellMethod()
    public void deletebyidbdoc(String id) {
        System.out.println(baseDocumentRepositoryImpl.deleteById(id));
    }
    @ShellMethod()
    public void createtask() {
        TaskDocument taskDocument=taskDocumentRepositoryImpl.getAll().stream().findFirst().get();


        taskDocumentService.create(taskDocument);

    }

    @ShellMethod()
    public void getalltask() {
        taskDocumentRepositoryImpl.getAll().stream().forEach(System.out::println);
    }
    @ShellMethod()
    public void getbyidtask(String id) {
        Optional<TaskDocument> taskDocument= taskDocumentRepositoryImpl.getById(id);
        if(taskDocument.isPresent()){
            System.out.println(taskDocument.get());
        }
        else System.out.println("не найдено");
    }
    @ShellMethod()
    public void updatetask() {
        Optional<TaskDocument> taskDocuments= taskDocumentRepositoryImpl.getAll().stream().findFirst();
        if(taskDocuments.isPresent()){
            TaskDocument taskDocument=taskDocuments.get();
            taskDocument.setName("BASENameU");
            taskDocument.setExecPeriod("12 дней");
            taskDocumentRepositoryImpl.update(taskDocument);
            System.out.println(taskDocument +" обнавлен");
        }
        else
            System.out.println("Нет department с id="+taskDocuments.get().getId());
    }
    @ShellMethod()
    public void deletealltask() {
        taskDocumentRepositoryImpl.deleteAll();
    }
    @ShellMethod()
    public void deletebyidtask(String id) {
        System.out.println(taskDocumentRepositoryImpl.deleteById(id));
    }
    @ShellMethod()
    public void createincom() {
        IncomingDocument incomingDocument=new IncomingDocument();
        incomingDocument.setId(baseDocumentRepositoryImpl.getAll().stream().findFirst().get().getId());
        long offset = Timestamp.valueOf("2015-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2022-08-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
        incomingDocument.setDateOfRegistration(rand);
        incomingDocument.setNumber(2343l);

        Person personResponse= personRepositoryImpl.getAll().stream().findFirst().get();
        incomingDocument.setSender(personResponse);
        incomingDocument.setDestination(personResponse);

        incomingDocumentRepositoryImpl.create(incomingDocument);
    }

    @ShellMethod()
    public void getallincom() {
        incomingDocumentRepositoryImpl.getAll().stream().forEach(System.out::println);
    }

    @ShellMethod()
    public void getbyidincom(String id) {
        Optional<IncomingDocument> incomingDocument= incomingDocumentRepositoryImpl.getById(id);
        if(incomingDocument.isPresent()){
            System.out.println(incomingDocument.get());
        }
        else System.out.println("не найдено");
    }
    @ShellMethod()
    public void updateincome() {
        Optional<IncomingDocument> incomingDocuments= incomingDocumentRepositoryImpl.getAll().stream().findFirst();
        if(incomingDocuments.isPresent()){
            IncomingDocument incomingDocument=incomingDocuments.get();
            incomingDocument.setNumber(1l);
            incomingDocumentRepositoryImpl.update(incomingDocument);
            System.out.println(incomingDocument +" обнавлен");
        }
        else
            System.out.println("Нет department с id="+incomingDocuments.get().getId());
    }
    @ShellMethod()
    public void deleteallincom() {
        incomingDocumentRepositoryImpl.deleteAll();
    }
    @ShellMethod()
    public void deletebyidincom(String id) {
        System.out.println(incomingDocumentRepositoryImpl.deleteById(id));
    }

    @ShellMethod()
    public void createout() {
        OutgoingDocument outgoingDocument=new OutgoingDocument();
        outgoingDocument.setId(baseDocumentRepositoryImpl.getAll().stream().findFirst().get().getId());
        outgoingDocument.setRegNumber(3349268837256532463l);
        Person personResponse= personRepositoryImpl.getAll().stream().findFirst().get();
        outgoingDocument.setSender(personResponse);

        outgoingDocument.setDeliveryType(DocumentDeliveryType.EMAIL);

        outgoingDocumentRepositoryImpl.create(outgoingDocument);

    }
    @ShellMethod()
    public void getallout() {
        outgoingDocumentRepositoryImpl.getAll().stream().forEach(System.out::println);
    }
    @ShellMethod()
    public void getbyidout(String id) {
        Optional<OutgoingDocument> outgoingDocument= outgoingDocumentRepositoryImpl.getById(id);
        if(outgoingDocument.isPresent()){
            System.out.println(outgoingDocument.get());
        }
        else System.out.println("не найдено");
    }

    @ShellMethod()
    public void updateout() {
        Optional<OutgoingDocument> outgoingDocuments= outgoingDocumentRepositoryImpl.getAll().stream().findFirst();
        if(outgoingDocuments.isPresent()){
            OutgoingDocument outgoingDocument=outgoingDocuments.get();
            outgoingDocument.setDeliveryType(DocumentDeliveryType.MESSENGER);
            outgoingDocumentRepositoryImpl.update(outgoingDocument);
            System.out.println(outgoingDocument +" обнавлен");
        }
        else
            System.out.println("Нет department с id="+outgoingDocuments.get().getId());
    }
    @ShellMethod()
    public void deleteallout() {
        outgoingDocumentRepositoryImpl.deleteAll();
    }
    @ShellMethod()
    public void deletebyidout(String id) {
        System.out.println(outgoingDocumentRepositoryImpl.deleteById(id));
    }
    @ShellMethod()
    public void exist() {
        BaseDocument baseDocument=new BaseDocument();
        baseDocument.setRegNumber(289l);
        baseDocumentRepositoryImpl.existByRegNumber(baseDocument.getRegNumber());
    }
}
