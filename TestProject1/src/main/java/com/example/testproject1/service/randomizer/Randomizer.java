package com.example.testproject1.service.randomizer;

import com.example.testproject1.model.enums.DocumentDeliveryType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Класс для рандомной выдачи данных
 *
 * @author smigranov
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
     * Метод возврата UUID
     *
     * @return возвращает рандомный UUID
     */
    public UUID getRandUUID() {
        return UUID.randomUUID();
    }

    /**
     * Метод возврата рандомного названия документа
     *
     * @return возвращает рандомное название из application.yaml
     */
    public String getRandDocName() {
        return newDocNameList.get((int) (Math.random() * newDocNameList.size()));
    }

    /**
     * Метод возврата рандомного текста документа
     *
     * @return возвращает рандомный текст из application.yaml
     */
    public String getRandDocText() {
        return newDocTextList.get((int) (Math.random() * newDocTextList.size()));
    }

    /**
     * Метод возврата рандомного регистрационного номера
     *
     * @return возвращает рандомный рег.номер
     */
    public Long getRandDocumentRegNumber() {
        return Long.valueOf((int) (Math.random() * 100));
    }

    /**
     * Метод возврата даты
     *
     * @return возвращает рандомную дату в 2022 году
     */
    public Date getRandDocumentData() {
        Random rnd = new Random();
        Long ms = 1641027402000L + (Math.abs(rnd.nextLong()) % (1L * 365 * 24 * 60 * 60 * 1000));
        return new Date(ms);
    }

    /**
     * Метод возврата автора
     *
     * @return возвращает рандомного автора из application.yaml
     */
    public String getRandDocumentAuthor() {
        return newDocAuthorList.get((int) (Math.random() * newDocAuthorList.size()));
    }

    /**
     * Метод возварата текущей даты
     *
     * @return
     */
    public Date getRandTaskOutDate() {
        Date date = new Date();
        return date;
    }

    /**
     * Метод возврата рандомного количества дней в промежутке от 1 до 14
     *
     * @return
     */
    public String getRandTaskExecPeriod() {
        return ((int) (Math.random() * 14 + 1) + " дня");
    }

    /**
     * Метод возврата ответственного лица
     *
     * @return возвращает рандомного автора из application.yaml
     */
    public String getRandTaskResponsible() {
        return newDocAuthorList.get((int) (Math.random() * newDocAuthorList.size()));
    }

    /**
     * Метод рандомно выдает true или false.Отслеживается документ или нет.
     *
     * @return
     */
    public Boolean getTaskSignOfControl() {
        return Math.random() < 0.5;
    }

    /**
     * Метод возврата контролирующего человека
     *
     * @return возвращает рандомного контролирующего из application.yaml
     */
    public String getRandTaskControlPerson() {
        return newDocControlPersonList.get((int) (Math.random() * newDocControlPersonList.size()));
    }

    /**
     * Метод возвращает ранддомного отправителя из application.yaml
     *
     * @return
     */
    public String getRandIncomingDocumentSender() {
        return newDocAuthorList.get((int) (Math.random() * newDocAuthorList.size()));
    }

    /**
     * Метод возврата рандомного получателя
     *
     * @return возвращает рандомного получателя из application.yaml
     */
    public String getIncomingDocumentDestination() {
        return newDocDistPerson.get((int) (Math.random() * newDocDistPerson.size()));
    }

    /**
     * Метод возвращает рандомный номер входящего документа
     *
     * @return
     */
    public Long getIncomingDocumentNumber() {
        return Long.valueOf((int) (Math.random() * 100));

    }

    /**
     * Метод возвращает рандомную дату в 2022 году
     *
     * @return
     */
    public Date getRandIncomingDocumentDate() {
        var rnd = new Random();
        var ms = 1641027402000L + (Math.abs(rnd.nextLong()) % (1L * 365 * 24 * 60 * 60 * 1000));
        return new Date(ms);
    }

    /**
     * Метод возвращает рандомного адресата
     *
     * @return
     */
    public String getRandOutgoingDocumentSender() {
        return newDocDistPerson.get((int) (Math.random() * newDocDistPerson.size()));
    }

    /**
     * Метод возвращает рандомный способ доставки из enum
     *
     * @return
     */
    public DocumentDeliveryType getRandOutgoingDocumentDeliveryType() {
        return DocumentDeliveryType.values()[new Random().nextInt(DocumentDeliveryType.values().length)];
    }
}
