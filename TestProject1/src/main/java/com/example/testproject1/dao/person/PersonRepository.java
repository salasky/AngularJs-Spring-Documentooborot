package com.example.testproject1.dao.person;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.sqlmapper.staff.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.BatchUpdateException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.PERSON_CREATE_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.PERSON_DELETE_ALL_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.PERSON_DELETE_BY_ID_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.PERSON_GET_ALL_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.PERSON_GET_BY_ID_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.PERSON_UPDATE_QUERY;

/**
 * Класс реализующий интерфейс {@link CrudRepository}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository("PersonRepository")
public class PersonRepository implements CrudRepository<Person> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private CrudService<Department> departmentService;

    @Autowired
    private CrudService<JobTittle> jobTittleService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Person create(Person person) {
        if (person == null) {
            throw new DocflowRuntimeApplicationException("Person не может быть null");
        }
        try {
            jdbcTemplate.update(PERSON_CREATE_QUERY, person.getId().toString(), person.getFirstName(), person.getSecondName(),
                    person.getLastName(), person.getPhoto(), person.getJobTittle().getId().toString(),
                    person.getDepartment().getId().toString(), person.getPhoneNumber(), person.getBirthDay());
        } catch (DataAccessException e) {
            throw new DocflowRuntimeApplicationException("Ошибка сохранения Person", e);
        }
        return person;
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
    public Optional<Person> getById(UUID id) {
        return jdbcTemplate.query(PERSON_GET_BY_ID_QUERY, personMapper, id.toString())
                .stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person update(Person person) {
        if (person == null) {
            throw new DocflowRuntimeApplicationException("Person не может быть null");
        }
        try {
            jdbcTemplate.update(PERSON_UPDATE_QUERY, person.getFirstName(), person.getSecondName(),
                    person.getLastName(), person.getPhoto(), person.getJobTittle().getId().toString(),
                    person.getDepartment().getId().toString(), person.getPhoneNumber(), person.getBirthDay(), person.getId().toString());
        } catch (DataAccessException e) {
            throw new DocflowRuntimeApplicationException("Ошибка сохранения Person", e);
        }
        return person;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        jdbcTemplate.update(PERSON_DELETE_ALL_QUERY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(UUID id) {
        jdbcTemplate.update(PERSON_DELETE_BY_ID_QUERY, id.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(UUID uuid) {
        return jdbcTemplate.query(PERSON_GET_BY_ID_QUERY, personMapper, uuid.toString())
                .stream().findFirst().isPresent();
    }

    @Override
    public void saveAll(List<Person> entityList) throws BatchUpdateException {
        jdbcTemplate.batchUpdate(PERSON_CREATE_QUERY, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, entityList.get(i).getId().toString());
                ps.setString(2, entityList.get(i).getFirstName());
                ps.setString(3, entityList.get(i).getSecondName());
                ps.setString(4, entityList.get(i).getLastName());
                ps.setString(5, entityList.get(i).getPhoto());
                ps.setString(6, entityList.get(i).getJobTittle().getId().toString());
                ps.setString(7, entityList.get(i).getDepartment().getId().toString());
                ps.setString(8, entityList.get(i).getPhoneNumber());
                ps.setDate(9, entityList.get(i).getBirthDay());
            }

            @Override
            public int getBatchSize() {
                return entityList.size();
            }
        });
    }
}
