package com.example.testproject1.dao.jobtittle;

import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.staff.JobTittle;

import java.util.List;
import java.util.Optional;
/**
 * Интерфейс репозитория для {@link JobTittle}
 *
 * @author smigranov
 */
public interface JobTittleRepository {
    /**
     * Метод сохранения объекта класса {@link JobTittle} в базу данных
     *
     * @param jobTittle объекта класса {@link JobTittle}
     * @return Optional<JobTittle>
     */
    Optional<JobTittle> create(JobTittle jobTittle);
    /**
     * Метод получения всех {@link JobTittle} из базы данных
     *
     * @return возвращает List<JobTittle>
     */
    List<JobTittle> getAll();
    /**
     * Метод получения {@link JobTittle} из базы по id
     *
     * @param uuid нужного объекта {@link JobTittle}
     * @return возвращает null или объект {@link JobTittle}
     */
    Optional<JobTittle> getById(String uuid);
    /**
     * Метод обновления информации {@link JobTittle}
     *
     * @param jobTittle объект класса {@link JobTittle}
     * @return возвращает количество измененных строк. 0 при неудаче.
     */
    Integer update(JobTittle jobTittle);
    /**
     * Метод удаления всех записей с таблицы jobTittle
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
     * Метод проверки существования {@link JobTittle} в базе по переданному uuid
     *
     * @param uuid документа
     * @return возвращает true при существовании записи и false при отсутствии
     */
    boolean existById(String uuid);
}
