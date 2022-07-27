package com.example.testproject1.service.storage;

import com.example.testproject1.model.staff.Person;

import java.util.List;

/**
 * Интерфейс для получения списка {@link Person}
 */
public interface PersonStorageService {
    /**
     * Метод получения сохраненных документов
     *
     * @return объект {@link List} содержащий объекты класса {@link Person}
     */
    List<Person> getPersonList();
}
