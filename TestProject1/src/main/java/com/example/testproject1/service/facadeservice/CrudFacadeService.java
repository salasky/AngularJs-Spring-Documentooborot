package com.example.testproject1.service.facadeservice;

import com.example.testproject1.model.dto.DepartmentDTO;
import com.example.testproject1.model.staff.Department;
import liquibase.pro.packaged.E;

import java.sql.BatchUpdateException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Интерфейс для фасадных сервисов с CRUD операциями
 *
 * @author smigranov
 */
public interface CrudFacadeService<D,T> {
    /**
     * Метод сохранения объекта указанного класса в базу данных
     *
     * @param entity объекта класса
     * @return возвращает Объект указанного класса
     */
     D create(T entity);

    /**
     * Метод получения всех объектов указанного из базы данных
     *
     * @return возвращает List<> объектов указанного типа
     */
    List<D> getAll();

    /**
     * Метод получения объекта указанного типа из базы по id
     *
     * @param id нужного объекта
     * @return возвращает null или объект
     */
    Optional<D> getById(UUID id);

    /**
     * Метод обновления информации указанного объекта
     *
     * @param entity объект указанного класса
     * @return возвращает объект
     */
    D update(T entity);

}
