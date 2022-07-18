package com.example.testproject1.shell;


import com.example.testproject1.model.Enum.DocumentDeliveryType;
import com.example.testproject1.service.DocSave.DocumentServiceImpl;
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
    private static final Logger logger = LoggerFactory.getLogger(TaskDocumentShell.class);
    /**
     * Autowired бина класса {@link DocumentServiceImpl}
     */
    @Autowired
    private DocumentServiceImpl documentServiceImpl;

    /**
     * @param task     Передаем количество генерируемых поручений
     * @param incoming Передаем количество генерируемых входящих документов
     * @param outgoing Передаем количество генерируемых исходящих
     */
    @ShellMethod(value = "generate Param(Int taskDocCount (default = 10), Int incomingDocCount(default = 10), Int outgoingDocCount(default = 10)", key = "generate")
    public void generate(@ShellOption(defaultValue = "10") String task, @ShellOption(defaultValue = "10") String incoming, @ShellOption(defaultValue = "10") String outgoing) {
        logger.info("Попытка сгенерировать документы");
        documentServiceImpl.generateDocument(task, incoming, outgoing);
        logger.info("Попытка сформировать отчет по документам");
        documentServiceImpl.genereteReport();
    }
}
