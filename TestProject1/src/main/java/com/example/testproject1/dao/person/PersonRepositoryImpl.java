package com.example.testproject1.dao.person;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.exception.DepartmentExistInDataBaseException;
import com.example.testproject1.exception.PersonExistInDataBaseException;
import com.example.testproject1.mapper.staff.PersonMapper;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.testproject1.dao.queryholder.QueryHolder.PERSON_CREATE_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.PERSON_DELETE_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.PERSON_DELETE_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.PERSON_GET_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.PERSON_GET_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.PERSON_UPDATE_QUERY;

/**
 * Класс реализующий интерфейс {@link CrudRepository}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository("PersonRepository")
public class PersonRepositoryImpl implements CrudRepository<Person> {
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
    private CrudService<Department> departmentService;
    /**
     * Сервис для работы с {@link Department}
     */
    @Autowired
    private CrudService<JobTittle> jobTittleService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Person> create(Person person) {
        if (person != null) {
            try {
                isNotExistElseThrow(person);
                jobTittleService.create(person.getJobTittle());
                departmentService.create(person.getDepartment());
                int countCreate = jdbcTemplate.update(PERSON_CREATE_QUERY, person.getId().toString(), person.getFirstName(), person.getSecondName(),
                        person.getLastName(), person.getPhoto(), person.getJobTittle().getUuid().toString(),
                        person.getDepartment().getId().toString(), person.getPhoneNumber(), person.getBirthDay());
                if (countCreate == 1) {
                    return Optional.ofNullable(person);
                }
                return Optional.empty();
            } catch (PersonExistInDataBaseException e) {
                LOGGER.info(e.toString());
                return Optional.empty();
            }
        } else throw new IllegalArgumentException("Person не может быть null");
    }

    /**
     * Метод поиска Person по id. Из-за того, что в XML staff сущностей(Person,Department и т.д.) ограниченное количество, каждый раз ловить
     * {@link org.springframework.dao.DataIntegrityViolationException} и откатывать сохранение очень долго
     *
     * @param person
     * @throws DepartmentExistInDataBaseException если найден Person с переданным id
     */
    private void isNotExistElseThrow(Person person) throws PersonExistInDataBaseException {
        if (existById(person.getId())) {
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
    public int update(Person person) {
        return jdbcTemplate.update(PERSON_UPDATE_QUERY, person.getFirstName(), person.getSecondName(),
                person.getLastName(), person.getPhoto(), person.getJobTittle().getUuid().toString(),
                person.getDepartment().getId().toString(), person.getPhoneNumber(), person.getBirthDay(), person.getId().toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAll() throws DeletePoorlyException {
        int deleteCount = jdbcTemplate.update(PERSON_DELETE_ALL_QUERY);
        if (deleteCount > 0) {
            return true;
        }
        throw new DeletePoorlyException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(String id) throws DeletePoorlyException {
        int deleteCount = jdbcTemplate.update(PERSON_DELETE_BY_ID_QUERY, id);
        if (deleteCount == 1) {
            return true;
        }
        throw new DeletePoorlyException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(UUID uuid) {
        return jdbcTemplate.query(PERSON_GET_BY_ID_QUERY, personMapper, uuid.toString())
                .stream().findFirst().isPresent();
    }
}
