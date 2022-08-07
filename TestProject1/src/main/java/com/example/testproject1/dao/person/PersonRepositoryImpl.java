package com.example.testproject1.dao.person;

import com.example.testproject1.dao.person.mapper.PersonMapper;
import com.example.testproject1.exception.DepartmentExistInDataBaseException;
import com.example.testproject1.exception.PersonExistInDataBaseException;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.department.DepartmentService;
import com.example.testproject1.service.dbservice.jobtittleservice.JobTittleService;
import com.example.testproject1.model.staff.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.testproject1.dao.queryholder.QueryHolder.PERSON_CREATE_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.PERSON_DELETE_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.PERSON_DELETE_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.PERSON_GET_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.PERSON_GET_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.PERSON_UPDATE_QUERY;

/**
 * Класс реализующий интерфейс {@link PersonRepository}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository
public class PersonRepositoryImpl implements PersonRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepositoryImpl.class);
    /**
     * Бин JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * Маппер для извлечения {@link Person}
     */
    @Autowired
    private PersonMapper personMapper;
    /**
     * Сервис для работы с {@link Department}
     */
    @Autowired
    private DepartmentService departmentService;
    /**
     * Сервис для работы с {@link Department}
     */
    @Autowired
    private JobTittleService jobTittleService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Person> create(Person person) {
        if(person!=null) {
            try {
                isNotExistElseThrow(person);
                jobTittleService.create(person.getJobTittle());
                departmentService.create(person.getDepartment());
                int countCreate=jdbcTemplate.update(PERSON_CREATE_QUERY, person.getId().toString(), person.getFirstName(), person.getSecondName(),
                        person.getLastName(), person.getPhoto(), person.getJobTittle().getUuid().toString(),
                        person.getDepartment().getId().toString(), person.getPhoneNumber(), person.getBirthDay());
                if(countCreate==1){
                    return Optional.ofNullable(person);
                }
                return Optional.empty();
            } catch (PersonExistInDataBaseException e) {
                LOGGER.info(e.toString());
                return Optional.empty();
            }
        }
        else throw new IllegalArgumentException("Person не может быть null");
    }
    /**
     * Метод поиска Person по id. Из-за того, что в XML staff сущностей(Person,Department и т.д.) ограниченное количество, каждый раз ловить
     * {@link org.springframework.dao.DataIntegrityViolationException} и откатывать сохранение очень долго
     * @param person
     * @throws DepartmentExistInDataBaseException если найден Person с переданным id
     */
    private void isNotExistElseThrow(Person person) throws PersonExistInDataBaseException {
        if (existById(person.getId().toString())) {
            throw new PersonExistInDataBaseException(person.getId().toString());
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query(PERSON_GET_ALL_QUERY, personMapper);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Person> getById(String id) {
        return jdbcTemplate.query(PERSON_GET_BY_ID_QUERY, personMapper, id)
                .stream().findFirst();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer update(Person person) {
        return jdbcTemplate.update(PERSON_UPDATE_QUERY, person.getFirstName(), person.getSecondName(),
                person.getLastName(), person.getPhoto(), person.getJobTittle().getUuid().toString(),
                person.getDepartment().getId().toString(), person.getPhoneNumber(), person.getBirthDay(), person.getId().toString());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteAll() {
        return jdbcTemplate.update(PERSON_DELETE_ALL_QUERY);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteById(String id) {
        int update = jdbcTemplate.update(PERSON_DELETE_BY_ID_QUERY, id);
        return update;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(String uuid) {
        Optional<Person> person = jdbcTemplate.query(PERSON_GET_BY_ID_QUERY, personMapper, uuid).stream().findFirst();
        return person.isPresent();
    }
}
