package com.example.testproject1.service.dbservice.person;

import com.example.testproject1.dao.person.PersonRepository;
import com.example.testproject1.model.staff.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Optional<Person> create(Person person) {
        LOGGER.info("Попытка создания Person");
        int updateCount = personRepository.create(person);
        if (updateCount == 1) {
            LOGGER.info("Person успешно сохранен");
            return Optional.ofNullable(person);
        }
        LOGGER.error("Неудачная попытка сохранения Person");
        return null;
    }

    @Override
    public List<Person> getall() {
        LOGGER.info("Попытка выдачи всех Person");
        return personRepository.getAll();
    }

    @Override
    public Optional<Person> getById(String id) {
        LOGGER.info("Попытка получить Person по id");
        return personRepository.getById(id);
    }

    @Override
    public Optional<Person> update(Person person) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у Person с id {0}", person.getId().toString()));
        int updateCount = personRepository.update(person);
        if (updateCount == 1) {
            LOGGER.info("Person успешно обновлен");
            return Optional.ofNullable(person);
        }
        LOGGER.error("Неудачная попытка обновления Person");
        return null;
    }

    @Override
    public String deleteAll() {
        LOGGER.info("Попытка удалить все записи в таблице person");
        int count = personRepository.deleteAll();
        if (count > 0) {
            LOGGER.info("Записи из таблицы person успешно удалены");
            return "Записи из таблицы person успешно удалены";
        }
        LOGGER.error("Не удачная попытка удаления записей из таблицы person");
        return "Не удачная попытка удаления записей из таблицы person";
    }

    @Override
    public String deleteById(String id) {
        LOGGER.info("Попытка удалить запись из таблицы person");
        int count = personRepository.deleteById(id);
        if (count > 0) {
            LOGGER.info("Запись из таблицы person успешно удалена");
            return "Запись из таблицы person успешно удалена";
        }
        LOGGER.error("Не удачная попытка удаления записи из таблицы person");
        return "Не удачная попытка удаления записи из таблицы person";
    }
}
