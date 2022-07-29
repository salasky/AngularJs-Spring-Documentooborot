package com.example.testproject1.dao.organization;

import com.example.testproject1.dao.organization.mapper.OrganizationMapper;
import com.example.testproject1.model.staff.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Класс репозиторий для организаций
 *
 * @author smigranov
 */
@Repository
public class OrganizationRepository {
    /**
     * Запрос на получение всех объектов из таблицы organization
     */
    private final String queryGetAll="SELECT * FROM organization";
    /**
     * Запрос на получение объекта по id из таблицы organization
     */
    private final String queryGetById="SELECT * FROM organization WHERE id=?";
    /**
     * Запрос на создание записи в таблице organization
     */
    private final String queryCreate="INSERT INTO organization VALUES (?,?,?,?,?)";
    /**
     * Запрос на удаление всех записей в таблице organization
     */
    private final String queryDeleteAll="DELETE FROM organization";
    /**
     * Запрос на удаление записи по id в таблице organization
     */
    private final String queryDeleteById="DELETE FROM organization WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Organization> getAll(){
        return jdbcTemplate.query(queryGetAll, new OrganizationMapper());
    }

    public Optional<Organization> getById(String uuid) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(queryGetById, new OrganizationMapper(),uuid));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void create(Organization organization){
        jdbcTemplate.update(queryCreate,organization.getId().toString()
        ,organization.getFullName(),organization.getShortName(),organization.getSupervisor(),organization.getContactNumber());
    }

    public void deleteAll(){
        jdbcTemplate.update(queryDeleteAll);
    }

    public Integer deleteById(String id) {
        int update = jdbcTemplate.update(queryDeleteById, id);
        return update;
    }
}
