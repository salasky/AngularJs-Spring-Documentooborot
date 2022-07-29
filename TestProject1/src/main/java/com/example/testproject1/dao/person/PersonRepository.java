package com.example.testproject1.dao.person;

import com.example.testproject1.dao.department.mapper.DepartmentMapper;
import com.example.testproject1.dao.person.mapper.PersonMapper;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {
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
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(Person person){
        jdbcTemplate.update(queryCreate,person.getId().toString(),person.getFirstName(),person.getSecondName(),
                person.getLastName(),person.getPhoto(),person.getJobTittle().getUuid().toString(),
                person.getDepartment().getId().toString(), person.getPhoneNumber(),person.getBirthDay());
    }
    public List<Person> getAll(){
        return jdbcTemplate.query(queryGetAll,new PersonMapper());
    }

    public Optional<Person> getById(String id){
        return jdbcTemplate.query(queryGetById, new PersonMapper(),id).stream().findFirst();
    }
}
