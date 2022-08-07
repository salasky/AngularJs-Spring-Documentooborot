package com.example.testproject1.service.documentservice.impl;

import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.dto.ReportForJsonDTO;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.incomingdocument.IncomingDocumentService;
import com.example.testproject1.service.dbservice.outgoingdocument.OutgoingDocumentService;
import com.example.testproject1.service.dbservice.taskdocument.TaskDocumentService;
import com.example.testproject1.service.documentservice.GenerateReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    private static final String SHORT_PATH = ClassLoader.getSystemClassLoader().getResource("").getPath();
    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateReportServiceImpl.class);
    /**
     * Объект для библиотеки Fasterxml Jackson
     * с настройками даты
     */
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TaskDocumentService taskDocumentService;
    @Autowired
    private IncomingDocumentService incomingDocumentService;
    @Autowired
    private OutgoingDocumentService outgoingDocumentService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateReport() {
        Map<Person, List<BaseDocument>> totalMap = new HashMap<>();
        List<TaskDocument> taskDocumentList = taskDocumentService.getall();
        List<IncomingDocument> incomingDocumentList = incomingDocumentService.getall();
        List<OutgoingDocument> outgoingDocumentList = outgoingDocumentService.getall();

        List<BaseDocument> baseDocuments = new ArrayList<>();
        baseDocuments.addAll(taskDocumentList);
        baseDocuments.addAll(incomingDocumentList);
        baseDocuments.addAll(outgoingDocumentList);

        for (BaseDocument baseDocument : baseDocuments) {
            List<BaseDocument> baseDocumentList = new ArrayList<>();
            //Если существует запись для данного автора
            if (totalMap.containsKey(baseDocument.getAuthor())) {
                baseDocumentList = totalMap.get(baseDocument.getAuthor());
            }
            baseDocumentList.add(baseDocument);
            totalMap.put(baseDocument.getAuthor(), baseDocumentList);
        }
        LOGGER.info(new StringBuilder("Путь к файлам:").append(SHORT_PATH).toString());
        for (Map.Entry<Person, List<BaseDocument>> entry : totalMap.entrySet()) {
            writeReportInFile(entry);
        }
    }

    public void writeReportInFile(Map.Entry<Person, List<BaseDocument>> entry) {
        ReportForJsonDTO reportForJsonDTO = ReportForJsonDTO.newBuilder()
                .setPerson(entry.getKey())
                .setDocumentList(entry.getValue())
                .build();
        StringBuilder secondName = new StringBuilder(entry.getKey().getSecondName());
        //Полный путь к файлу
        StringBuilder filepathFull = new StringBuilder(SHORT_PATH).append(secondName).append(".json");
        LOGGER.info(new StringBuilder("Создаем файл ").append(secondName).append(".json").toString());
        try {
            objectMapper.writeValue(new File(filepathFull.toString()), reportForJsonDTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
