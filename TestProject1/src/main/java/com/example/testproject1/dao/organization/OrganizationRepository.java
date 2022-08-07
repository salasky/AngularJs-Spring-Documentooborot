package com.example.testproject1.dao.organization;

import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.staff.Organization;

import java.util.List;
import java.util.Optional;
/**
 * Интерфейс репозитория для {@link Organization}
 *
 * @author smigranov
 */
public interface OrganizationRepository {
    /**
     * Метод сохранения объекта класса {@link Organization} в базу данных
     *
     * @param organization объекта класса {@link Organization}
     * @return возвращает  Optional<Organization>
     */
    Optional<Organization> create(Organization organization);
    /**
     * Метод получения всех {@link Organization} из базы данных
     *
     * @return возвращает List<Organization>
     */
    List<Organization> getAll();
    /**
     * Метод получения {@link Organization} из базы по id
     *
     * @param uuid нужного объекта {@link Organization}
     * @return возвращает null или объект {@link Organization}
     */
    Optional<Organization> getById(String uuid);
    /**
     * Метод обновления информации {@link Organization}
     *
     * @param organization объект класса {@link Organization}
     * @return возвращает количество измененных строк. 0 при неудаче.
     */
    Integer update(Organization organization);
    /**
     * Метод удаления всех записей с таблицы organization
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
     * Метод проверки существования {@link Organization} в базе по переданному uuid
     *
     * @param uuid документа
     * @return возвращает true при существовании записи и false при отсутствии
     */
    boolean existById(String uuid);
}
