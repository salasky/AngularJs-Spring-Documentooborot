package com.example.testproject1.dao.organization;

import com.example.testproject1.dao.organization.mapper.OrganizationMapper;
import com.example.testproject1.model.staff.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Класс репозиторий для организаций
 *
 * @author smigranov
 */
@Repository
public class OrganizationRepositoryImpl implements OrganizationRepository{
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

    /**
     * Запрос на обновление записи по id в таблице organization
     */
    private final String queryUpdate="UPDATE organization SET full_name=?, short_name=?, supervisor=?, contact_number=? WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public Integer create(Organization organization){
        return jdbcTemplate.update(queryCreate,organization.getId().toString()
                ,organization.getFullName(),organization.getShortName(),organization.getSupervisor(),organization.getContactNumber());
    }
    @Override
    public Integer update(Organization organization){
        return jdbcTemplate.update(queryUpdate,organization.getFullName(),organization.getShortName(),
                organization.getSupervisor(),organization.getContactNumber(),organization.getId().toString());
    }
    @Override
    public List<Organization> getAll(){
        return jdbcTemplate.query(queryGetAll, organizationMapper);
    }
    @Override
    public Optional<Organization> getById(String uuid) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(queryGetById, organizationMapper,uuid));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
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
}
