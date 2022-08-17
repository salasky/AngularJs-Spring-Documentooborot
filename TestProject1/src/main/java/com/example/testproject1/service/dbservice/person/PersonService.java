package com.example.testproject1.service.dbservice.person;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.BatchUpdateException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций объектов класса {@link Person} к базе данных .
 *
 * @author smigranov
 */
@Service("PersonService")
@Order(value = 4)
public class PersonService implements CrudService<Person> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private CrudRepository<Person> personRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Person create(Person person) {
        LOGGER.info("Попытка создания Person");
        if (person.getId() == null) {
            person.setId(UUID.randomUUID());
        }
        return personRepository.create(person);
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
    public Optional<Person> getById(UUID id) {
        LOGGER.info("Попытка получить Person по id");
        return personRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person update(Person person) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у Person с id {0}", person.getId().toString()));
        return personRepository.update(person);
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
    public void deleteById(UUID id) {
        LOGGER.info(MessageFormat.format("Попытка удаления Person с id {0}", id.toString()));
        personRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<Person> entityList) {
        LOGGER.info("Попытка сохранения List<Person> в таблицу Person");
        entityList.stream().filter(entity -> entity.getId() == null).forEach(entity -> entity.setId(UUID.randomUUID()));
        try {
            personRepository.saveAll(entityList);
        } catch (BatchUpdateException e) {
            throw new DocflowRuntimeApplicationException("Ошибка сохранения", e);
        }
    }
}
