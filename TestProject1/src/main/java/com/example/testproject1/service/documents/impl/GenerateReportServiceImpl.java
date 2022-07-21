package com.example.testproject1.service.documents.impl;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.service.documents.GenerateReportService;
import com.example.testproject1.service.visitorPatternRelase.DocumentInspector;
import com.example.testproject1.storage.Impl.DocumentHolderImpl;
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
    public void genereteReport() {
        Map<String, List<String>> totalMap = new TreeMap<>();
        for (BaseDocument basedoc : DocumentHolderImpl.documentList
        ) {
            //Если не существует запись для данного автора
            if (!totalMap.containsKey(basedoc.getDocumentAuthor())) {
                List<String> list = new ArrayList<>();

                list.add(MessageFormat.format("{0} {1} от {2}. {3} \n"
                        , basedoc.accept(documentInspector), basedoc.getId(), basedoc.getDocumentData(),basedoc.getDocumentName()));
                totalMap.put(basedoc.getDocumentAuthor(), list);
            } else {
                //Ecли существуют документы данного автора
                List<String> oldlist = totalMap.get(basedoc.getDocumentAuthor());
                oldlist.add(MessageFormat.format("{0} {1} от {2}. {3} \n"
                        , basedoc.accept(documentInspector), basedoc.getId(), basedoc.getDocumentData(),basedoc.getDocumentName()));
                Collections.sort(oldlist);
                totalMap.put(basedoc.getDocumentAuthor(), oldlist);
            }
        }
        LOGGER.info("\n         ------------------------Отчет------------------------");
        for (Map.Entry<String, List<String>> entry : totalMap.entrySet()) {
            LOGGER.info(MessageFormat.format("\n Автор документа {0}\n{1}", entry.getKey(), entry.getValue()));
        }
    }
}
