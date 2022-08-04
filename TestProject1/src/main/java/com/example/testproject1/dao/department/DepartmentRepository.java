package com.example.testproject1.dao.department;

import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.staff.Department;

import java.util.List;
import java.util.Optional;
/**
 * Интерфейс репозитория для {@link Department}
 *
 * @author smigranov
 */
public interface DepartmentRepository {
    /**
     * Метод получения всех {@link Department} из базы данных
     *
     * @return возвращает List<Department>
     */
    List<Department> getAll();
    /**
     * Метод получения {@link Department} из базы по id
     *
     * @param id нужного объекта {@link Department}
     * @return возвращает null или объект {@link Department}
     */
    Optional<Department> getById(String id);
    /**
     * Метод сохранения объекта класса {@link Department} в базу данных
     *
     * @param department объекта класса {@link Department}
     * @return возвращает количество созданных строк 1 удачное сохранение, 0 ошибка сохранения
     */
    Integer create(Department department);
    /**
     * Метод обновления информации {@link Department}
     *
     * @param department объект класса {@link Department}
     * @return возвращает количество измененных строк. 0 при неудаче.
     */
    Integer update(Department department);
    /**
     * Метод удаления всех записей с таблицы department
     *
     * @return возвращает количество удаленных строк
     */
    Integer deleteAll();
    /**
     * Метод удаления по id
     *
     * @param id UUID в строковом формате
     * @return возвращает 1 при удачном удалениии и 0 при неудаче
     */
    Integer deleteById(String id);
    /**
     * Метод проверки существования {@link Department} в базе по переданному uuid
     *
     * @param uuid department
     * @return возвращает true при существовании записи и false при отсутствии
     */
    boolean existById(String uuid);
}
