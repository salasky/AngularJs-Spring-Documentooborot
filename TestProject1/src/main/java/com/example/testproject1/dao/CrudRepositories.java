package com.example.testproject1.dao;

import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.model.staff.Department;

import java.util.List;
import java.util.Optional;

public interface CrudRepositories <T>{
    /**
     * Метод получения всех {@link T} из базы данных
     *
     * @return возвращает List<T>
     */
    List<T> getAll();
    /**
     * Метод получения {@link T} из базы по id
     *
     * @param id нужного объекта {@link T}
     * @return возвращает null или объект {@link T}
     */
    Optional<T> getById(String id);
    /**
     * Метод сохранения объекта класса {@link T} в базу данных
     *
     * @param obj объекта класса {@link T}
     * @return возвращает Optional<T>
     */
    Optional<T> create(T obj);
    /**
     * Метод обновления информации {@link T}
     *
     * @param obj объект класса {@link T}
     * @return возвращает количество измененных строк. 0 при неудаче.
     */
    Integer update(T obj);
    /**
     * Метод удаления всех записей с таблицы
     *
     * @return возвращает true при успешном удалении и false при неудаче
     */
    boolean deleteAll() throws DeletePoorlyException;
    /**
     * Метод удаления по id
     *
     * @param id UUID в строковом формате
     * @return возвращает true при успешном удалении и false при неудаче
     */
    boolean deleteById(String id) throws DeletePoorlyException;
    /**
     * Метод проверки существования {@link Department} в базе по переданному uuid
     *
     * @param uuid department
     * @return возвращает true при существовании записи и false при отсутствии
     */
    boolean existById(String uuid);
}
