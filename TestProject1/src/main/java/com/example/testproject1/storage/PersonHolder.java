package com.example.testproject1.storage;

import com.example.testproject1.model.person.Person;

import java.util.List;

/**
 * Интерфейс для получения списка {@link Person}
 */
public interface PersonHolder {
    /**
     * Метод получения сохраненных документов
     *
     * @return
     */
    List<Person> getPersonListList();
}
