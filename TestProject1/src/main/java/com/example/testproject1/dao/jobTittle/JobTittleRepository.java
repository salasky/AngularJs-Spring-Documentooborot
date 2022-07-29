package com.example.testproject1.dao.jobTittle;

import com.example.testproject1.dao.jobTittle.mapper.JobTittleMapper;
import com.example.testproject1.dao.organization.mapper.OrganizationMapper;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JobTittleRepository {
    /**
     * Запрос на получение всех объектов из таблицы jobTittle
     */
    private final String queryGetAll="SELECT * FROM job_tittle";
    /**
     * Запрос на получение объекта по id из таблицы jobTittle
     */
    private final String queryGetById="SELECT * FROM job_tittle WHERE id=?";
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


    public List<JobTittle> getAll(){
        return jdbcTemplate.query(queryGetAll, new JobTittleMapper());
    }

    public Optional<JobTittle> getById(String uuid) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(queryGetById, new JobTittleMapper(),uuid));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void create(JobTittle jobTittle){
        jdbcTemplate.update(queryCreate,jobTittle.getUuid().toString()
                ,jobTittle.getName());
    }

    public Integer update(JobTittle jobTittle){
        return jdbcTemplate.update(queryUpdate,jobTittle.getName(),jobTittle.getUuid().toString());
    }

    public void deleteAll(){
        jdbcTemplate.update(queryDeleteAll);
    }

    public Integer deleteById(String id) {
        int update = jdbcTemplate.update(queryDeleteById, id);
        return update;
    }
}
