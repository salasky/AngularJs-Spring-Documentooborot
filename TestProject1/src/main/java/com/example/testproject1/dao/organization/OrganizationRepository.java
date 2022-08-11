package com.example.testproject1.dao.organization;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.mapper.staff.OrganizationMapper;
import com.example.testproject1.model.staff.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.testproject1.queryholder.outgoingdocumentquery.OutgoingDocumentQueryHolder.OUTGOING_DOCUMENT_GET_BY_ID_QUERY;
import static com.example.testproject1.queryholder.staffqueryholder.StaffQueryHolder.JOB_TITTLE_UPDATE_ID_QUERY;
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
    public Organization create(Organization organization) throws DocflowRuntimeApplicationException {
        if (organization == null) {
            throw new DocflowRuntimeApplicationException("Organization не может быть null");
        }
        jdbcTemplate.update(ORGANIZATION_CREATE_QUERY, organization.getId().toString()
                , organization.getFullName(), organization.getShortName(), organization.getSupervisor(), organization.getContactNumber());
        return organization;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization update(Organization organization) throws DocflowRuntimeApplicationException {
        if (organization == null) {
            throw new DocflowRuntimeApplicationException("Organization не может быть null");
        }
        jdbcTemplate.update(ORGANIZATION_UPDATE_QUERY, organization.getFullName(), organization.getShortName(),
                organization.getSupervisor(), organization.getContactNumber(), organization.getId().toString());
        return organization;
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
        return jdbcTemplate.query(ORGANIZATION_GET_BY_ID_QUERY, organizationMapper, uuid).stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        jdbcTemplate.update(ORGANIZATION_DELETE_ALL_QUERY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(String id) {
        jdbcTemplate.update(ORGANIZATION_DELETE_BY_ID_QUERY, id);
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
