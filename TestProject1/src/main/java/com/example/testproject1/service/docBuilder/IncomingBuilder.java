package com.example.testproject1.service.docBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.IncomingDocument;
/**
 * Интерфейс для Builder-a входящих документов
 * @author smigranov
 * @version 1.0
 */
public interface IncomingBuilder {
    /**
     * Метод для записи в объект id документа
     * @return Объект билдера
     */
    public IncomingBuilder fixDocumentId();
    /**
     * Метод для записи в объект названия документа
     * @return Объект билдера
     */
    public IncomingBuilder fixDocumentName();
    /**
     * Метод для записи в объект текста документа
     * @return Объект билдера
     */
    public IncomingBuilder fixDocumentText();
    /**
     * Метод для записи в объект текста документа
     * @return Объект билдера
     * @throws DocumentExistsException при существовании такого рег.номера выбрасывается исключение
     */
    public IncomingBuilder fixDocumentRegNumber() throws DocumentExistsException;
    /**
     * Метод для записи в объект даты создания документа
     * @return Объект билдера
     */
    public IncomingBuilder fixDocumentData();
    /**
     * Метод для записи в объект автора документа
     * @return Объект билдера
     */
    public IncomingBuilder fixDocumentAuthor();
    /**
     * Метод для записи в объект отправителя документа
     * @return Объект билдера
     */
    public IncomingBuilder fixIncomingDocumentSender();
    /**
     * Метод для записи в объект адресата документа
     * @return Объект билдера
     */
    public IncomingBuilder fixIncomingDocumentDestination();
    /**
     * Метод для записи в объект исходящего номера документа
     * @return Объект билдера
     */
    public IncomingBuilder fixIncomingDocumentNumber();
    /**
     * Метод для записи в объект исходящей даты регистрации документа
     * @return Объект билдера
     */
    public IncomingBuilder fixIncomingDocumentDate();
    /**
     * Метод построения поручения
     * @return Объект IncomingDocument
     */
    public IncomingDocument build();

}
