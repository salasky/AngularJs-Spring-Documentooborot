package com.example.testproject1.shell;


import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.documentservice.GenerateDocumentService;
import com.example.testproject1.service.documentservice.GenerateReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.sql.Timestamp;
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
        Integer countDocument = Integer.valueOf(a);
        LOGGER.info("Попытка сгенерировать документы");
        generateDocumentService.createAndSaveDocument(countDocument);
        LOGGER.info("Попытка сформировать отчет по документам");
        generateReportService.saveReportByAuthor();
    }
    @ShellMethod
    public void save(){
        IncomingDocument incomingDocument =new IncomingDocument();
        Random random=new Random();
        incomingDocument.setId(UUID.randomUUID());
        incomingDocument.setText("text");
        incomingDocument.setName("name");
        incomingDocument.setNumber(123l);
        incomingDocument.setRegNumber(random.nextLong());
        incomingDocument.setCreatingDate(new Timestamp(234));
        incomingDocument.setDateOfRegistration(new Timestamp(4354));
        incomingDocument.setAuthor(personCrudRepository.getAll().stream().findFirst().get());
        incomingDocument.setSender(personCrudRepository.getAll().stream().findFirst().get());
        incomingDocument.setDestination(personCrudRepository.getAll().stream().findFirst().get());

        System.out.println(incomingDocumentCrudRepository.create(incomingDocument));
    }
}
