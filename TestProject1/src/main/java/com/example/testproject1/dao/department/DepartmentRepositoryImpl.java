package com.example.testproject1.dao.department;

import com.example.testproject1.dao.CrudRepositories;
import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.exception.DepartmentExistInDataBaseException;
import com.example.testproject1.mapper.staff.DepartmentMapper;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
 * Класс репозиторий для {@link Department}. Реализует интерфейс {@link CrudRepositories}
 *
 * @author smigranov
 */
@Repository("DepartmentRepository")
public class DepartmentRepositoryImpl implements CrudRepositories<Department> {
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
    @Qualifier("OrganizationService")
    private CrudService organizationService;

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
    public Optional<Department> create(Department department) {
        if (department != null) {
            try {
                isNotExistElseThrow(department);
                organizationService.create(department.getOrganization());
                int countCreate = jdbcTemplate.update(DEPARTMENT_CREATE_QUERY, department.getId().toString()
                        , department.getFullName(), department.getShortName(), department.getSupervisor()
                        , department.getContactNumber(), department.getOrganization().getId().toString());
                if (countCreate == 1) {
                    return Optional.ofNullable(department);
                }
                return Optional.empty();
            } catch (DepartmentExistInDataBaseException e) {
                LOGGER.error(e.toString());
                return Optional.empty();
            }
        } else throw new IllegalArgumentException("Department не может быть null");
    }

    /**
     * Метод поиска Department по id. Из-за того, что в XML staff сущностей(Person,Department и т.д.) ограниченное количество, каждый раз ловить
     * {@link org.springframework.dao.DataIntegrityViolationException} и откатывать сохранение очень долго
     *
     * @param department
     * @throws DepartmentExistInDataBaseException если найден Department с переданным id
     */
    private void isNotExistElseThrow(Department department) throws DepartmentExistInDataBaseException {
        if (existById(department.getId().toString())) {
            throw new DepartmentExistInDataBaseException(department.getId().toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(Department department) {
        return jdbcTemplate.update(DEPARTMENT_UPDATE_QUERY, department.getFullName(), department.getShortName(),
                department.getSupervisor(), department.getContactNumber(), department.getOrganization().getId().toString(),
                department.getId().toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAll() throws DeletePoorlyException {
        int deleteCount = jdbcTemplate.update(DEPARTMENT_DELETE_ALL_QUERY);
        if (deleteCount > 0) {
            return true;
        }
        throw new DeletePoorlyException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(String id) throws DeletePoorlyException {
        int deleteCount = jdbcTemplate.update(DEPARTMENT_DELETE_BY_ID_QUERY, id);
        if (deleteCount == 1) {
            return true;
        }
        throw new DeletePoorlyException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(String uuid) {
        return jdbcTemplate.query(DEPARTMENT_GET_BY_ID_QUERY, departmentMapper, uuid)
                .stream().findFirst().isPresent();
    }
}
