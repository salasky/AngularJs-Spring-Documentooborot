package com.example.testproject1.service.documents.impl;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.service.documents.GenerateReportService;
import com.example.testproject1.service.visitorPatternRelase.DocumentInspector;
import com.example.testproject1.storage.DocumentHolderImpl;
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
 * Класс реализующий интерфейс {@link GenerateReportService}
 *
 * @author smigranov
 */
@Service
public class GenerateReportServiceImpl implements GenerateReportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateReportServiceImpl.class);

    /**
     * Вспомогательный объект класса DocumentInspector для реализации паттерна посетитель
     */
    @Autowired
    private DocumentInspector documentInspector;

    @Override
    public void generateReport() {
        Map<String, List<String>> totalMap = new TreeMap<>();
        for (BaseDocument baseDocument : DocumentHolderImpl.documentList
        ) {
            //Если не существует запись для данного автора
            if (!totalMap.containsKey(baseDocument.getDocumentAuthor())) {
                List<String> list = new ArrayList<>();
                list.add(baseDocument.accept(documentInspector));
                totalMap.put(baseDocument.getDocumentAuthor(), list);
            } else {
                //Ecли существуют документы данного автора
                List<String> oldlist = totalMap.get(baseDocument.getDocumentAuthor());
                oldlist.add(baseDocument.accept(documentInspector));
                Collections.sort(oldlist);
                totalMap.put(baseDocument.getDocumentAuthor(), oldlist);
            }
        }
        LOGGER.info("\n         ------------------------Отчет------------------------");
        for (Map.Entry<String, List<String>> entry : totalMap.entrySet()) {
            LOGGER.info(MessageFormat.format("\n Автор документа {0}\n{1}", entry.getKey(), entry.getValue()));
        }
    }
}
