package com.example.testproject1.service.documents.impl;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.ReportForJson;
import com.example.testproject1.model.person.Person;
import com.example.testproject1.service.documents.GenerateReportService;
import com.example.testproject1.storage.DocumentHolder;
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
    private static String SHORTPATH="./src/main/resources/";
    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateReportServiceImpl.class);
    /**
     * Объект для библиотеки Fasterxml Jackson
     * с настройками даты
     */
    ObjectMapper objectMapper = new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(SerializationFeature.INDENT_OUTPUT);

    /**
     * Autowired бина класса {@link DocumentHolder}
     */
    @Autowired
    private DocumentHolder documentHolder;
    /**
     * {@inheritDoc}
     */
    @Override
    public void generateReport() {
        Map<Person, List<BaseDocument>> totalMap = new HashMap<>();
        for (BaseDocument baseDocument : documentHolder.getAll()) {
            //Если не существует запись для данного автора
            if (!totalMap.containsKey(baseDocument.getAuthor())) {
                List<BaseDocument> list = new ArrayList<>();
                list.add(baseDocument);
                totalMap.put(baseDocument.getAuthor(), list);
            } else {
                //Ecли существуют документы данного автора
                List<BaseDocument> oldList = totalMap.get(baseDocument.getAuthor());
                oldList.add(baseDocument);
                Collections.sort(oldList);
                totalMap.put(baseDocument.getAuthor(), oldList);
            }
        }

        for (Map.Entry<Person, List<BaseDocument>> entry : totalMap.entrySet()) {
            writeReportInFile(entry);
        }
    }
    public void writeReportInFile(Map.Entry<Person, List<BaseDocument>> entry){
        ReportForJson reportForJson=ReportForJson.newBuilder()
                .setPerson(entry.getKey())
                .setDocumentList(entry.getValue())
                .build();
        String secondName=entry.getKey().getSecondName();
        //Полный путь к файлу
        String filepathFull=SHORTPATH+secondName+".json";
        LOGGER.info("Создаем файл "+secondName+".json");
        try {
            objectMapper.writeValue(new File(filepathFull),reportForJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
