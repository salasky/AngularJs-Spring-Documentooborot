package com.example.testproject1.dao.incomingdocument;

import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.model.document.IncomingDocument;

import java.util.List;
import java.util.Optional;
/**
 * Интерфейс репозитория для {@link IncomingDocument}
 *
 * @author smigranov
 */
public interface IncomingDocumentRepository {
    /**
     * Метод сохранения объекта класса {@link IncomingDocument} в базу данных
     *
     * @param incomingDocument объекта класса {@link IncomingDocument}
     * @return возвращает IncomingDocument или null при неудаче
     */
    Optional<IncomingDocument> create(IncomingDocument incomingDocument);
    /**
     * Метод получения всех {@link IncomingDocument} из базы данных
     *
     * @return возвращает List<IncomingDocument>
     */
    List<IncomingDocument> getAll();
    /**
     * Метод получения {@link IncomingDocument} из базы по id
     *
     * @param id нужного объекта {@link IncomingDocument}
     * @return возвращает null или объект {@link IncomingDocument}
     */
    Optional<IncomingDocument> getById(String id);
    /**
     * Метод обновления информации {@link IncomingDocument}
     *
     * @param incomingDocument объект класса {@link IncomingDocument}
     * @return возвращает количество измененных строк. 0 при неудаче.
     */
    Integer update(IncomingDocument incomingDocument);
    /**
     * Метод удаления всех записей с таблицы baseDocument
     *
     * @return возвращает true при успешном удалении и false при неудаче
     */
    boolean deleteAll() throws DeletePoorlyException;
    /**
     * Метод удаления по id
     *
     * @param id UUID в строковом формате
     * @return возвращает true при успешном удалении и false при неудаче
     */
    boolean deleteById(String id) throws DeletePoorlyException;
    /**
     * Метод проверки существования {@link IncomingDocument} в базе по переданному uuid
     *
     * @param uuid документа
     * @return возвращает true при существовании записи и false при отсутствии
     */
    boolean existById(String uuid);
}
