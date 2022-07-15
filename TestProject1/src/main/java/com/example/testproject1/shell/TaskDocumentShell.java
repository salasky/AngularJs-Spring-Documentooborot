package com.example.testproject1.shell;


import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.model.OutgoingDocument;
import com.example.testproject1.model.TaskDocument;
import com.example.testproject1.service.DocSave.DocSave;
import com.example.testproject1.service.docfactory.IncomingDocumentFactory;
import com.example.testproject1.service.docfactory.OutgoingDocumentFactory;
import com.example.testproject1.service.docfactory.TaskDocumentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Класс для работы с терминалом shell и запуска генерации документов и отчетов
 * @author smigranov
 * @version 1.0
 */
@ShellComponent
public class TaskDocumentShell {
    Logger logger = LoggerFactory.getLogger(TaskDocumentShell.class);
    /**
     * Объект класса {@link TaskDocumentFactory}
     */
    private TaskDocumentFactory taskFactory;
    /**
     * Объект класса {@link IncomingDocumentFactory}
     */
    private IncomingDocumentFactory incomingDocFactory;
    /**
     * Объект класса {@link OutgoingDocumentFactory}
     */
    private OutgoingDocumentFactory outgoingFactory;
    /**
     * Объект класса {@link DocSave}
     */
    private DocSave docSave;

    @Autowired
    public TaskDocumentShell(TaskDocumentFactory taskFactory, IncomingDocumentFactory incomingDocFactory, OutgoingDocumentFactory outgoingFactory, DocSave docSave) {
        this.taskFactory = taskFactory;
        this.incomingDocFactory = incomingDocFactory;
        this.outgoingFactory = outgoingFactory;
        this.docSave = docSave;
    }

    /**
     * @param task     Передаем количество генерируемых поручений
     * @param incoming Передаем количество генерируемых входящих документов
     * @param outgoing Передаем количество генерируемых исходящих
     */
    @ShellMethod(value = "generate Param(Int taskDocCount(default=10),Int incomingDocCount(default=10),Int outgoingDocCount(default=10)", key = "generate")
    public void generate(@ShellOption(defaultValue = "10") String task, @ShellOption(defaultValue = "10") String incoming, @ShellOption(defaultValue = "10") String outgoing) {


        System.out.println("----------------------------------------------------------------------");
        System.out.println("---------------------Сгенерированные документы---------------------");

        //Генерация поручений
        for (int i = 0; i < Integer.valueOf(task); i++) {
            BaseDocument taskDoc = taskFactory.createDocument();
            if (taskDoc != null) {
                docSave.docSave(taskDoc);
                logger.info(String.valueOf(taskDoc));
            }
        }
        //Генерация входящих сообщений
        for (int i = 0; i < Integer.valueOf(incoming); i++) {
            BaseDocument incomingDoc = incomingDocFactory.createDocument();
            if (incomingDoc != null) {
                docSave.docSave(incomingDoc);
                logger.info(String.valueOf(incomingDoc));
            }
        }
        //Генерация исходящих сообщений
        for (int i = 0; i < Integer.valueOf(outgoing); i++) {
            BaseDocument outgoingDoc = outgoingFactory.createDocument();
            if (outgoingDoc != null) {
                docSave.docSave(outgoingDoc);
                logger.info(String.valueOf(outgoingDoc));
            }

        }
        System.out.println("------------------------------------------------");
        System.out.println("---------------------Отчет---------------------");

        Map<String, List<String>> totalMap = new TreeMap<>();

        for (BaseDocument basedoc : DocSave.documentList
        ) {
            //Если не существует запись для данного автора
            if (!totalMap.containsKey(basedoc.getDocumentAuthor())) {

                List<String> list = new ArrayList<>();
                //Если это поручение
                if (basedoc instanceof TaskDocument) {
                    list.add("Поручение " + basedoc.getId() + " от " + basedoc.getDocumentData() + ". " + basedoc.getDocumentName() + "\n");
                }
                //Если документ входящий
                if (basedoc instanceof IncomingDocument) {
                    list.add("Входящий " + basedoc.getId() + " от " + basedoc.getDocumentData() + ". " + basedoc.getDocumentName() + "\n");
                }
                //Если документ исходящий
                if (basedoc instanceof OutgoingDocument) {
                    list.add("Исходящий   " + basedoc.getId() + " от " + basedoc.getDocumentData() + ". " + basedoc.getDocumentName() + "\n");
                }
                totalMap.put(basedoc.getDocumentAuthor(), list);
            } else {
                //Ecли существуют документы данного автора
                var oldlist = totalMap.get(basedoc.getDocumentAuthor());

                if (basedoc instanceof TaskDocument) {
                    oldlist.add("Поручение " + basedoc.getId() + " от " + basedoc.getDocumentData() + ". " + basedoc.getDocumentName() + "\n");
                }

                if (basedoc instanceof IncomingDocument) {
                    oldlist.add("Входящий " + basedoc.getId() + " от " + basedoc.getDocumentData() + ". " + basedoc.getDocumentName() + "\n");
                }

                if (basedoc instanceof OutgoingDocument) {
                    oldlist.add("Исходящий " + basedoc.getId() + " от " + basedoc.getDocumentData() + ". " + basedoc.getDocumentName() + "\n");
                }
                Collections.sort(oldlist);
                totalMap.put(basedoc.getDocumentAuthor(), oldlist);
            }
        }

        for (Map.Entry<String, List<String>> entry : totalMap.entrySet()) {
            logger.info(entry.getKey() + ":\n" + entry.getValue());
        }


    }


}
