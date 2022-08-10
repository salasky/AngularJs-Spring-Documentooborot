package com.example.testproject1.dao.organization;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.exception.EntityExistInDataBaseException;
import com.example.testproject1.mapper.staff.OrganizationMapper;
import com.example.testproject1.model.staff.Organization;
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

import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.ORGANIZATION_CREATE_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.ORGANIZATION_DELETE_ALL_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.ORGANIZATION_DELETE_BY_ID_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.ORGANIZATION_GET_ALL_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.ORGANIZATION_GET_BY_ID_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.ORGANIZATION_UPDATE_QUERY;

/**
 * Класс реализующий интерфейс {@link CrudRepository}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository("OrganizationRepository")
public class OrganizationRepository implements CrudRepository<Organization> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationRepository.class);
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
    public Organization create(Organization organization) {
        if (organization != null) {
                jdbcTemplate.update(ORGANIZATION_CREATE_QUERY, organization.getId().toString()
                        , organization.getFullName(), organization.getShortName(), organization.getSupervisor(), organization.getContactNumber());
                return organization;
        } else {
            throw new IllegalArgumentException("Organization не может быть null");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(Organization organization) {
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
    public void deleteAll(){
        jdbcTemplate.update(ORGANIZATION_DELETE_ALL_QUERY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(String id) throws DeleteByIdException {
        int deleteCount = jdbcTemplate.update(ORGANIZATION_DELETE_BY_ID_QUERY, id);
        if (deleteCount == 1) {
            return true;
        }
        throw new DeleteByIdException("Organization");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existById(UUID uuid) {
        return jdbcTemplate.query(ORGANIZATION_GET_BY_ID_QUERY, organizationMapper, uuid.toString())
                .stream().findFirst().isPresent();
    }
}
