package com.example.testproject1.dao.organization;

import com.example.testproject1.dao.CrudRepositories;
import com.example.testproject1.mapper.staff.OrganizationMapper;
import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.exception.DepartmentExistInDataBaseException;
import com.example.testproject1.exception.OrganizationExistInDataBaseException;
import com.example.testproject1.model.staff.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.testproject1.dao.queryholder.QueryHolder.ORGANIZATION_CREATE_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.ORGANIZATION_DELETE_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.ORGANIZATION_DELETE_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.ORGANIZATION_GET_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.ORGANIZATION_GET_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.ORGANIZATION_UPDATE_QUERY;

/**
 * Класс реализующий интерфейс {@link CrudRepositories}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository("OrganizationRepository")
public class OrganizationRepositoryImpl implements CrudRepositories<Organization> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationRepositoryImpl.class);
    /**
     * Бин JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * Маппер для извлечения {@link Organization}
     */
    @Autowired
    private OrganizationMapper organizationMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Organization> create(Organization organization) {
        if(organization!=null) {
            try {
                isNotExistElseThrow(organization);
                int countCreate=jdbcTemplate.update(ORGANIZATION_CREATE_QUERY, organization.getId().toString()
                        , organization.getFullName(), organization.getShortName(), organization.getSupervisor(), organization.getContactNumber());

                if(countCreate==1){
                    return Optional.ofNullable(organization);
                }
                return Optional.empty();
            } catch (OrganizationExistInDataBaseException e) {
                LOGGER.info(e.toString());
                return Optional.empty();
            }
        }
        else throw new IllegalArgumentException("Organization не может быть null");
    }
    /**
     * Метод поиска Organization по id. Из-за того, что в XML staff сущностей(Person,Department и т.д.) ограниченное количество, каждый раз ловить
     * {@link org.springframework.dao.DataIntegrityViolationException} и откатывать сохранение очень долго
     * @param organization
     * @throws DepartmentExistInDataBaseException если найден Organization с переданным id
     */
    private void isNotExistElseThrow(Organization organization) throws OrganizationExistInDataBaseException {
        if (existById(organization.getId().toString())) {
            throw new OrganizationExistInDataBaseException(organization.getId().toString());
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer update(Organization organization) {
        return jdbcTemplate.update(ORGANIZATION_UPDATE_QUERY, organization.getFullName(), organization.getShortName(),
                organization.getSupervisor(), organization.getContactNumber(), organization.getId().toString());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> getAll() {
        return jdbcTemplate.query(ORGANIZATION_GET_ALL_QUERY, organizationMapper);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Organization> getById(String uuid) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(ORGANIZATION_GET_BY_ID_QUERY, organizationMapper, uuid));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAll() throws DeletePoorlyException {
        int deleteCount= jdbcTemplate.update(ORGANIZATION_DELETE_ALL_QUERY);
        if(deleteCount>0) {
            return true;
        }
        throw new DeletePoorlyException();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(String id) throws DeletePoorlyException {
        int deleteCount = jdbcTemplate.update(ORGANIZATION_DELETE_BY_ID_QUERY, id);
        if(deleteCount==1) {
            return true;
        }
        throw new DeletePoorlyException();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(String uuid) {
        Optional<Organization> organization = jdbcTemplate.query(ORGANIZATION_GET_BY_ID_QUERY, organizationMapper, uuid).stream().findFirst();
        return organization.isPresent();
    }
}
