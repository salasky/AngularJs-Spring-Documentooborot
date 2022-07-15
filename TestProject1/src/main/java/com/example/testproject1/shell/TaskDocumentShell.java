package com.example.testproject1.shell;


import com.example.testproject1.service.DocSave.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;



/**
 * Класс для работы с терминалом shell и запуска генерации документов и отчетов
 * @author smigranov
 * @version 1.0
 */
@ShellComponent
public class TaskDocumentShell {
    /**
     * Autowired бина класса {@link DocumentService}
     */
    @Autowired
    private DocumentService documentService;

    /**
     * @param task     Передаем количество генерируемых поручений
     * @param incoming Передаем количество генерируемых входящих документов
     * @param outgoing Передаем количество генерируемых исходящих
     */
    @ShellMethod(value = "generate Param(Int taskDocCount(default=10),Int incomingDocCount(default=10),Int outgoingDocCount(default=10)", key = "generate")
    public void generate(@ShellOption(defaultValue = "10") String task, @ShellOption(defaultValue = "10") String incoming, @ShellOption(defaultValue = "10") String outgoing) {
        documentService.generateDocument(task,incoming,outgoing);
        documentService.genereteReport();
    }


}
