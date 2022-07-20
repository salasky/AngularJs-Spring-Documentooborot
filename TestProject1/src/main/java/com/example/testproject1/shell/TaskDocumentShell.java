package com.example.testproject1.shell;


import com.example.testproject1.jaxb.DepartmentJaxbReader;
import com.example.testproject1.jaxb.OrganizationJaxbReader;
import com.example.testproject1.jaxb.PersonJaxbReader;
import com.example.testproject1.model.department.Department;
import com.example.testproject1.model.department.Departments;
import com.example.testproject1.model.person.JobTittle;
import com.example.testproject1.model.organization.Organization;
import com.example.testproject1.model.organization.Organizations;
import com.example.testproject1.model.person.Person;
import com.example.testproject1.model.person.Persons;
import com.example.testproject1.service.documents.GenerateReportService;
import com.example.testproject1.service.documents.impl.GenerateDocumentImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;


/**
 * Класс для работы с терминалом shell и запуска генерации документов и отчетов
 *
 * @author smigranov
 */
@ShellComponent
public class TaskDocumentShell {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskDocumentShell.class);
    /**
     * Autowired бина класса {@link GenerateDocumentImpl}
     */
    private GenerateDocumentImpl documentServiceImpl;
    /**
     * Autowired бина класса {@link GenerateReportService}
     */
    private GenerateReportService generateReportService;

    private PersonJaxbReader personJaxbReader;
    private OrganizationJaxbReader organizationJaxbReader;

    private DepartmentJaxbReader departmentJaxbReader;

    @Autowired
    public TaskDocumentShell(GenerateDocumentImpl documentServiceImpl, GenerateReportService generateReportService, PersonJaxbReader personJaxbReader, OrganizationJaxbReader organizationJaxbReader, DepartmentJaxbReader departmentJaxbReader) {
        this.documentServiceImpl = documentServiceImpl;
        this.generateReportService = generateReportService;
        this.personJaxbReader = personJaxbReader;
        this.organizationJaxbReader = organizationJaxbReader;
        this.departmentJaxbReader = departmentJaxbReader;
    }

    /**
     *Shell метод генерации документов и создания отчетов по ним
     *
     * @param a Генерируем заданное количество документов
     */
    @ShellMethod("Cmd: generate --a  (Int DocumentCount (default = 500")
    public void generate(@ShellOption(defaultValue="500") int a) {
        Integer countDocument = Integer.valueOf(a);
        LOGGER.info("Попытка сгенерировать документы");
        documentServiceImpl.generateDocument(countDocument);
        LOGGER.info("Попытка сформировать отчет по документам");
        generateReportService.genereteReport();
    }
    @ShellMethod("JAXB")
    public void jaxp(){
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Persons.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            Person person = new Person();
            person.setId(UUID.randomUUID());
            person.setFirstName("Ivan");
            person.setSecondName("Ivanov");
            person.setLastName("Ivanovich");
            person.setJobTittle(new JobTittle(UUID.randomUUID(), "Java middle"));
            Random rnd = new Random();
            Long ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
            person.setBirthDay(new Date(ms));
            person.setPhoto("http//:dssf.png");
            person.setPhoneNumber("687546346");

            Person person1 = new Person();
            person1.setId(UUID.randomUUID());
            person1.setFirstName("Salavat");
            person1.setSecondName("Migranov");
            person1.setLastName("Vagizovich");
            person1.setJobTittle(new JobTittle(UUID.randomUUID(), "Java junior"));
            Long ms1 =-946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
            person1.setBirthDay(new Date(ms1));
            person1.setPhoto("http//:migsal.png");
            person1.setPhoneNumber("897546346");

            Person person2 = new Person();
            person2.setId(UUID.randomUUID());
            person2.setFirstName("Kondratyuk");
            person2.setSecondName("Aleksandr");
            person2.setLastName("Petrovich");
            person2.setJobTittle(new JobTittle(UUID.randomUUID(), "QA"));
            Long ms2 = -946771200000L  + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
            person2.setBirthDay(new Date(ms2));
            person2.setPhoto("http//:dsd.png");
            person2.setPhoneNumber("86436346");

            Person person3 = new Person();
            person3.setId(UUID.randomUUID());
            person3.setFirstName("Yushko");
            person3.setSecondName("Oleg");
            person3.setLastName("Yurevich");
            person3.setJobTittle(new JobTittle(UUID.randomUUID(), "QA junior"));
            Long ms3 = -946771200000L  + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
            person3.setBirthDay(new Date(ms3));
            person3.setPhoto("http//:sdsf.png");
            person3.setPhoneNumber("45345323");

            Person person4 = new Person();
            person4.setId(UUID.randomUUID());
            person4.setFirstName("Petrosyan");
            person4.setSecondName("Sergey");
            person4.setLastName("Uralovich");
            person4.setJobTittle(new JobTittle(UUID.randomUUID(), "Alfresco junior"));
            Long ms4 = -946771200000L  + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
            person4.setBirthDay(new Date(ms4));
            person4.setPhoto("http//:hgh.png");
            person4.setPhoneNumber("66787546346");

            Persons persons=new Persons();
            persons.add(person);
            persons.add(person1);
            persons.add(person2);
            persons.add(person3);
            persons.add(person4);

            mar.marshal(persons, new File("C:\\Users\\smigranov\\Desktop\\TestProject\\TestProject\\testrepo\\TestProject1\\src\\main\\resources\\persons.xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        List<Person> listPerson=personJaxbReader.getPerson();
        listPerson.stream().forEach(System.out::println);
    }
    @ShellMethod("JAXB")
    public void jaxo(){
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Organizations.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            Organization organization1=new Organization();
            organization1.setId(UUID.randomUUID());
            organization1.setOrganizationFullName("Nefteaftomatoika");
            organization1.setOrganizationShortName("Nft");
            organization1.setOrganizationSupervisor("Petrov Ivan");
            organization1.setOrganizationContactNumber("876543567");

            Organization organization2=new Organization();
            organization2.setId(UUID.randomUUID());
            organization2.setOrganizationFullName("Oil and gas systems");
            organization2.setOrganizationShortName("OGS");
            organization2.setOrganizationSupervisor("Pashkov Andrey");
            organization2.setOrganizationContactNumber("87650943567");


            Organization organization3=new Organization();
            organization3.setId(UUID.randomUUID());
            organization3.setOrganizationFullName("Business Logic");
            organization3.setOrganizationShortName("LB");
            organization3.setOrganizationSupervisor("Pyj Elena");
            organization3.setOrganizationContactNumber("89232736422");



            Organizations organizations=new Organizations();
            organizations.add(organization1);
            organizations.add(organization2);
            organizations.add(organization3);

            mar.marshal(organizations, new File("C:\\Users\\smigranov\\Desktop\\TestProject\\TestProject\\testrepo\\TestProject1\\src\\main\\resources\\organizations.xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        List<Organization> organizationList=organizationJaxbReader.getOrganization();
        organizationList.stream().forEach(System.out::println);
    }


    @ShellMethod("JAXB")
    public void jaxd(){
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Departments.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            Department department1=new Department();
            department1.setId(UUID.randomUUID());
            department1.setDepartmentFullName("Market-based");
            department1.setDepartmentShortName("Mk");
            department1.setDepartmentSupervisor("Tom Hanks");
            department1.setDepartmentContactNumber("89654456");

            Department department2=new Department();
            department2.setId(UUID.randomUUID());
            department2.setDepartmentFullName("Product-based");
            department2.setDepartmentShortName("Pd");
            department2.setDepartmentSupervisor("Tom Hanks");
            department2.setDepartmentContactNumber("89654456");

            Department department3=new Department();
            department3.setId(UUID.randomUUID());
            department3.setDepartmentFullName("Information Technology");
            department3.setDepartmentShortName("IT");
            department3.setDepartmentSupervisor("Tom Cruze");
            department3.setDepartmentContactNumber("8965445456");



            Departments departments=new Departments();
            departments.add(department1);
            departments.add(department2);
            departments.add(department3);


            mar.marshal(departments, new File("C:\\Users\\smigranov\\Desktop\\TestProject\\TestProject\\testrepo\\TestProject1\\src\\main\\resources\\departments.xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        List<Department> departmentList=departmentJaxbReader.getDepartment();
        departmentList.stream().forEach(System.out::println);
    }
}
