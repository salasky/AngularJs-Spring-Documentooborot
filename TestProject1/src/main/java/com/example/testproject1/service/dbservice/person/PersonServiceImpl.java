package com.example.testproject1.service.dbservice.person;

import com.example.testproject1.dao.CrudRepositories;
import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class PersonServiceImpl implements CrudService<Person> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);
    /**
     * Бин {@link CrudRepositories}
     */
    @Autowired
    @Qualifier("PersonRepository")
    private CrudRepositories personRepository;
    /**
     * Лог при успешном сохранении
     */
    private final String CREATE_SUCCESS="Person успешно сохранен";
    /**
     * Лог при неудачном сохранении
     */
    private final String CREATE_FAIL="Неудачная попытка сохранения Person";
    /**
     * Лог при выдаче всех Person
     */
    private final String GET_ALL_ATTEMPT="Попытка выдачи всех Person";
    /**
     * Лог при выдаче Person по id
     */
    private final String GET_BY_ID_ATTEMPT="Попытка получить Person по id";
    /**
     * Лог при успешном обновлении
     */
    private final String UPDATE_SUCCESS="Person успешно обновлен";
    /**
     * Лог при неудачном обновлении
     */
    private final String UPDATE_FAIL="Неудачная попытка обновления Person";
    /**
     * Лог при успешном удалении всех записей
     */
    private final String DELETE_SUCCESS="Записи из таблицы Person успешно удалены";
    /**
     * Лог при успешном удалении записи по id
     */
    private final String DELETE_BY_ID_SUCCESS="Запись из таблицы Person успешно удалена";
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Person> create(Person person) {
        Optional<Person> optionalPerson = personRepository.create(person);
        if (optionalPerson.isPresent()) {
            LOGGER.info(CREATE_SUCCESS);
            return Optional.ofNullable(person);
        }
        LOGGER.error(CREATE_FAIL);
        return Optional.empty();
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
    public Optional<Person> update(Person person) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у Person с id {0}", person.getId().toString()));
        int updateCount = personRepository.update(person);
        if (updateCount == 1) {
            LOGGER.info(UPDATE_SUCCESS);
            return Optional.ofNullable(person);
        }
        LOGGER.error(UPDATE_FAIL);
        return Optional.empty();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        try {
            if (personRepository.deleteAll()) {
                LOGGER.info(DELETE_SUCCESS);
            }
        } catch (DeletePoorlyException e) {
            LOGGER.error(e.toString());
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(String id) {
        try {
            if (personRepository.deleteById(id)) {
                LOGGER.info(DELETE_BY_ID_SUCCESS);
            }
        } catch (DeletePoorlyException e) {
            LOGGER.error(e.toString());
        }
    }
}
