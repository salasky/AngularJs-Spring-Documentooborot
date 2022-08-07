package com.example.testproject1.service.dbservice.organization;

import com.example.testproject1.dao.CrudRepositories;
import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class OrganizationServiceImpl implements CrudService<Organization> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl.class);
    /**
     * Бин {@link CrudRepositories}
     */
    @Autowired
    @Qualifier("OrganizationRepository")
    private CrudRepositories organizationRepository;
    /**
     * Лог при успешном сохранении
     */
    private final String CREATE_SUCCESS="Organization успешно сохранен";
    /**
     * Лог при неудачном сохранении
     */
    private final String CREATE_FAIL="Неудачная попытка сохранения Organization";
    /**
     * Лог при выдаче всех Organization
     */
    private final String GET_ALL_ATTEMPT="Попытка выдачи всех Organization";
    /**
     * Лог при выдаче Organization по id
     */
    private final String GET_BY_ID_ATTEMPT="Попытка получить Organization по id";
    /**
     * Лог при успешном обновлении
     */
    private final String UPDATE_SUCCESS="Organization успешно обновлен";
    /**
     * Лог при неудачном обновлении
     */
    private final String UPDATE_FAIL="Неудачная попытка обновления Organization";
    /**
     * Лог при успешном удалении всех записей
     */
    private final String DELETE_SUCCESS="Записи из таблицы Organization успешно удалены";
    /**
     * Лог при успешном удалении записи по id
     */
    private final String DELETE_BY_ID_SUCCESS="Запись из таблицы Organization успешно удалена";
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Organization> create(Organization organization) {
        Optional<Organization> optionalOrganization = organizationRepository.create(organization);
        if (optionalOrganization.isPresent()) {
            LOGGER.info(CREATE_SUCCESS);
            return Optional.ofNullable(organization);
        }
        LOGGER.error(CREATE_FAIL);
        return Optional.empty();
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
    public Optional<Organization> update(Organization organization) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у Organization с id {0}", organization.getId().toString()));
        int updateCount = organizationRepository.update(organization);
        if (updateCount == 1) {
            LOGGER.info(UPDATE_SUCCESS);
            return Optional.ofNullable(organization);
        }
        LOGGER.error(UPDATE_FAIL);
        return Optional.empty();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        try {
            if (organizationRepository.deleteAll()) {
                LOGGER.info(DELETE_SUCCESS);
            }
        } catch (DeletePoorlyException e) {
            LOGGER.error(e.toString());
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(String id) {
        try {
            if (organizationRepository.deleteById(id)) {
                LOGGER.info(DELETE_BY_ID_SUCCESS);
            }
        } catch (DeletePoorlyException e) {
            LOGGER.error(e.toString());
        }
    }
}
