package com.example.testproject1.service.docBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.OutgoingDocument;
/**
 * Интерфейс для Builder-a исходящих документов
 * @author smigranov
 * @version 1.0
 */
public interface OutgoingBuilder {

    /**
     * Метод для записи в объект названия документа
     * @return Объект билдера
     */
    public OutgoingBuilder fixDocumentName();
    /**
     * Метод для записи в объект текста документа
     * @return Объект билдера
     */
    public OutgoingBuilder fixDocumentText();

    /**
     * Метод для записи в объект текста документа
     * @return Объект билдера
     * @throws DocumentExistsException при существовании такого рег.номера выбрасывается исключение
     */
    public OutgoingBuilder fixDocumentRegNumber() throws DocumentExistsException;
    /**
     * Метод для записи в объект даты создания документа
     * @return Объект билдера
     */
    public OutgoingBuilder fixDocumentData();
    /**
     * Метод для записи в объект автора документа
     * @return Объект билдера
     */
    public OutgoingBuilder fixDocumentAuthor();
    /**
     * Метод для записи в объект адресата документа
     * @return Объект билдера
     */
    public OutgoingBuilder fixOutgoingDocumentSender();
    /**
     * Метод для записи в объект типа доставки документа
     * @return Объект билдера
     */
    public OutgoingBuilder fixOutgoingDocumentDeliveryType();
    /**
     * Метод построения поручения
     * @return Объект OutgoingDocument
     */
    public OutgoingDocument build();

}
