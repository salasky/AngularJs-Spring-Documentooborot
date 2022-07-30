package com.example.testproject1.shell;



import com.example.testproject1.dao.baseDocument.BaseDocumentRepository;
import com.example.testproject1.dao.department.DepartmentRepository;
import com.example.testproject1.dao.jobTittle.JobTittleRepository;
import com.example.testproject1.dao.organization.OrganizationRepository;
import com.example.testproject1.dao.person.PersonRepository;
import com.example.testproject1.dao.taskDocument.TaskDocumentRepository;
import com.example.testproject1.model.documents.BaseDocument;
import com.example.testproject1.model.documents.TaskDocument;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.documentService.DocumentStorageService;
import com.example.testproject1.service.documentService.GenerateDocumentService;
import com.example.testproject1.service.documentService.GenerateReportService;
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
    /**
     * Autowired бина класса {@link DocumentStorageService}
     */
    @Autowired
    private DocumentStorageService documentStorageService;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private JobTittleRepository jobTittleRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BaseDocumentRepository baseDocumentRepository;

    @Autowired
    TaskDocumentRepository taskDocumentRepository;
    /**
     * Shell метод генерации документов и создания отчетов по ним
     *
     * @param a Генерируем заданное количество документов
     */
    @ShellMethod("Cmd: generate --a  (Int DocumentCount (default = 100)")
    public void generate(@ShellOption(defaultValue = "100") int a) {
        Integer countDocument = Integer.valueOf(a);
        LOGGER.info("Попытка сгенерировать документы");
        generateDocumentService.generateDocument(countDocument);
        LOGGER.info("Попытка сформировать отчет по документам");
        generateReportService.generateReport();
        documentStorageService.getAll().clear();
    }
    @ShellMethod()
    public void createorg() {
        Organization organization=new Organization();
        organization.setId(UUID.randomUUID());
        organization.setFullName("organizationName");
        organization.setShortName("organizationShortName");
        organization.setSupervisor("organizationSupervisor");
        organization.setContactNumber("organizationContactNumber");
        organizationRepository.create(organization);
        System.out.println(organization +" создан");
    }
    @ShellMethod()
    public void updateorg() {
        Optional<Organization> organizations=organizationRepository.getAll().stream().findFirst();
        if(organizations.isPresent()){
            Organization organization=organizations.get();
            organization.setFullName("organizationNameUpdate");
            organization.setShortName("organizationShortNameU");
            organization.setSupervisor("organizationSupervisorU");
            organization.setContactNumber("organizationContactNumberU");
            organizationRepository.update(organization);
            System.out.println(organization +" обнавлен");
        }
        else
        System.out.println("Нет организации с id="+organizations.get().getId());
    }
    @ShellMethod()
    public void getallorg() {
        organizationRepository.getAll().stream().forEach(System.out::println);
    }
    @ShellMethod()
    public void getbyidorg(String id) {
        Optional<Organization> organization=organizationRepository.getById(id);
        if(organization.isPresent()){
            System.out.println(organization.get());
        }
        else System.out.println("не найдено");
    }
    @ShellMethod()
    public void deleteallorg() {
        organizationRepository.deleteAll();
    }
    @ShellMethod()
    public void deletebyidorg(String id) {
        System.out.println(organizationRepository.deleteById(id));
    }
    @ShellMethod()
    public void createdep() {
        Department department=new Department();
        department.setId(UUID.randomUUID());
        department.setFullName("departmentName");
        department.setShortName("departmentShortName");
        department.setSupervisor("departmentSupervisor");
        department.setContactNumber("departmentContactNumber");
        department.setOrganization(organizationRepository.getAll().get(0));
        departmentRepository.create(department);
        System.out.println(department +" создан");
    }

    @ShellMethod()
    public void updatedep() {
        Optional<Department> departments=departmentRepository.getAll().stream().findFirst();
        if(departments.isPresent()){
            Department department=departments.get();
            department.setFullName("departmentNameUpdate2");
            department.setShortName("departmentShortNameU");
            department.setSupervisor("departmentSupervisorU");
            department.setContactNumber("departmentContactNumberU");
            department.setOrganization(organizationRepository.getAll().stream().findFirst().get());
            departmentRepository.update(department);
            System.out.println(department +" обнавлен");
        }
        else
            System.out.println("Нет department с id="+departments.get().getId());
    }
    @ShellMethod()
    public void getalldep() {
        departmentRepository.getAll().stream().forEach(System.out::println);
    }

    @ShellMethod()
    public void getbyiddep(String id) {
        System.out.println( departmentRepository.getById(id).get());
    }

    @ShellMethod()
    public void deletealldep() {
        departmentRepository.deleteAll();
    }

    @ShellMethod()
    public Integer deletebyiddep(String id) {
        return departmentRepository.deleteById(id);
    }

    @ShellMethod()
    public void createjob() {
        JobTittle jobTittle=new JobTittle();
        jobTittle.setUuid(UUID.randomUUID());
        jobTittle.setName("JobName");
        jobTittleRepository.create(jobTittle);
        System.out.println(jobTittle +" создан");
    }
    @ShellMethod()
    public void updatejob() {
        Optional<JobTittle> jobTittles=jobTittleRepository.getAll().stream().findFirst();
        if(jobTittles.isPresent()){
            JobTittle jobTittle=jobTittles.get();
            jobTittle.setName("JobNameU");
            jobTittleRepository.update(jobTittle);
            System.out.println(jobTittle +" обнавлен");
        }
        else
            System.out.println("Нет department с id="+jobTittles.get().getUuid());
    }
    @ShellMethod()
    public void getalljob() {
        jobTittleRepository.getAll().stream().forEach(System.out::println);
    }
    @ShellMethod()
    public void getbyidjob(String id) {
        Optional<JobTittle> jobTittle=jobTittleRepository.getById(id);
        if(jobTittle.isPresent()){
            System.out.println(jobTittle.get());
        }
        else System.out.println("не найдено");
    }
    @ShellMethod()
    public void deletealljob() {
        jobTittleRepository.deleteAll();
    }
    @ShellMethod()
    public void deletebyidjob(String id) {
        System.out.println(jobTittleRepository.deleteById(id));
    }

    @ShellMethod()
    public void createpers() {
        Person person=new Person();
        person.setId(UUID.randomUUID());
        person.setFirstName("personsetFirstName");
        person.setSecondName("personsetSecondName");
        person.setLastName("personsetLastName");
        person.setPhoto("personsetPhoto");
        JobTittle jobTittle=jobTittleRepository.getAll().stream().findFirst().get();
        person.setJobTittle(jobTittle);
        Department department=departmentRepository.getAll().stream().findFirst().get();
        person.setDepartment(department);
        person.setPhoneNumber("personsetPhoneNumber22");
        person.setBirthDay(new Date(System.currentTimeMillis()));
        personRepository.create(person);
        System.out.println(person +" создан");
    }

    @ShellMethod()
    public void getallper() {
       personRepository.getAll().stream().forEach(System.out::println);
    }

    @ShellMethod()
    public void getbyidper(String id) {
        Optional<Person> person=personRepository.getById(id);
        if(person.isPresent()){
            System.out.println(person.get());
        }
        else System.out.println("не найдено");
    }
    @ShellMethod()
    public void updatepers() {
        Optional<Person> persons=personRepository.getAll().stream().findFirst();
        if(persons.isPresent()){
            Person person=persons.get();
            person.setFirstName("FirstUpdateName");
            personRepository.update(person);
            System.out.println(person +" обнавлен");
        }
        else
            System.out.println("Нет person с id="+persons.get().getId().toString());
    }
    @ShellMethod()
    public void deleteallpers() {
        personRepository.deleteAll();
    }
    @ShellMethod()
    public void deletebyidpers(String id) {
        System.out.println(personRepository.deleteById(id));
    }

    @ShellMethod()
    public void createbdoc() {
        BaseDocument baseDocument=new BaseDocument();
        baseDocument.setId(UUID.randomUUID());
        baseDocument.setName("BaseDocName");
        baseDocument.setText("BaseDocText");
        baseDocument.setRegNumber(ThreadLocalRandom.current().nextLong(1000));
        long offset = Timestamp.valueOf("2015-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2022-08-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
        baseDocument.setCreatingDate(rand);
        Person person=personRepository.getAll().stream().findFirst().get();
        baseDocument.setAuthor(person);
        baseDocumentRepository.create(baseDocument);
        System.out.println(baseDocument +" создан");
    }
    @ShellMethod()
    public void getallbdoc() {
        baseDocumentRepository.getAll().stream().forEach(System.out::println);
    }

    @ShellMethod()
    public void getbyidbdoc(String id) {
        Optional<BaseDocument> baseDocument=baseDocumentRepository.getById(id);
        if(baseDocument.isPresent()){
            System.out.println(baseDocument.get());
        }
        else System.out.println("не найдено");
    }
    @ShellMethod()
    public void updatebdoc() {
        Optional<BaseDocument> baseDocuments=baseDocumentRepository.getAll().stream().findFirst();
        if(baseDocuments.isPresent()){
            BaseDocument baseDocument=baseDocuments.get();
            baseDocument.setName("BaseNameU");
            baseDocumentRepository.update(baseDocument);
            System.out.println(baseDocument +" обнавлен");
        }
        else
            System.out.println("Нет department с id="+baseDocuments.get().getId());
    }

    @ShellMethod()
    public void deleteallbdoc() {
        baseDocumentRepository.deleteAll();
    }
    @ShellMethod()
    public void deletebyidbdoc(String id) {
        System.out.println(baseDocumentRepository.deleteById(id));
    }
    @ShellMethod()
    public void createtask() {
        TaskDocument taskDocument=new TaskDocument();
        taskDocument.setId(baseDocumentRepository.getAll().stream().findFirst().get().getId());
        long offset = Timestamp.valueOf("2015-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2022-08-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
        taskDocument.setOutDate(rand);
        taskDocument.setExecPeriod("3 дня");
        Person personResponse=personRepository.getAll().stream().findFirst().get();
        taskDocument.setResponsible(personResponse);
        taskDocument.setControlPerson(personResponse);
        taskDocument.setSignOfControl(true);

        taskDocumentRepository.create(taskDocument);
        System.out.println(taskDocument +" создан");
    }

    @ShellMethod()
    public void getalltask() {
        taskDocumentRepository.getAll().stream().forEach(System.out::println);
    }
}
