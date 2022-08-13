package com.example.testproject1.service.dbservice.organization;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.BatchUpdateException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций объектов класса {@link  Organization} к базе данных .
 *
 * @author smigranov
 */
@Transactional
@Service("OrganizationService")
@Order(value = 1)
public class OrganizationService implements CrudService<Organization> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationService.class);
    /**
     * Бин {@link CrudRepository}
     */
    @Autowired
    private CrudRepository<Organization> organizationRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Organization create(Organization organization) {
        LOGGER.info("Попытка создания Organization");
        if (organization.getId() == null) {
            organization.setId(UUID.randomUUID());
        }
        return organizationRepository.create(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<Organization> getAll() {
        LOGGER.info("Попытка выдачи всех Organization");
        return organizationRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Organization> getById(UUID id) {
        LOGGER.info("Попытка получить Organization по id");
        return organizationRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization update(Organization organization) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у Organization с id {0}", organization.getId().toString()));
        return organizationRepository.update(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info("Попытка удаления записей из таблицы Organization");
        organizationRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(UUID id) {
        LOGGER.info(MessageFormat.format("Попытка удаления Organization с id {0}", id.toString()));
        organizationRepository.deleteById(id);
    }

    @Override
    public void saveALL(List<Organization> entityList) throws BatchUpdateException {
        LOGGER.info("Попытка сохранения List<Organization> в таблицу Organization");
        entityList.stream().filter(entity -> entity.getId() == null).forEach(entity -> entity.setId(UUID.randomUUID()));
        organizationRepository.saveAll(entityList);
    }
}
