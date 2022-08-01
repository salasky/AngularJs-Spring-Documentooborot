package com.example.testproject1.dao.person;

import com.example.testproject1.model.staff.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    Integer create(Person person);

    List<Person> getAll();

    Optional<Person> getById(String id);

    Integer update(Person person);

    Integer deleteAll();

    Integer deleteById(String id);
}
