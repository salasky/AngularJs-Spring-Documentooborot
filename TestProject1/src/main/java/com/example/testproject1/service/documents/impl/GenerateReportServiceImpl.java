package com.example.testproject1.service.documents.impl;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.ReportForJson;
import com.example.testproject1.model.person.Person;
import com.example.testproject1.service.documents.GenerateReportService;
import com.example.testproject1.service.visitorPatternRelase.DocumentInspector;
import com.example.testproject1.storage.Impl.DocumentHolderImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс реализующий интерфейс {@link GenerateReportService}
 *
 * @author smigranov
 */
@Service
public class GenerateReportServiceImpl implements GenerateReportService {
    /**
     * Путь к папке с json файлами
     */
    private static String SHORTPATH="C:\\Users\\smigranov\\Desktop\\TestProject\\TestProject\\testrepo\\TestProject1\\src\\main\\resources\\";
    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateReportServiceImpl.class);

    /**
     * Вспомогательный объект класса DocumentInspector для реализации паттерна посетитель
     */
    @Autowired
    private DocumentInspector documentInspector;

    @Override
    public void generateReport() {
        Map<Person, List<BaseDocument>> totalMap = new HashMap<>();
        for (BaseDocument baseDocument : DocumentHolderImpl.documentList
        ) {
            //Если не существует запись для данного автора
            if (!totalMap.containsKey(baseDocument.getDocumentAuthor())) {
                List<BaseDocument> list = new ArrayList<>();
                list.add(baseDocument);
                totalMap.put(baseDocument.getDocumentAuthor(), list);
            } else {
                //Ecли существуют документы данного автора
                List<BaseDocument> oldlist = totalMap.get(baseDocument.getDocumentAuthor());
                oldlist.add(baseDocument);
                Collections.sort(oldlist);
                totalMap.put(baseDocument.getDocumentAuthor(), oldlist);
            }
        }

        for (Map.Entry<Person, List<BaseDocument>> entry : totalMap.entrySet()) {
            ReportForJson reportForJson=ReportForJson.newBuilder()
                                        .setPerson(entry.getKey())
                                        .setDocumentList(entry.getValue())
                                        .build();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String secondName=entry.getKey().getSecondName();
            //Полный путь к файлу
            String filepathFull=SHORTPATH+secondName+".json";
            LOGGER.info("Создаем файл "+secondName+".json");
            try {
                objectMapper.writeValue(new File(filepathFull),reportForJson);
                //Для удобства так же выводим в консоль
                String json=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(reportForJson);
                System.out.println(json);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
