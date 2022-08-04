package com.example.testproject1.dao.jobtittle;

import com.example.testproject1.dao.jobtittle.mapper.JobTittleMapper;
import com.example.testproject1.exception.DepartmentExistInDb;
import com.example.testproject1.exception.JobTittleExistIndDb;
import com.example.testproject1.model.staff.JobTittle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.testproject1.dao.queryholder.QueryHolder.JOB_TITTLE_CREATE_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.JOB_TITTLE_DELETE_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.JOB_TITTLE_DELETE_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.JOB_TITTLE_GET_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.JOB_TITTLE_GET_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.JOB_TITTLE_UPDATE_ID_QUERY;

/**
 * Класс реализующий интерфейс {@link JobTittleRepository}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository
public class JobTittleRepositoryImpl implements JobTittleRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobTittleRepositoryImpl.class);
    /**
     * Маппер для извлечения {@link JobTittle}
     */
    @Autowired
    private JobTittleMapper jobTittleMapper;
    /**
     * Бин JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<JobTittle> getAll() {
        return jdbcTemplate.query(JOB_TITTLE_GET_ALL_QUERY, jobTittleMapper);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<JobTittle> getById(String uuid) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(JOB_TITTLE_GET_BY_ID_QUERY, jobTittleMapper, uuid));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer create(JobTittle jobTittle) {
        try {
            isNotExistElseThrow(jobTittle);
            return jdbcTemplate.update(JOB_TITTLE_CREATE_QUERY, jobTittle.getUuid().toString()
                    , jobTittle.getName());

        } catch (JobTittleExistIndDb e) {
            LOGGER.info(e.toString());
            return 0;
        }
    }
    /**
     * Метод поиска jobTittle по id. Из-за того, что в XML staff сущностей(Person,Department и т.д.) ограниченное количество, каждый раз ловить
     * {@link org.springframework.dao.DataIntegrityViolationException} и откатывать сохранение очень долго
     * @param jobTittle
     * @throws DepartmentExistInDb если найден JobTittle с переданным id
     */
    public void isNotExistElseThrow(JobTittle jobTittle) throws JobTittleExistIndDb {
        if (existById(jobTittle.getUuid().toString())) {
            throw new JobTittleExistIndDb(jobTittle.getUuid().toString());
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer update(JobTittle jobTittle) {
        return jdbcTemplate.update(JOB_TITTLE_UPDATE_ID_QUERY, jobTittle.getName(), jobTittle.getUuid().toString());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteAll() {
        return jdbcTemplate.update(JOB_TITTLE_DELETE_ALL_QUERY);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteById(String id) {
        int update = jdbcTemplate.update(JOB_TITTLE_DELETE_BY_ID_QUERY, id);
        return update;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(String uuid) {
        Optional<JobTittle> jobTittle = jdbcTemplate.query(JOB_TITTLE_GET_BY_ID_QUERY, jobTittleMapper, uuid).stream().findFirst();
        return jobTittle.isPresent();
    }
}
