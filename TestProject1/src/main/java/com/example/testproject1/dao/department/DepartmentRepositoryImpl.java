package com.example.testproject1.dao.department;

import com.example.testproject1.dao.department.mapper.DepartmentMapper;
import com.example.testproject1.exception.DepartmentExistInDb;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.dbservice.organization.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.testproject1.dao.queryholder.QueryHolder.DEPARTMENT_CREATE_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.DEPARTMENT_DELETE_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.DEPARTMENT_DELETE_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.DEPARTMENT_GET_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.DEPARTMENT_GET_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.DEPARTMENT_UPDATE_QUERY;

/**
 * Класс репозиторий для {@link Department}. Реализует интерфейс {@link DepartmentRepository}
 *
 * @author smigranov
 */
@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentRepositoryImpl.class);
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
    private OrganizationService organizationService;

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
    public Optional<Department> getById(String id) {
        return jdbcTemplate.query(DEPARTMENT_GET_BY_ID_QUERY, departmentMapper, id).stream().findFirst();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer create(Department department) {
        try {
            isNotExistElseThrow(department);
            organizationService.create(department.getOrganization());
            return jdbcTemplate.update(DEPARTMENT_CREATE_QUERY, department.getId().toString()
                    , department.getFullName(), department.getShortName(), department.getSupervisor()
                    , department.getContactNumber(), department.getOrganization().getId().toString());
        } catch (DepartmentExistInDb e) {
            LOGGER.info(e.toString());
            return 0;
        }
    }

    /**
     * Метод поиска Department по id. Из-за того, что в XML staff сущностей(Person,Department и т.д.) ограниченное количество, каждый раз ловить
     * {@link org.springframework.dao.DataIntegrityViolationException} и откатывать сохранение очень долго
     * @param department
     * @throws DepartmentExistInDb если найден Department с переданным id
     */
    private void isNotExistElseThrow(Department department) throws DepartmentExistInDb {
        if (existById(department.getId().toString())) {
            throw new DepartmentExistInDb(department.getId().toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer update(Department department) {
        return jdbcTemplate.update(DEPARTMENT_UPDATE_QUERY, department.getFullName(), department.getShortName(),
                department.getSupervisor(), department.getContactNumber(), department.getOrganization().getId().toString(),
                department.getId().toString());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteAll() {
        return jdbcTemplate.update(DEPARTMENT_DELETE_ALL_QUERY);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteById(String id) {
        int update = jdbcTemplate.update(DEPARTMENT_DELETE_BY_ID_QUERY, id);
        return update;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(String uuid) {
        Optional<Department> department = jdbcTemplate.query(DEPARTMENT_GET_BY_ID_QUERY, departmentMapper, uuid).stream().findFirst();
        return department.isPresent();
    }
}
