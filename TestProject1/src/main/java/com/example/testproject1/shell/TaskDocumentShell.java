package com.example.testproject1.shell;



import com.example.testproject1.dao.department.DepartmentRepository;
import com.example.testproject1.dao.organization.OrganizationRepository;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.documentService.DocumentStorageService;
import com.example.testproject1.service.documentService.GenerateDocumentService;
import com.example.testproject1.service.documentService.GenerateReportService;
import liquibase.pro.packaged.O;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Optional;
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

}
