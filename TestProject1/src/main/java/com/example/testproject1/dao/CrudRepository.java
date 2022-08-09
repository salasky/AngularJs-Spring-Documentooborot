package com.example.testproject1.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Интерфейс для репозиториев с CRUD операциями
 *
 * @author smigranov
 */
public interface CrudRepository<T> {
    /**
     * Метод получения всех объектов указанного из базы данных
     *
     * @return возвращает List<> объектов указанного типа
     */
    List<T> getAll();

    /**
     * Метод получения объекта указанного типа из базы по id
     *
     * @param id нужного объекта
     * @return возвращает null или объект
     */
    Optional<T> getById(String id);

    /**
     * Метод сохранения объекта указанного класса в базу данных
     *
     * @param obj объекта класса
     * @return возвращает Объект указанного класса
     */
    T create(T obj);

    /**
     * Метод обновления информации указанного объекта
     *
     * @param obj объект класса
     * @return возвращает количество измененных строк. 0 при неудаче.
     */
    int update(T obj);

    /**
     * Метод удаления всех записей с таблицы
     *
     */
    void deleteAll();

    /**
     * Метод удаления по id
     *
     * @param id UUID в строковом формате
     */
    boolean deleteById(String id);

    /**
     * Метод проверки существования объекта в базе по переданному uuid
     *
     * @param uuid department
     * @return возвращает true при существовании записи и false при отсутствии
     */
    boolean existById(UUID uuid);
}