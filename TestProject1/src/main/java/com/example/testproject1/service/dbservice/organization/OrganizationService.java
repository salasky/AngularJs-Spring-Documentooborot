package com.example.testproject1.service.dbservice.organization;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

/**
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций объектов класса {@link  Organization} к базе данных .
 *
 * @author smigranov
 */
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
     * Лог при успешном сохранении
     */
    private final String CREATE_SUCCESS = "Organization успешно сохранен";
    /**
     * Лог при неудачном сохранении
     */
    private final String CREATE_FAIL = "Неудачная попытка сохранения Organization";
    /**
     * Лог при выдаче всех Organization
     */
    private final String GET_ALL_ATTEMPT = "Попытка выдачи всех Organization";
    /**
     * Лог при выдаче Organization по id
     */
    private final String GET_BY_ID_ATTEMPT = "Попытка получить Organization по id";
    /**
     * Лог при успешном обновлении
     */
    private final String UPDATE_SUCCESS = "Organization успешно обновлен";
    /**
     * Лог при неудачном обновлении
     */
    private final String UPDATE_FAIL = "Неудачная попытка обновления Organization";
    /**
     * Лог при попытке удаления всех записей
     */
    private final String DELETE_SUCCESS = "Попытка удаления записей из таблицы Organization";
    /**
     * Лог при успешном удалении записи по id
     */
    private final String DELETE_BY_ID_SUCCESS = "Запись из таблицы Organization успешно удалена";
    /**
     * Лог при неудачном удалении удалении записи по id
     */
    private final String DELETE_BY_ID_FAIL = "Запись из таблицы Organization не удалена";

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization create(Organization organization) throws DocflowRuntimeApplicationException {
        Organization organizationDB = organizationRepository.create(organization);
        if (organizationDB != null) {
            LOGGER.info(CREATE_SUCCESS);
            return organizationDB;
        }
        throw new DocflowRuntimeApplicationException(CREATE_FAIL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> getall() {
        LOGGER.info(GET_ALL_ATTEMPT);
        return organizationRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Organization> getById(String id) {
        LOGGER.info(GET_BY_ID_ATTEMPT);
        return organizationRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization update(Organization organization) throws DocflowRuntimeApplicationException {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у Organization с id {0}", organization.getId().toString()));
        int updateCount = organizationRepository.update(organization);
        if (updateCount == 1) {
            LOGGER.info(UPDATE_SUCCESS);
            return organization;
        }
        throw new DocflowRuntimeApplicationException(UPDATE_FAIL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info(DELETE_SUCCESS);
        organizationRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(String id) throws DocflowRuntimeApplicationException {
        try {
            organizationRepository.deleteById(id);
        } catch (DeleteByIdException e) {
            throw new DocflowRuntimeApplicationException(DELETE_BY_ID_FAIL);
        }
    }
}
