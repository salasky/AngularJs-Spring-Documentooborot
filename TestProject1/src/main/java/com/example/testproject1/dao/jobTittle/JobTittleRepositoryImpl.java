package com.example.testproject1.dao.jobTittle;

import com.example.testproject1.dao.jobTittle.mapper.JobTittleMapper;
import com.example.testproject1.dao.person.PersonRepositoryImpl;
import com.example.testproject1.exception.DocumentExistInDb;
import com.example.testproject1.exception.JobTittleExistIndDb;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JobTittleRepositoryImpl implements JobTittleRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobTittleRepositoryImpl.class);
    @Autowired
    private JobTittleMapper jobTittleMapper;
    /**
     * Запрос на получение всех объектов из таблицы jobTittle
     */
    private final String queryGetAll="SELECT job_tittle.id AS job_tittle_id," +
            " job_tittle.name AS job_name" +
            " FROM job_tittle";
    /**
     * Запрос на получение объекта по id из таблицы jobTittle
     */
    private final String queryGetById="SELECT job_tittle.id AS job_tittle_id," +
            " job_tittle.name AS job_name" +
            " FROM job_tittle WHERE id=?";
    /**
     * Запрос на создание записи в таблице jobTittle
     */
    private final String queryCreate="INSERT INTO job_tittle VALUES (?,?)";
    /**
     * Запрос на удаление всех записей в таблице jobTittle
     */
    private final String queryDeleteAll="DELETE FROM job_tittle";
    /**
     * Запрос на удаление записи по id в таблице jobTittle
     */
    private final String queryDeleteById="DELETE FROM job_tittle WHERE id=?";
    /**
     * Запрос на обновление записи по id в таблице jobTittle
     */
    private final String queryUpdate="UPDATE job_tittle SET name=? WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<JobTittle> getAll(){
        return jdbcTemplate.query(queryGetAll, jobTittleMapper);
    }

    @Override
    public Optional<JobTittle> getById(String uuid) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(queryGetById,jobTittleMapper,uuid));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    @Override
    public Integer create(JobTittle jobTittle){
        try {
            isNotExistElseThrow(jobTittle);
            return jdbcTemplate.update(queryCreate,jobTittle.getUuid().toString()
                    ,jobTittle.getName());

        } catch (JobTittleExistIndDb e) {
            LOGGER.info(e.toString());
            return 0;
        }

    }
    public void isNotExistElseThrow(JobTittle jobTittle) throws JobTittleExistIndDb {
        if(existById(jobTittle.getUuid().toString())){
            throw new JobTittleExistIndDb(jobTittle.getUuid().toString());
        }
    }
    @Override
    public Integer update(JobTittle jobTittle){
        return jdbcTemplate.update(queryUpdate,jobTittle.getName(),jobTittle.getUuid().toString());
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
        Optional<JobTittle> jobTittle= jdbcTemplate.query(queryGetById,jobTittleMapper,uuid).stream().findFirst();
        return jobTittle.isPresent();
    }
}
