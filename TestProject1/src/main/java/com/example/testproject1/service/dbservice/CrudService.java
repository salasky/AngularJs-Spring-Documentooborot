package com.example.testproject1.service.dbservice;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс для сервисов с CRUD операциями
 *
 * @author smigranov
 */
public interface CrudService<T> {
    /**
     * Метод сохранения объекта указанного класса в базу данных
     *
     * @param object объекта класса
     * @return возвращает Optional<Объект указанного класса>
     */
    Optional<T> create(T object);

    /**
     * Метод получения всех объектов указанного из базы данных
     *
     * @return возвращает List<> объектов указанного типа
     */
    List<T> getall();

    /**
     * Метод получения объекта указанного типа из базы по id
     *
     * @param id нужного объекта
     * @return возвращает null или объект
     */
    Optional<T> getById(String id);

    /**
     * Метод обновления информации указанного объекта
     *
     * @param object объект указанного класса
     * @return возвращает null или объект
     */
    Optional<T> update(T object);

    /**
     * Метод удаления всех записей с таблицы
     */
    void deleteAll();

    /**
     * Метод удаления по id
     *
     * @param id UUID в строковом формате
     */
    void deleteById(String id);
}
