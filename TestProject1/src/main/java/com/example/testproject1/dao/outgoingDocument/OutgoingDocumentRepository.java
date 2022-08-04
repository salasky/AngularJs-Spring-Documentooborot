package com.example.testproject1.dao.outgoingDocument;

import com.example.testproject1.model.document.BaseDocument;
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
     * @return возвращает количество созданных строк 1 удачное сохранение, 0 ошибка сохранения
     */
    Integer create(OutgoingDocument outgoingDocument);
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
     * @return возвращает количество удаленных строк
     */
    Integer deleteAll();
    /**
     * Метод удаления по id
     *
     * @param id UUID в строковом формате
     * @return возвращает 1 при удачном удалениии и 0 при неудаче
     */
    Integer deleteById(String id);
    /**
     * Метод проверки существования {@link OutgoingDocument} в базе по переданному uuid
     *
     * @param uuid документа
     * @return возвращает true при существовании записи и false при отсутствии
     */
    boolean existById(String uuid);
}
