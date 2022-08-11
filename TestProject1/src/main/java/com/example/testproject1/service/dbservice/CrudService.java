package com.example.testproject1.service.dbservice;

import com.example.testproject1.exception.DocflowRuntimeApplicationException;

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
    T create(T object) throws DocflowRuntimeApplicationException;

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
     * Метод обновления информации указанного объекта
     *
     * @param object объект указанного класса
     * @return возвращает null или объект
     */
    T update(T object) throws DocflowRuntimeApplicationException;

    /**
     * Метод удаления всех записей с таблицы
     */
    void deleteAll();

    /**
     * Метод удаления по id
     *
     * @param id UUID в строковом формате
     */
    void deleteById(String id) throws DocflowRuntimeApplicationException;
}
