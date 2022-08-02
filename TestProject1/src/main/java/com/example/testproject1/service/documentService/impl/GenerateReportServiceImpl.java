package com.example.testproject1.service.documentService.impl;

import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.dto.ReportForJsonDTO;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.documentService.GenerateReportService;
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
 * Создает отчеты по сгенерированным документам и сохраняет их в json формате по авторам.
 *
 * @author smigranov
 */
@Service
public class GenerateReportServiceImpl implements GenerateReportService {
    /**
     * Путь к папке с json файлами
     */
    private static final String SHORTPATH = ClassLoader.getSystemClassLoader().getResource("").getPath();
    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateReportServiceImpl.class);
    /**
     * Объект для библиотеки Fasterxml Jackson
     * с настройками даты
     */
    private final ObjectMapper objectMapper = new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(SerializationFeature.INDENT_OUTPUT);


    /**
     * {@inheritDoc}
     */
    @Override
    public void generateReport() {
        /*Map<Person, List<BaseDocument>> totalMap = new HashMap<>();
        for (BaseDocument baseDocument : documentStorageService.getAll()) {
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
        LOGGER.info("Путь к файлам:" + SHORTPATH);
        for (Map.Entry<Person, List<BaseDocument>> entry : totalMap.entrySet()) {
            writeReportInFile(entry);
        }*/
    }

    public void writeReportInFile(Map.Entry<Person, List<BaseDocument>> entry) {
        ReportForJsonDTO reportForJsonDTO = ReportForJsonDTO.newBuilder()
                .setPerson(entry.getKey())
                .setDocumentList(entry.getValue())
                .build();
        StringBuilder secondName = new StringBuilder(entry.getKey().getSecondName());
        //Полный путь к файлу
        StringBuilder filepathFull = new StringBuilder(SHORTPATH + secondName + ".json");
        LOGGER.info("Создаем файл " + secondName + ".json");
        try {
            objectMapper.writeValue(new File(filepathFull.toString()), reportForJsonDTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
