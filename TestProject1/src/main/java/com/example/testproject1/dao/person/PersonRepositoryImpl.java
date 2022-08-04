package com.example.testproject1.dao.person;

import com.example.testproject1.dao.person.mapper.PersonMapper;
import com.example.testproject1.exception.DepartmentExistInDb;
import com.example.testproject1.exception.PersonExistInDb;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbService.department.DepartmentService;
import com.example.testproject1.service.dbService.jobTittleService.JobTittleService;
import com.example.testproject1.model.staff.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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
     * Запрос на создание записи в таблице person
     */
    private final String queryCreate = "INSERT INTO person VALUES (?,?,?,?,?,?,?,?,?)";
    /**
     * Запрос на получение всех объектов из таблицы person
     */
    private final String queryGetAll =
            "SELECT  person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name," +
                    "person.last_name AS person_last_name, person.photo AS person_photo, person.phone_number AS person_phone_number," +
                    "person.birth_day AS person_birth_day," +
                    "department.id AS department_id, department.full_name AS department_full_name," +
                    "department.short_name AS department_short_name, department.supervisor AS department_supervisor," +
                    "department.contact_number AS department_contact_number, organization.id AS organization_id ," +
                    "organization.full_name AS organization_full_name, organization.short_name AS organization_short_name," +
                    "organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number, " +
                    "job_tittle.id AS job_tittle_id, job_tittle.name AS job_name  " +
                    "FROM person " +
                    "INNER JOIN department " +
                    "    ON person.department_id=department_id " +
                    "INNER JOIN job_tittle " +
                    "    ON person.job_tittle_id=job_tittle.id " +
                    "INNER JOIN organization " +
                    "   ON organization.id=department.organization_id";
    /**
     * Запрос на получение объекта по id из таблицы person
     */
    private final String queryGetById =
            "SELECT  person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name," +
                    "person.last_name AS person_last_name, person.photo AS person_photo, person.phone_number AS person_phone_number," +
                    "person.birth_day AS person_birth_day," +
                    "department.id AS department_id, department.full_name AS department_full_name," +
                    "department.short_name AS department_short_name, department.supervisor AS department_supervisor," +
                    "department.contact_number AS department_contact_number, organization.id AS organization_id ," +
                    "organization.full_name AS organization_full_name, organization.short_name AS organization_short_name," +
                    "organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number, " +
                    "job_tittle.id AS job_tittle_id, job_tittle.name AS job_name  " +
                    "FROM person " +
                    "INNER JOIN department " +
                    "    ON person.department_id=department_id " +
                    "INNER JOIN job_tittle " +
                    "    ON person.job_tittle_id=job_tittle.id " +
                    "INNER JOIN organization " +
                    "   ON organization.id=department.organization_id  WHERE person.id=?";

    /**
     * Запрос на обновление записи в таблице person
     */
    private final String queryUpdate = "UPDATE person SET first_name=?, second_name=?, last_name=?," +
            " photo=?, job_tittle_id=?, department_id=?, phone_number=?, birth_day=? WHERE id=?";
    /**
     * Запрос на удаление всех записей в таблице person
     */
    private final String queryDeleteAll = "DELETE FROM person";
    /**
     * Запрос на удаление записи по id в таблице person
     */
    private final String queryDeleteById = "DELETE FROM person WHERE id=?";

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer create(Person person) {
        try {
            isNotExistElseThrow(person);
            jobTittleService.create(person.getJobTittle());
            departmentService.create(person.getDepartment());
            return jdbcTemplate.update(queryCreate, person.getId().toString(), person.getFirstName(), person.getSecondName(),
                    person.getLastName(), person.getPhoto(), person.getJobTittle().getUuid().toString(),
                    person.getDepartment().getId().toString(), person.getPhoneNumber(), person.getBirthDay());
        } catch (PersonExistInDb e) {
            LOGGER.info(e.toString());
            return 0;
        }
    }
    /**
     * Метод поиска Person по id. Из-за того, что в XML staff сущностей(Person,Department и т.д.) ограниченное количество, каждый раз ловить
     * {@link org.springframework.dao.DataIntegrityViolationException} и откатывать сохранение очень долго
     * @param person
     * @throws DepartmentExistInDb если найден Person с переданным id
     */
    public void isNotExistElseThrow(Person person) throws PersonExistInDb {
        if (existById(person.getId().toString())) {
            throw new PersonExistInDb(person.getId().toString());
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query(queryGetAll, personMapper);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Person> getById(String id) {
        return jdbcTemplate.query(queryGetById, personMapper, id)
                .stream().findFirst();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer update(Person person) {
        return jdbcTemplate.update(queryUpdate, person.getFirstName(), person.getSecondName(),
                person.getLastName(), person.getPhoto(), person.getJobTittle().getUuid().toString(),
                person.getDepartment().getId().toString(), person.getPhoneNumber(), person.getBirthDay(), person.getId().toString());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteAll() {
        return jdbcTemplate.update(queryDeleteAll);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteById(String id) {
        int update = jdbcTemplate.update(queryDeleteById, id);
        return update;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(String uuid) {
        Optional<Person> person = jdbcTemplate.query(queryGetById, personMapper, uuid).stream().findFirst();
        return person.isPresent();
    }
}
