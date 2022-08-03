package com.example.testproject1.dao.person;

import com.example.testproject1.dao.baseDocument.BaseDocumentRepositoryImpl;
import com.example.testproject1.dao.department.DepartmentRepository;
import com.example.testproject1.dao.jobTittle.JobTittleRepository;
import com.example.testproject1.dao.person.mapper.PersonMapper;
import com.example.testproject1.exception.BaseDocumentExistInDb;
import com.example.testproject1.exception.PersonExistInDb;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbService.department.DepartmentService;
import com.example.testproject1.service.dbService.jobTittleService.JobTittleService;
import liquibase.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements PersonRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private JobTittleService jobTittleService;
    /**
     * Запрос на создание записи в таблице person
     */
    private final String queryCreate="INSERT INTO person VALUES (?,?,?,?,?,?,?,?,?)";
    /**
     * Запрос на получение всех объектов из таблицы person
     */
    private final String queryGetAll=
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
            "    ON person.job_tittle_id=job_tittle.id "+
            "INNER JOIN organization " +
            "   ON organization.id=department.organization_id";
    /**
     * Запрос на получение объекта по id из таблицы person
     */
    private final String queryGetById=
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
                    "    ON person.job_tittle_id=job_tittle.id "+
                    "INNER JOIN organization " +
                    "   ON organization.id=department.organization_id  WHERE person.id=?";

    /**
     * Запрос на обновление записи в таблице person
     */
    private final String queryUpdate="UPDATE person SET first_name=?, second_name=?, last_name=?," +
            " photo=?, job_tittle_id=?, department_id=?, phone_number=?, birth_day=? WHERE id=?";
    /**
     * Запрос на удаление всех записей в таблице person
     */
    private final String queryDeleteAll="DELETE FROM person";
    /**
     * Запрос на удаление записи по id в таблице person
     */
    private final String queryDeleteById="DELETE FROM person WHERE id=?";

    @Override
    public Integer create(Person person){
        try {
            isNotExistElseThrow(person);
            jobTittleService.create(person.getJobTittle());
            departmentService.create(person.getDepartment());
            return jdbcTemplate.update(queryCreate,person.getId().toString(),person.getFirstName(),person.getSecondName(),
                    person.getLastName(),person.getPhoto(),person.getJobTittle().getUuid().toString(),
                    person.getDepartment().getId().toString(), person.getPhoneNumber(),person.getBirthDay());
        } catch (PersonExistInDb e) {
            LOGGER.info(e.toString());
            return 0;
        }
    }
    public void isNotExistElseThrow(Person person) throws PersonExistInDb {
        if(existById(person.getId().toString())){
            throw new PersonExistInDb(person.getId().toString());
        }
    }
    @Override
    public List<Person> getAll(){
        return jdbcTemplate.query(queryGetAll,personMapper);
    }

    @Override
    public Optional<Person> getById(String id){
        return jdbcTemplate.query(queryGetById, personMapper,id)
                .stream().findFirst();
    }
    @Override
    public Integer update(Person person){
        return jdbcTemplate.update(queryUpdate,person.getFirstName(),person.getSecondName(),
                person.getLastName(),person.getPhoto(),person.getJobTittle().getUuid().toString(),
                person.getDepartment().getId().toString(), person.getPhoneNumber(),person.getBirthDay(),person.getId().toString());
    }
    @Override
    public Integer deleteAll(){
        return jdbcTemplate.update(queryDeleteAll);
    }
    @Override
    public Integer deleteById(String id) {
        int update = jdbcTemplate.update(queryDeleteById, id);
        return update;
    }
    @Override
    public boolean existById(String uuid) {
        Optional<Person> person= jdbcTemplate.query(queryGetById,personMapper,uuid).stream().findFirst();
        return person.isPresent();
    }
}
