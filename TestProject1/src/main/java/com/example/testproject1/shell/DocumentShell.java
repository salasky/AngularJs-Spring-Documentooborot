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

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;
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
    public void get() {
        Organization organization = Organization.newBuilder()
                .setId(UUID.randomUUID())
                .setFullName("FullName")
                .setShortName("organizationShortName")
                .setSupervisor("orgSupervisor")
                .setContactNumber(List.of("897643567895")).build();
        UUID orgUid = organization.getId();
        Organization organization2 = Organization.newBuilder()
                .setId(organization.getId())
                .setFullName("FullName")
                .setShortName("organizationShortName")
                .setSupervisor("orgSupervisor")
                .setContactNumber(List.of("897643567895")).build();

        List<Organization> organizationList = new ArrayList<>();
        organizationList.add(organization);
        organizationList.add(organization2);
        organizationList.stream().forEach(System.out::println);

        try {
            organizationCrudRepository.saveAll(organizationList);
        } catch (BatchUpdateException e) {
            throw new RuntimeException(e);
        }
    }
}
