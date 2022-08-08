package com.example.testproject1.service.dbservice.basedocument;

import com.example.testproject1.model.document.BaseDocument;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс crud операций для класса {@link BaseDocument}
 *
 * @author smigranov
 */
public interface BaseDocumentService {
    /**
     * Метод сохранения объекта класса {@link BaseDocument} в базу данных
     *
     * @param baseDocument объекта класса {@link BaseDocument}
     * @return возвращает сохраненный объект,или null
     */
    Optional<BaseDocument> create(BaseDocument baseDocument);

    /**
     * Метод получения всех {@link BaseDocument} из базы данных
     *
     * @return возвращает List<BaseDocument>
     */
    List<BaseDocument> getall();

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
     * @return возвращает возвращает null или объект {@link BaseDocument}
     */
    Optional<BaseDocument> update(BaseDocument baseDocument);

    /**
     * Метод удаления всех записей с таблицы baseDocument
     */
    void deleteAll();

    /**
     * Метод удаления по id
     *
     * @param id UUID в строковом формате
     */
    void deleteById(String id);

    /**
     * Метод проверки существования {@link BaseDocument} в базе с переданным рег.номером
     *
     * @param regNumber регистрационный номер документа
     * @return возвращает true при существовании записи и false при отсутствии
     */
    boolean existByRegNumber(Long regNumber);
}
