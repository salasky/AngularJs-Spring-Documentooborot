package com.example.testproject1.dao.department;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.mapper.staff.DepartmentMapper;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.dbservice.CrudService;
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

import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.DEPARTMENT_CREATE_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.DEPARTMENT_DELETE_ALL_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.DEPARTMENT_DELETE_BY_ID_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.DEPARTMENT_GET_ALL_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.DEPARTMENT_GET_BY_ID_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.DEPARTMENT_UPDATE_QUERY;

/**
 * Класс репозиторий для {@link Department}. Реализует интерфейс {@link CrudRepository}
 *
 * @author smigranov
 */
@Repository("DepartmentRepository")
public class DepartmentRepository implements CrudRepository<Department> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentRepository.class);
    /**
     * Бин JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * Маппер для извлечения {@link Department}
     */
    @Autowired
    private DepartmentMapper departmentMapper;
    /**
     * Сервис для работы с {@link Organization}
     */
    @Autowired
    private CrudService<Organization> organizationService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Department> getAll() {
        return jdbcTemplate.query(DEPARTMENT_GET_ALL_QUERY, departmentMapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Department> getById(UUID id) {
        return jdbcTemplate.query(DEPARTMENT_GET_BY_ID_QUERY, departmentMapper, id.toString()).stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Department create(Department department) {
        if (department == null) {
            throw new DocflowRuntimeApplicationException("Department не может быть null");
        }
        try {
            jdbcTemplate.update(DEPARTMENT_CREATE_QUERY, department.getId().toString()
                    , department.getFullName(), department.getShortName(), department.getSupervisor()
                    , department.getContactNumber(), department.getOrganization().getId().toString());
        } catch (DataAccessException e) {
            throw new DocflowRuntimeApplicationException("Ошибка сохранения", e);
        }
        return department;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Department update(Department department) {
        if (department == null) {
            throw new DocflowRuntimeApplicationException("Department не может быть null");
        }
        try {
            jdbcTemplate.update(DEPARTMENT_UPDATE_QUERY, department.getFullName(), department.getShortName(),
                    department.getSupervisor(), department.getContactNumber(), department.getOrganization().getId().toString(),
                    department.getId().toString());
        } catch (DataAccessException e) {
            throw new DocflowRuntimeApplicationException("Ошибка сохранения", e);
        }
        return department;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        jdbcTemplate.update(DEPARTMENT_DELETE_ALL_QUERY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(UUID id) {
        jdbcTemplate.update(DEPARTMENT_DELETE_BY_ID_QUERY, id.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(UUID uuid) {
        return jdbcTemplate.query(DEPARTMENT_GET_BY_ID_QUERY, departmentMapper, uuid.toString())
                .stream().findFirst().isPresent();
    }

    @Override
    public void saveAll(List<Department> departmentList) throws BatchUpdateException {
        jdbcTemplate.batchUpdate(DEPARTMENT_CREATE_QUERY, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, departmentList.get(i).getId().toString());
                ps.setString(2, departmentList.get(i).getFullName());
                ps.setString(3, departmentList.get(i).getShortName());
                ps.setString(4, departmentList.get(i).getSupervisor());
                ps.setString(5, departmentList.get(i).getContactNumber());
                ps.setString(6, departmentList.get(i).getOrganization().getId().toString());
            }

            @Override
            public int getBatchSize() {
                return departmentList.size();
            }
        });
    }
}
