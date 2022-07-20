package com.example.testproject1.shell;


import com.example.testproject1.service.documents.GenerateDocument;
import com.example.testproject1.service.documents.GenerateReportService;
import com.example.testproject1.service.documents.impl.GenerateDocumentImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;


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
    private GenerateDocument generateDocument;
    /**
     * Autowired бина класса {@link GenerateReportService}
     */
    private GenerateReportService generateReportService;
    @Autowired
    public TaskDocumentShell(GenerateDocument generateDocument, GenerateReportService generateReportService) {
        this.generateDocument = generateDocument;
        this.generateReportService = generateReportService;
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
        generateDocument.generateDocument(countDocument);
        LOGGER.info("Попытка сформировать отчет по документам");
        generateReportService.genereteReport();
    }
}
