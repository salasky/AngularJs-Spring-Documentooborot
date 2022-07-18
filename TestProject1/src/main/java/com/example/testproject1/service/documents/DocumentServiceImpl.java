package com.example.testproject1.service.documents;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.model.OutgoingDocument;
import com.example.testproject1.model.TaskDocument;
import com.example.testproject1.service.docfactory.IncomingDocumentFactory;
import com.example.testproject1.service.docfactory.OutgoingDocumentFactory;
import com.example.testproject1.service.docfactory.TaskDocumentFactory;
import com.example.testproject1.storage.DocumentHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Класс для работы с документами.Генерация документов и их сохранение.Генерация отчетов.
 *
 * @author smigranov
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentServiceImpl.class);
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
     * Объект класса {@link DocumentHolder}
     */
    private DocumentHolder documentHolder;


    @Autowired
    public DocumentServiceImpl(TaskDocumentFactory taskFactory
            , IncomingDocumentFactory incomingDocFactory
            , OutgoingDocumentFactory outgoingFactory
            , DocumentHolder documentHolder) {
        this.taskFactory = taskFactory;
        this.incomingDocFactory = incomingDocFactory;
        this.outgoingFactory = outgoingFactory;
        this.documentHolder = documentHolder;
    }



    /**
     * {@inheritDoc}
     * Метод сохранения объекта класса {@link BaseDocument}
     * @param baseDocument передаем объект наследник от {@link BaseDocument} для сохранения в {@link DocumentHolder#documentList}
     * @throws DocumentExistsException при существовании в базе указанного идентификатора или рег.номера
     */
    @Override
    public void documentAdd(BaseDocument baseDocument) throws DocumentExistsException {
        var documentList = documentHolder.getDocumentList();
        for (BaseDocument bd : documentList
        ) {
            if (bd.getId() == baseDocument.getId() || bd.getDocumentRegNumber() == baseDocument.getDocumentRegNumber()) {

                throw new DocumentExistsException(bd.getDocumentRegNumber(), "Document number " + bd.getDocumentRegNumber() + " exist");
            }
        }
        DocumentHolder.documentList.add(baseDocument);
    }



    /**
     * {@inheritDoc}
     * Метод генерирует заданное количество документов разных типов и печатает в консоль
     *
     * @param task     Количество генерируемых поручений
     * @param incoming Количество генерируемых входящих документов
     * @param outgoing Количество генерируемых исходящих документов
     */
    @Override
    public void generateDocument(String task, String incoming, String outgoing) {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("---------------------Сгенерированные документы---------------------");
        //Генерация поручений
        generateTaskDocument(Integer.valueOf(task));
        //Генерация входящих документов
        generateIncomingDocument(Integer.valueOf(incoming));
        //Генерация исходящих сообщений
        generateOutgoingDocument(Integer.valueOf(outgoing));
    }

    //Генерация поручений
    public void generateTaskDocument(int task){
        for (int i = 0; i < Integer.valueOf(task); i++) {
            BaseDocument taskDoc = taskFactory.createDocument();
            if (taskDoc != null) {
                try {
                    documentAdd(taskDoc);
                    LOGGER.info(String.valueOf(taskDoc));
                } catch (DocumentExistsException e) {
                    LOGGER.error(e.getMessage());
                }

            }
        }
    }
    //Генерация входящих документов
    public void generateIncomingDocument(int incoming){
        for (int i = 0; i < Integer.valueOf(incoming); i++) {
            BaseDocument incomingDoc = incomingDocFactory.createDocument();
            if (incomingDoc != null) {
                try {
                    documentAdd(incomingDoc);
                    LOGGER.info(String.valueOf(incomingDoc));
                } catch (DocumentExistsException e) {
                    LOGGER.error(e.getMessage());
                }

            }
        }
    }
    //Генерация исходящих сообщений
    public void generateOutgoingDocument(int outgoing){
        for (int i = 0; i < Integer.valueOf(outgoing); i++) {
            BaseDocument outgoingDoc = outgoingFactory.createDocument();
            if (outgoingDoc != null) {
                try {
                    documentAdd(outgoingDoc);
                    LOGGER.info(String.valueOf(outgoingDoc));
                } catch (DocumentExistsException e) {
                    /* throw new RuntimeException(e);*/
                    LOGGER.error(e.getMessage());
                }

            }
        }
    }

    /**
     * {@inheritDoc}
     * Метод генерирует отчеты по сгенерированным документам
     */
    @Override
    public void genereteReport() {
        Map<String, List<String>> totalMap = new TreeMap<>();
        for (
                BaseDocument basedoc : DocumentHolder.documentList
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
        System.out.println("------------------------------------------------");
        System.out.println("---------------------Отчет---------------------");
        for (Map.Entry<String, List<String>> entry : totalMap.entrySet()) {
            Object[] taskArgs = {entry.getKey(), entry.getValue()};
            MessageFormat form = new MessageFormat("\n Автор документа {0}\n{1}");
            LOGGER.info(form.format(taskArgs));
        }
    }
}
