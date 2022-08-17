package com.example.testproject1.service.randomizer;

import com.example.testproject1.model.documentenum.DocumentDeliveryType;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.staffservice.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.sql.Timestamp;
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

    @Autowired
    private StorageService<Person> personStorageService;
    /**
     * Лист названий документов из application.yaml
     */
    @Value("${doc.documentName}")
    private List<String> newDocNameList;
    /**
     * Лист текстов документов из application.yaml
     */
    @Value("${doc.documentText}")
    private List<String> newDocTextList;
    /**
     * List person для авторов, отправителей и получателей
     */
    private List<Person> personList;
    /**
     * Random для получения рандомных значений
     */
    private Random random;

    /**
     * Инициализируем PersonList
     */
    @PostConstruct
    private void postConstruct() {
        personList = personStorageService.getList();
        random = new Random();
    }

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
     * @return возвращает рандомный рег.номер типа {@link Long} в промежутке от 0 до 10000
     */
    public Long getRandDocumentRegNumber() {
        return Math.abs(random.nextLong());
    }

    /**
     * Метод возврата даты
     *
     * @return возвращает рандомную дату в 2022 году
     */
    public Timestamp getRandDocumentData() {
        Long ms = 1641027402000L + (Math.abs(random.nextLong()) % (1L * 365 * 24 * 60 * 60 * 1000));
        return new Timestamp(ms);
    }

    /**
     * Метод возврата автора
     *
     * @return возвращает рандомного автора из XML файла
     */
    public Person getRandDocumentAuthor() {
        return personList.get(random.nextInt(personList.size()));
    }

    /**
     * Метод возварата текущей даты
     *
     * @return объект класса {@link Date} с текущим временем
     */
    public Timestamp getRandTaskOutDate() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * Метод возврата рандомного количества дней в промежутке от 1 до 14
     *
     * @return объект класса {@link String} содержащий количество дней от 1 до 14 в формате: X дня
     */
    public String getRandTaskExecPeriod() {
        return (random.nextInt(14) + " дня");
    }

    /**
     * Метод возврата ответственного лица
     *
     * @return возвращает рандомного автора из xml файла
     */
    public Person getRandTaskResponsible() {
        return personList.get(random.nextInt(personList.size()));
    }

    /**
     * Метод рандомно выдает true или false.Отслеживается документ или нет.
     *
     * @return объект класса {@link Boolean} с рандомным значением true или false
     */
    public Boolean getTaskSignOfControl() {
        return Math.random() < 0.5;
    }

    /**
     * Метод возврата контролирующего человека
     *
     * @return возвращает рандомного контролирующего из XML
     */
    public Person getRandTaskControlPerson() {
        return personList.get(random.nextInt(personList.size()));
    }

    /**
     * Метод возвращает ранддомного отправителя из persons.xml
     *
     * @return рандомный объект класса {@link Person} из persons.xml
     */
    public Person getRandIncomingDocumentSender() {
        return personList.get(random.nextInt(personList.size()));
    }

    /**
     * Метод возврата рандомного получателя
     *
     * @return возвращает рандомного получателя из XML
     */
    public Person getIncomingDocumentDestination() {
        return personList.get(random.nextInt(personList.size()));
    }

    /**
     * Метод возвращает рандомный номер входящего документа
     *
     * @return рандомный объект класса {@link Long} в промежутке от 0 до 10000 с использованием {@link Math#random()}
     */
    public Long getIncomingDocumentNumber() {
        return Math.abs(random.nextLong());
    }

    /**
     * Метод возвращает рандомную дату в 2022 году
     *
     * @return объект класса {@link Date} в 2022 году
     */
    public Timestamp getRandIncomingDocumentDate() {
        var ms = 1641027402000L + (Math.abs(random.nextLong()) % (1L * 365 * 24 * 60 * 60 * 1000));
        return new Timestamp(ms);
    }

    /**
     * Метод возвращает рандомного адресата
     *
     * @return рандомный объект класса {@link Person} из person.xml
     */
    public Person getRandOutgoingDocumentSender() {
        return personList.get(random.nextInt(personList.size()));
    }

    /**
     * Метод возвращает рандомный способ доставки из enum
     *
     * @return рандомное значение из {@link DocumentDeliveryType}
     */
    public DocumentDeliveryType getRandOutgoingDocumentDeliveryType() {
        return DocumentDeliveryType.values()[new Random().nextInt(DocumentDeliveryType.values().length)];
    }
}
