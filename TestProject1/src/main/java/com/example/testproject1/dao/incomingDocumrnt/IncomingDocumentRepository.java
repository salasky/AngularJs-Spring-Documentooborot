package com.example.testproject1.dao.incomingDocumrnt;

import com.example.testproject1.model.document.BaseDocument;
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
     * @return возвращает количество созданных строк 1 удачное сохранение, 0 ошибка сохранения
     */
    Integer create(IncomingDocument incomingDocument);
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
     * Метод проверки существования {@link IncomingDocument} в базе по переданному uuid
     *
     * @param uuid документа
     * @return возвращает true при существовании записи и false при отсутствии
     */
    boolean existById(String uuid);
}
