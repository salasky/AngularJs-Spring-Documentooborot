package com.example.testproject1.service.docBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.TaskDocument;

/**
 * Интерфейс для Builder-a поручений
 * @author smigranov
 * @version 1.0
 */
public interface TaskBuilder {

    /**
     * Метод для записи в объект id документа
     * @return Объект билдера
     */
    public TaskBuilder fixDocumentId();

    /**
     * Метод для записи в объект названия документа
     * @return Объект билдера
     */
    public TaskBuilder fixDocumentName();
    /**
     * Метод для записи в объект текста документа
     * @return Объект билдера
     */
    public TaskBuilder fixDocumentText();
    /**
     * Метод для записи в объектрегистрационного номера документа
     * @return Объект билдера
     * @throws DocumentExistsException при существовании такого рег.номера выбрасывается исключение
     */
    public TaskBuilder fixDocumentRegNumber() throws DocumentExistsException;
    /**
     * Метод для записи в объект даты создания документа
     * @return Объект билдера
     */
    public TaskBuilder fixDocumentData();
    /**
     * Метод для записи в объект автора документа
     * @return Объект билдера
     */
    public TaskBuilder fixDocumentAuthor();
    /**
     * Метод для записи в объект дату выдачи поручения
     * @return Объект билдера
     */
    public TaskBuilder fixTaskOutDate();
    /**
     * Метод для записи в объект ⦁	срока исполнения поручения
     * @return Объект билдера
     */
    public TaskBuilder fixTaskExecPeriod();
    /**
     * Метод для записи в объект ответственного исполнителя поручения
     * @return Объект билдера
     */
    public TaskBuilder fixTaskResponsible();
    /**
     * Метод для записи в объект признака контрольности поручения
     * @return Объект билдера
     */
    public TaskBuilder fixTaskSignOfControl();
    /**
     * Метод для записи в объект контроллшера поручения
     * @return Объект билдера
     */
    public TaskBuilder fixTaskControlPerson();
    /**
     * Метод построения поручения
     * @return Объект TaskDocument
     */
    public TaskDocument build();
}
