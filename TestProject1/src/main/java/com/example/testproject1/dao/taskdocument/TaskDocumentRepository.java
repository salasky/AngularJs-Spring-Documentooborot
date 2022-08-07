package com.example.testproject1.dao.taskdocument;

import com.example.testproject1.model.document.TaskDocument;

import java.util.List;
import java.util.Optional;
/**
 * Интерфейс репозитория для {@link TaskDocument}
 *
 * @author smigranov
 */
public interface TaskDocumentRepository {
    /**
     * Метод сохранения объекта класса {@link TaskDocument} в базу данных
     *
     * @param taskDocument объекта класса {@link TaskDocument}
     * @return возвращает Optional<TaskDocument>
     */
    Optional<TaskDocument> create(TaskDocument taskDocument);
    /**
     * Метод получения всех {@link TaskDocument} из базы данных
     *
     * @return возвращает List<TaskDocument>
     */
    List<TaskDocument> getAll();
    /**
     * Метод получения {@link TaskDocument} из базы по id
     *
     * @param id нужного объекта {@link TaskDocument}
     * @return возвращает null или объект {@link TaskDocument}
     */
    Optional<TaskDocument> getById(String id);
    /**
     * Метод обновления информации {@link TaskDocument}
     *
     * @param taskDocument объект класса {@link TaskDocument}
     * @return возвращает количество измененных строк. 0 при неудаче.
     */
    Integer update(TaskDocument taskDocument);
    /**
     * Метод удаления всех записей с таблицы taskDocument
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
     * Метод проверки существования {@link TaskDocument} в базе по переданному uuid
     *
     * @param uuid документа
     * @return возвращает true при существовании записи и false при отсутствии
     */
    boolean existById(String uuid);
}
