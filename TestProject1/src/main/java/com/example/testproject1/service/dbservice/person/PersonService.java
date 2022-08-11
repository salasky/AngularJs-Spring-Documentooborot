package com.example.testproject1.service.dbservice.person;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

/**
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций объектов класса {@link Person} к базе данных .
 *
 * @author smigranov
 */
@Service("PersonService")
@Order(value = 4)
public class PersonService implements CrudService<Person> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    /**
     * Бин {@link CrudRepository}
     */
    @Autowired
    private CrudRepository<Person> personRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Person create(Person person) throws DocflowRuntimeApplicationException {
        Person personDB = personRepository.create(person);
        if (personDB != null) {
            LOGGER.info("Person успешно сохранен");
            return personDB;
        }
        throw new DocflowRuntimeApplicationException("Неудачная попытка сохранения Person");
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<Person> getAll() {
        LOGGER.info("Попытка выдачи всех Person");
        return personRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Person> getById(String id) {
        LOGGER.info("Попытка получить Person по id");
        return personRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person update(Person person) throws DocflowRuntimeApplicationException {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у Person с id {0}", person.getId().toString()));
        int updateCount = personRepository.update(person);
        if (updateCount == 1) {
            LOGGER.info("Person успешно обновлен");
            return person;
        }
        throw new DocflowRuntimeApplicationException("Неудачная попытка обновления Person");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info("Попытка удаления записей из таблицы Person");
        personRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(String id) throws DocflowRuntimeApplicationException {
        try {
            personRepository.deleteById(id);
        } catch (DeleteByIdException e) {
            throw new DocflowRuntimeApplicationException("Запись из таблицы Person не удалена");
        }
    }
}
