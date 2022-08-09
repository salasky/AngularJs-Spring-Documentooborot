package com.example.testproject1.dao.jobtittle;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.EntityExistInDataBaseException;
import com.example.testproject1.mapper.staff.JobTittleMapper;
import com.example.testproject1.model.staff.JobTittle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.testproject1.dao.queryholder.QueryHolder.JOB_TITTLE_CREATE_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.JOB_TITTLE_DELETE_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.JOB_TITTLE_DELETE_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.JOB_TITTLE_GET_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.JOB_TITTLE_GET_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.JOB_TITTLE_UPDATE_ID_QUERY;

/**
 * Класс реализующий интерфейс {@link CrudRepository}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository("JobTittleRepository")
public class JobTittleRepositoryImpl implements CrudRepository<JobTittle> {
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
    public JobTittle create(JobTittle jobTittle) {
        if (jobTittle != null) {
            try {
                //This is a temporary solution due to duplicate values in the xml. It is more correct to catch DataIntegrityViolationException,
                // but in this case, database rollbacks along the chain take a long time
                isNotExistElseThrow(jobTittle);
                jdbcTemplate.update(JOB_TITTLE_CREATE_QUERY, jobTittle.getUuid().toString(), jobTittle.getName());
                return jobTittle;
            } catch (EntityExistInDataBaseException e) {
                LOGGER.error(e.toString());
                return null;
            }
        } else throw new IllegalArgumentException("JobTittle не может быть null");
    }

    /**
     * Метод поиска jobTittle по id. Из-за того, что в XML staff сущностей(Person,Department и т.д.) ограниченное количество, каждый раз ловить
     * {@link org.springframework.dao.DataIntegrityViolationException} и откатывать сохранение очень долго
     *
     * @param jobTittle
     * @throws EntityExistInDataBaseException если найден JobTittle с переданным id
     */
    private void isNotExistElseThrow(JobTittle jobTittle) throws EntityExistInDataBaseException {
        if (existById(jobTittle.getUuid())) {
            throw new EntityExistInDataBaseException(
                    MessageFormat.format("JobTittle с id {0} уже существует",jobTittle.getUuid().toString()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(JobTittle jobTittle) {
        return jdbcTemplate.update(JOB_TITTLE_UPDATE_ID_QUERY, jobTittle.getName(), jobTittle.getUuid().toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        jdbcTemplate.update(JOB_TITTLE_DELETE_ALL_QUERY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(String id) {
        int deleteCount = jdbcTemplate.update(JOB_TITTLE_DELETE_BY_ID_QUERY, id);
        if (deleteCount == 1) {
            return true;
        }
        throw new RuntimeException(
                MessageFormat.format("Ошибка удаления JobTittle с id {0}",id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(UUID uuid) {
        return jdbcTemplate.query(JOB_TITTLE_GET_BY_ID_QUERY, jobTittleMapper, uuid.toString())
                .stream().findFirst().isPresent();
    }
}
