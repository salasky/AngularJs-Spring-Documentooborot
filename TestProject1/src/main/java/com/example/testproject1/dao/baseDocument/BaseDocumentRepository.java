package com.example.testproject1.dao.baseDocument;

import com.example.testproject1.model.document.BaseDocument;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс репозитория для {@link BaseDocument}
 *
 * @author smigranov
 */
public interface BaseDocumentRepository {
    /**
     * Метод сохранения объекта класса {@link BaseDocument} в базу данных
     *
     * @param baseDocument объекта класса {@link BaseDocument}
     * @return возвращает количество созданных строк 1 удачное сохранение, 0 ошибка сохранения
     */
    Integer create(BaseDocument baseDocument);

    /**
     * Метод получения всех {@link BaseDocument} из базы данных
     *
     * @return возвращает List<BaseDocument>
     */
    List<BaseDocument> getAll();

    /**
     * Метод получения {@link BaseDocument} из базы по id
     *
     * @param id нужного объекта {@link BaseDocument}
     * @return возвращает null или объект {@link BaseDocument}
     */
    Optional<BaseDocument> getById(String id);

    /**
     * Метод обновления информации {@link BaseDocument}
     *
     * @param baseDocument объект класса {@link BaseDocument}
     * @return возвращает количество измененных строк. 0 при неудаче.
     */
    Integer update(BaseDocument baseDocument);

    /**
     * Метод удаления всех записей с таблицы baseDocument
     *
     * @return возвращает количество удаленнх строк
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
     * Метод проверки существования {@link BaseDocument} в базе с переданным рег.номером
     *
     * @param regNumber регистрационный номер документа
     * @return возвращает true при существовании записи и false при отсутствии
     */
    boolean existByRegNumber(Long regNumber);

    /**
     * Метод проверки существования {@link BaseDocument} в базе по переданному uuid
     *
     * @param uuid документа
     * @return возвращает true при существовании записи и false при отсутствии
     */
    boolean existById(String uuid);
}
