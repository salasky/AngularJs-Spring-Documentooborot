package com.example.testproject1.dao.department;

import com.example.testproject1.dao.department.mapper.DepartmentMapper;
import com.example.testproject1.exception.DepartmentExistInDb;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.service.dbService.organization.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private OrganizationService organizationService;
    /**
     * Запрос на получение всех объектов из таблицы department
     */
    private final String queryGetAll = "SELECT department.id AS department_id, department.full_name AS department_full_name," +
            " department.short_name AS department_short_name, department.supervisor AS department_supervisor," +
            " department.contact_number AS department_contact_number, organization.id AS organization_id ," +
            " organization.full_name AS organization_full_name, organization.short_name AS organization_short_name,\n" +
            "organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number\n" +
            "FROM department INNER JOIN organization ON department.organization_id=organization.id";
    /**
     * Запрос на получение объекта по id из таблицы department
     */
    private final String queryGetById = "SELECT department.id AS department_id, department.full_name AS department_full_name," +
            " department.short_name AS department_short_name, department.supervisor AS department_supervisor," +
            " department.contact_number AS department_contact_number, organization.id AS organization_id ," +
            " organization.full_name AS organization_full_name, organization.short_name AS organization_short_name,\n" +
            "organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number\n" +
            "FROM department INNER JOIN organization ON department.organization_id=organization.id WHERE department.id=?";
    /**
     * Запрос на создание записи в таблице department
     */
    private final String queryCreate = "INSERT INTO department VALUES (?,?,?,?,?,?)";
    /**
     * Запрос на удаление всех записей в таблице department
     */
    private final String queryDeleteAll = "DELETE FROM department";
    /**
     * Запрос на удаление записи по id в таблице department
     */
    private final String queryDeleteById = "DELETE FROM department WHERE id=?";
    /**
     * Запрос на обновление записи по id в таблице department
     */
    private final String queryUpdate = "UPDATE department SET full_name=?, short_name=?," +
            " supervisor=?, contact_number=?, organization_id=? WHERE id=?";

    @Override
    public List<Department> getAll() {
        return jdbcTemplate.query(queryGetAll, departmentMapper);
    }

    @Override
    public Optional<Department> getById(String id) {
        return jdbcTemplate.query(queryGetById, departmentMapper, id).stream().findFirst();
    }

    @Override
    public Integer create(Department department) {
        try {
            isNotExistElseThrow(department);
            organizationService.create(department.getOrganization());
            return jdbcTemplate.update(queryCreate, department.getId().toString()
                    , department.getFullName(), department.getShortName(), department.getSupervisor()
                    , department.getContactNumber(), department.getOrganization().getId().toString());
        } catch (DepartmentExistInDb e) {
            LOGGER.info(e.toString());
            return 0;
        }
    }

    public void isNotExistElseThrow(Department department) throws DepartmentExistInDb {
        if (existById(department.getId().toString())) {
            throw new DepartmentExistInDb(department.getId().toString());
        }
    }

    @Override
    public Integer update(Department department) {
        return jdbcTemplate.update(queryUpdate, department.getFullName(), department.getShortName(),
                department.getSupervisor(), department.getContactNumber(), department.getOrganization().getId().toString(),
                department.getId().toString());
    }

    @Override
    public Integer deleteAll() {
        return jdbcTemplate.update(queryDeleteAll);
    }

    @Override
    public Integer deleteById(String id) {
        int update = jdbcTemplate.update(queryDeleteById, id);
        return update;
    }

    @Override
    public boolean existById(String uuid) {
        Optional<Department> department = jdbcTemplate.query(queryGetById, departmentMapper, uuid).stream().findFirst();
        return department.isPresent();
    }
}
