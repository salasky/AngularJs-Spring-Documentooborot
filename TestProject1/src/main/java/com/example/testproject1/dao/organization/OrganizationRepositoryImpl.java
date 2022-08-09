package com.example.testproject1.dao.organization;

import com.example.testproject1.dao.CrudRepository;
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

import static com.example.testproject1.dao.queryholder.QueryHolder.ORGANIZATION_CREATE_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.ORGANIZATION_DELETE_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.ORGANIZATION_DELETE_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.ORGANIZATION_GET_ALL_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.ORGANIZATION_GET_BY_ID_QUERY;
import static com.example.testproject1.dao.queryholder.QueryHolder.ORGANIZATION_UPDATE_QUERY;

/**
 * Класс реализующий интерфейс {@link CrudRepository}. Для выполнения операций с базой данных.
 *
 * @author smigranov
 */
@Repository("OrganizationRepository")
public class OrganizationRepositoryImpl implements CrudRepository<Organization> {
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
    public Organization create(Organization organization) {
        if (organization != null) {
            try {
                //This is a temporary solution due to duplicate values in the xml. It is more correct to catch DataIntegrityViolationException,
                // but in this case, database rollbacks along the chain take a long time
                isNotExistElseThrow(organization);
                jdbcTemplate.update(ORGANIZATION_CREATE_QUERY, organization.getId().toString()
                        , organization.getFullName(), organization.getShortName(), organization.getSupervisor(), organization.getContactNumber());
                return organization;
            } catch (EntityExistInDataBaseException e) {
                LOGGER.info(e.toString());
                return null;
            }
        } else throw new IllegalArgumentException("Organization не может быть null");
    }

    /**
     * Метод поиска Organization по id. Из-за того, что в XML staff сущностей(Person,Department и т.д.) ограниченное количество, каждый раз ловить
     * {@link org.springframework.dao.DataIntegrityViolationException} и откатывать сохранение очень долго
     *
     * @param organization
     * @throws EntityExistInDataBaseException если найден Organization с переданным id
     */
    private void isNotExistElseThrow(Organization organization) throws EntityExistInDataBaseException {
        if (existById(organization.getId())) {
            throw new EntityExistInDataBaseException(
                    MessageFormat.format("Организация с id {0} уже существует",organization.getId().toString()));
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
    public boolean deleteById(String id) {
        int deleteCount = jdbcTemplate.update(ORGANIZATION_DELETE_BY_ID_QUERY, id);
        if (deleteCount == 1) {
            return true;
        }
        throw new RuntimeException(
                MessageFormat.format("Ошибка удаления Organization с id {0}",id));
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
