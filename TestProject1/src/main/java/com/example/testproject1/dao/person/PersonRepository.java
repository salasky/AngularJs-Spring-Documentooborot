package com.example.testproject1.dao.person;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
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

import static com.example.testproject1.queryholder.outgoingdocumentquery.OutgoingDocumentQueryHolder.OUTGOING_DOCUMENT_UPDATE_QUERY;
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
    public Person create(Person person) throws DocflowRuntimeApplicationException {
        if (person == null) {
            throw new DocflowRuntimeApplicationException("Person не может быть null");
        }
        jdbcTemplate.update(PERSON_CREATE_QUERY, person.getId().toString(), person.getFirstName(), person.getSecondName(),
                person.getLastName(), person.getPhoto(), person.getJobTittle().getUuid().toString(),
                person.getDepartment().getId().toString(), person.getPhoneNumber(), person.getBirthDay());
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
    public Optional<Person> getById(String id) {
        return jdbcTemplate.query(PERSON_GET_BY_ID_QUERY, personMapper, id)
                .stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person update(Person person) throws DocflowRuntimeApplicationException {
        if (person == null) {
            throw new DocflowRuntimeApplicationException("Person не может быть null");

        }
        jdbcTemplate.update(PERSON_UPDATE_QUERY, person.getFirstName(), person.getSecondName(),
                person.getLastName(), person.getPhoto(), person.getJobTittle().getUuid().toString(),
                person.getDepartment().getId().toString(), person.getPhoneNumber(), person.getBirthDay(), person.getId().toString());
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
    public void deleteById(String id) {
        jdbcTemplate.update(PERSON_DELETE_BY_ID_QUERY, id);
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
