package com.example.testproject1.service.documents.impl;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.model.OutgoingDocument;
import com.example.testproject1.model.TaskDocument;
import com.example.testproject1.service.documents.GenerateReportService;
import com.example.testproject1.storage.DocumentHolderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Класс реализующий интерфейс {@link GenerateReportService}
 *
 * @author smigranov
 */
@Service
public class GenerateReportServiceImpl implements GenerateReportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateReportServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void genereteReport() {
        Map<String, List<String>> totalMap = new TreeMap<>();
        for (
                BaseDocument basedoc : DocumentHolderImpl.documentList
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
