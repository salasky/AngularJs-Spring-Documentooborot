package com.example.testproject1.dao.person;

import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.staff.Person;

import java.util.List;
import java.util.Optional;
/**
 * Интерфейс репозитория для {@link Person}
 *
 * @author smigranov
 */
public interface PersonRepository {
    /**
     * Метод сохранения объекта класса {@link Person} в базу данных
     *
     * @param person объекта класса {@link Person}
     * @return возвращает Optional<Person>
     */
    Optional<Person> create(Person person);
    /**
     * Метод получения всех {@link Person} из базы данных
     *
     * @return возвращает List<BaseDocument>
     */
    List<Person> getAll();
    /**
     * Метод получения {@link Person} из базы по id
     *
     * @param id нужного объекта {@link Person}
     * @return возвращает null или объект {@link Person}
     */
    Optional<Person> getById(String id);
    /**
     * Метод обновления информации {@link Person}
     *
     * @param person объект класса {@link Person}
     * @return возвращает количество измененных строк. 0 при неудаче.
     */
    Integer update(Person person);
    /**
     * Метод удаления всех записей с таблицы person
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
     * Метод проверки существования {@link Person} в базе по переданному uuid
     *
     * @param uuid документа
     * @return возвращает true при существовании записи и false при отсутствии
     */
    boolean existById(String uuid);
}
