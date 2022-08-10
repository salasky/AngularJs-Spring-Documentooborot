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
     * Лог при успешном сохранении
     */
    private final String CREATE_SUCCESS = "Person успешно сохранен";
    /**
     * Лог при неудачном сохранении
     */
    private final String CREATE_FAIL = "Неудачная попытка сохранения Person";
    /**
     * Лог при выдаче всех Person
     */
    private final String GET_ALL_ATTEMPT = "Попытка выдачи всех Person";
    /**
     * Лог при выдаче Person по id
     */
    private final String GET_BY_ID_ATTEMPT = "Попытка получить Person по id";
    /**
     * Лог при успешном обновлении
     */
    private final String UPDATE_SUCCESS = "Person успешно обновлен";
    /**
     * Лог при неудачном обновлении
     */
    private final String UPDATE_FAIL = "Неудачная попытка обновления Person";
    /**
     * Лог при попытке удаления всех записей
     */
    private final String DELETE_SUCCESS = "Попытка удаления записей из таблицы Person";
    /**
     * Лог при успешном удалении записи по id
     */
    private final String DELETE_BY_ID_SUCCESS = "Запись из таблицы Person успешно удалена";
    /**
     * Лог при неудачном удалении удалении записи по id
     */
    private final String DELETE_BY_ID_FAIL = "Запись из таблицы Person не удалена";

    /**
     * {@inheritDoc}
     */
    @Override
    public Person create(Person person) throws DocflowRuntimeApplicationException {
        Person personDB = personRepository.create(person);
        if (personDB != null) {
            LOGGER.info(CREATE_SUCCESS);
            return personDB;
        }
        throw new DocflowRuntimeApplicationException(CREATE_FAIL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Person> getall() {
        LOGGER.info(GET_ALL_ATTEMPT);
        return personRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Person> getById(String id) {
        LOGGER.info(GET_BY_ID_ATTEMPT);
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
            LOGGER.info(UPDATE_SUCCESS);
            return person;
        }
        throw new DocflowRuntimeApplicationException(UPDATE_FAIL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info(DELETE_SUCCESS);
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
            throw new DocflowRuntimeApplicationException(DELETE_BY_ID_FAIL);
        }
    }
}
