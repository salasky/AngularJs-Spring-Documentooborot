package com.example.testproject1.service.facadeservice;

import java.sql.BatchUpdateException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Интерфейс для фасадных сервисов с CRUD операциями
 *
 * @author smigranov
 */
public interface CrudFacadeService<T> {
    /**
     * Метод сохранения объекта указанного класса в базу данных
     *
     * @param entity объекта класса
     * @return возвращает DTO объект
     */
    T create(T entity);

    /**
     * Метод получения всех объектов указанного из базы данных
     *
     * @return возвращает List<> DTO объектов
     */
    List<T> getAll();

    /**
     * Метод получения объекта указанного типа из базы по id
     *
     * @param id нужного объекта
     * @return возвращает null или объект DTO
     */
    Optional<T> getById(UUID id);

    /**
     * Метод обновления информации указанного объекта
     *
     * @param entity объект указанного класса
     * @return возвращает объект DTO
     */
    T update(T entity);

    /**
     * Метод сохранения List объектов указанного класса в базу данных
     *
     * @param entityList List объектов класса
     */
    void saveAll(List<T> entityList) throws BatchUpdateException;

}
