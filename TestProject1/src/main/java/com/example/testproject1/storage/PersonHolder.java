package com.example.testproject1.storage;

import com.example.testproject1.model.person.Person;

import java.util.List;

public interface PersonHolder {
    /**
     * Метод получения сохраненных документов
     *
     * @return
     */
    public List<Person> getPersonListList();
}
