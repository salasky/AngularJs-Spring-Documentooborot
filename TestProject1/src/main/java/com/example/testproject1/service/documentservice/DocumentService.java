package com.example.testproject1.service.documentservice;

import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.exception.DocumentExistsByRegistrationNumberException;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.document.TaskDocument;

/**
 * Интерфейс для работы с документами(Сохранение)
 *
 * @author smigranov
 */
public interface DocumentService {
    /**
     * Метод сохранения поручения в базу данных
     *
     * @param taskDocument объект класса {@link TaskDocument}
     * @throws DocumentExistsByRegistrationNumberException если в базе существует запись с таким регистрационным номером
     */
    void saveTaskInDB(TaskDocument taskDocument) throws DocflowRuntimeApplicationException;

    /**
     * Метод сохранения входящих документов в базу данных
     *
     * @param incomingDocument объект класса {@link IncomingDocument}
     * @throws DocumentExistsByRegistrationNumberException если в базе существует запись с таким регистрационным номером
     */
    void saveIncomingInDB(IncomingDocument incomingDocument) throws DocflowRuntimeApplicationException;

    /**
     * Метод сохранения исходящих документов в базу данных
     *
     * @param outgoingDocument объект класса {@link OutgoingDocument}
     * @throws DocumentExistsByRegistrationNumberException если в базе существует запись с таким регистрационным номером
     */
    void saveOutgoingInDB(OutgoingDocument outgoingDocument) throws DocflowRuntimeApplicationException;
}
