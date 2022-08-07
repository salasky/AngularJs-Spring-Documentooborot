package com.example.testproject1.service.dbservice.organization;

import com.example.testproject1.dao.organization.OrganizationRepository;
import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.model.staff.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Autowired
    private OrganizationRepository organizationRepository;

    private final String CREATE_SUCCESS="Organization успешно сохранен";
    private final String CREATE_FAIL="Неудачная попытка сохранения Organization";
    private final String GET_ALL_ATTEMPT="Попытка выдачи всех Organization";
    private final String GET_BY_ID_ATTEMPT="Попытка получить Organization по id";
    private final String UPDATE_SUCCESS="Organization успешно обновлен";
    private final String UPDATE_FAIL="Неудачная попытка обновления Organization";
    private final String DELETE_SUCCESS="Записи из таблицы Organization успешно удалены";
    private final String DELETE_BY_ID_SUCCESS="Запись из таблицы Organization успешно удалена";

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

    @Override
    public List<Organization> getall() {
        LOGGER.info(GET_ALL_ATTEMPT);
        return organizationRepository.getAll();
    }

    @Override
    public Optional<Organization> getById(String id) {
        LOGGER.info(GET_BY_ID_ATTEMPT);
        return organizationRepository.getById(id);
    }

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
