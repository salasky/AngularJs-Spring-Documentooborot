package com.example.testproject1.service.dbservice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Интерфейс для сервисов с CRUD операциями
 *
 * @author smigranov
 */
public interface CrudService<T> {
    /**
     * Метод сохранения объекта указанного класса в базу данных
     *
     * @param entity объекта класса
     * @return возвращает Объект указанного класса
     */
    T create(T entity);

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
    Optional<T> getById(UUID id);

    /**
     * Метод обновления информации указанного объекта
     *
     * @param entity объект указанного класса
     * @return возвращает объект
     */
    T update(T entity);

    /**
     * Метод удаления всех записей с таблицы
     */
    void deleteAll();

    /**
     * Метод удаления по id
     *
     * @param id UUID в строковом формате
     */
    void deleteById(UUID id);

    /**
     * Метод сохранения List объектов указанного класса в базу данных
     *
     * @param entityList List объектов класса
     */
    void saveAll(List<T> entityList);
}
