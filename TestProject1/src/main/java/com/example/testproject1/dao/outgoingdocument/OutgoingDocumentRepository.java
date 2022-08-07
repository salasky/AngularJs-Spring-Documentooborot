package com.example.testproject1.dao.outgoingdocument;

import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.model.document.OutgoingDocument;

import java.util.List;
import java.util.Optional;
/**
 * Интерфейс репозитория для {@link OutgoingDocument}
 *
 * @author smigranov
 */
public interface OutgoingDocumentRepository {
    /**
     * Метод сохранения объекта класса {@link OutgoingDocument} в базу данных
     *
     * @param outgoingDocument объекта класса {@link OutgoingDocument}
     * @return возвращаетOptional<OutgoingDocument>
     */
    Optional<OutgoingDocument> create(OutgoingDocument outgoingDocument);
    /**
     * Метод получения всех {@link OutgoingDocument} из базы данных
     *
     * @return возвращает List<BaseDocument>
     */
    List<OutgoingDocument> getAll();
    /**
     * Метод получения {@link OutgoingDocument} из базы по id
     *
     * @param id нужного объекта {@link OutgoingDocument}
     * @return возвращает null или объект {@link OutgoingDocument}
     */
    Optional<OutgoingDocument> getById(String id);
    /**
     * Метод обновления информации {@link OutgoingDocument}
     *
     * @param outgoingDocument объект класса {@link OutgoingDocument}
     * @return возвращает количество измененных строк. 0 при неудаче.
     */
    Integer update(OutgoingDocument outgoingDocument);
    /**
     * Метод удаления всех записей с таблицы outgoingDocument
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
     * Метод проверки существования {@link OutgoingDocument} в базе по переданному uuid
     *
     * @param uuid документа
     * @return возвращает true при существовании записи и false при отсутствии
     */
    boolean existById(String uuid);
}
