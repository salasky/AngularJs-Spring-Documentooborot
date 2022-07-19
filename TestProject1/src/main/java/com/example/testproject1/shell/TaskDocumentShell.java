package com.example.testproject1.shell;


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
    private GenerateDocumentImpl documentServiceImpl;
    /**
     * Autowired бина класса {@link GenerateReportService}
     */
    private GenerateReportService generateReportService;

    @Autowired
    public TaskDocumentShell(GenerateDocumentImpl documentServiceImpl, GenerateReportService generateReportService) {
        this.documentServiceImpl = documentServiceImpl;
        this.generateReportService = generateReportService;
    }

    /**
     * @param task     Передаем количество генерируемых документов
     */
    @ShellMethod(value = "generate Param(Int DocгьутеCount (default = 50)", key = "generate")
    public void generate(@ShellOption(defaultValue = "50") String task) {
        LOGGER.info("Попытка сгенерировать документы");
        documentServiceImpl.generateDocument(task);
        LOGGER.info("Попытка сформировать отчет по документам");
        generateReportService.genereteReport();
    }
}
