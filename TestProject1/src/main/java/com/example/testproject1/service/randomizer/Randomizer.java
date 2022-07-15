package com.example.testproject1.service.randomizer;

import com.example.testproject1.exeption.DocumentExistsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Класс для рандомной выдачи данных
 * @author smigranov
 * @version 1.0
 */
@Service
public class Randomizer {
    @Value("${doc.documentName}")
    private List<String> newDocNameList;
    /**
     * Лист текстов поручений из application.yaml
     */
    @Value("${doc.documentText}")
    private List<String> newDocTextList;
    /**
     * Лист авторов поручений из application.yaml
     */
    @Value("${doc.documentAuthor}")
    private List<String> newDocAuthorList;
    /**
     * Лист признаков контроля поручений из application.yaml
     */
    @Value("${doc.documentControl}")
    private List<String> newDocControlList;
    /**
     * Лист контролеров поручений из application.yaml
     */
    @Value("${doc.documentControlPerson}")
    private List<String> newDocControlPersonList;

    /**
     * Лист значений названий входящих документов из application.yaml
     */
    @Value("${doc.documentIncomingList}")
    private List<String> newDocIncomingList;
    /**
     * Лист значений адресатов из application.yaml
     */
    @Value("${doc.documentDistPerson}")
    private List<String> newDocDistPerson;

    /**
     * Лист названий исходящих документов из application.yaml
     */
    @Value("${doc.documentOutName}")
    private List<String> newdocNameOutgoingList;
    /**
     * Лист типа доставки исходящих документов из application.yaml
     */
    @Value("${doc.docDeliveryType}")
    private List<String> newdocDeliveryTypeList;

    public UUID getRandUUID(){
        return UUID.randomUUID();
    }
    public String getRandDocName(){
        return newDocNameList.get((int) (Math.random() * newDocNameList.size()));
    }

    public String getRandDocText(){
        return newDocTextList.get((int) (Math.random() * newDocTextList.size()));
    }


    public Long getRandDocumentRegNumber()  {
        return Long.valueOf((int) (Math.random() * 100));
    }


    public String getRandDocumentData() {
        return  "2022-" + (int) (Math.random() * 12 + 1) + "-" + (int) (Math.random() * 29 + 1);

    }

    public String getRandDocumentAuthor() {
        return newDocAuthorList.get((int) (Math.random() * newDocAuthorList.size()));
    }


    public String getRandTaskOutDate() {
        Date date = new Date();
        return date.toString();
    }

    public String getRandTaskExecPeriod() {
        return  ((int) (Math.random() * 14 + 1) + " дня");
    }


    public String getRandTaskResponsible() {
        return newDocAuthorList.get((int) (Math.random() * newDocAuthorList.size()));
    }

    public String getTaskSignOfControl() {
        return newDocControlList.get((int) (Math.random() * newDocControlList.size()));
    }


    public String getRandTaskControlPerson() {
        return newDocControlPersonList.get((int) (Math.random() * newDocControlPersonList.size()));
    }


    public String getRandIncomingDocumentSender() {
        return newDocAuthorList.get((int) (Math.random() * newDocAuthorList.size()));
    }


    public String getIncomingDocumentDestination() {
        return newDocDistPerson.get((int) (Math.random() * newDocDistPerson.size()));
    }

    public Long getIncomingDocumentNumber() {
        return Long.valueOf((int) (Math.random() * 100));

    }

    public String getRandIncomingDocumentDate() {
        return "2022-" + (int) (Math.random() * 12 + 1) + "-" + (int) (Math.random() * 29 + 1);
    }



    public String getRandOutgoingDocumentSender() {
        return  newDocDistPerson.get((int) (Math.random() * newDocDistPerson.size()));
    }


    public String getRandOutgoingDocumentDeliveryType() {
        return newdocDeliveryTypeList.get((int) (Math.random() * newdocDeliveryTypeList.size()));
    }

}
