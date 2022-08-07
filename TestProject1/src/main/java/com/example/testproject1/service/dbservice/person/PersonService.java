package com.example.testproject1.service.dbservice.person;

import com.example.testproject1.model.staff.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Optional<Person> create(Person person);

    List<Person> getall();

    Optional<Person> getById(String id);

    Optional<Person> update(Person person);

    void deleteAll();

    void deleteById(String id);
}

