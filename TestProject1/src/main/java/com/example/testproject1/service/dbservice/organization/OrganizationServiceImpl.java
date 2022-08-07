package com.example.testproject1.service.dbservice.organization;

import com.example.testproject1.dao.organization.OrganizationRepository;
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

    @Override
    public Optional<Organization> create(Organization organization) {
        LOGGER.info("Попытка создания Organization");
        Optional<Organization> optionalOrganization = organizationRepository.create(organization);
        if (optionalOrganization.isPresent()) {
            LOGGER.info("Organization успешно сохранен");
            return Optional.ofNullable(organization);
        }
        LOGGER.error("Неудачная попытка сохранения Organization");
        return Optional.empty();
    }

    @Override
    public List<Organization> getall() {
        LOGGER.info("Попытка выдачи всех Organization");
        return organizationRepository.getAll();
    }

    @Override
    public Optional<Organization> getById(String id) {
        LOGGER.info("Попытка получить Organization по id");
        return organizationRepository.getById(id);
    }

    @Override
    public Optional<Organization> update(Organization organization) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у Organization с id {0}", organization.getId().toString()));
        int updateCount = organizationRepository.update(organization);
        if (updateCount == 1) {
            LOGGER.info("Organization успешно обновлен");
            return Optional.ofNullable(organization);
        }
        LOGGER.error("Неудачная попытка обновления Organization");
        return null;
    }

    @Override
    public String deleteAll() {
        LOGGER.info("Попытка удалить все записи в таблице organization");
        int count = organizationRepository.deleteAll();
        if (count > 0) {
            LOGGER.info("Записи из таблицы organization успешно удалены");
            return "Записи из таблицы organization успешно удалены";
        }
        LOGGER.error("Не удачная попытка удаления записей из таблицы organization");
        return "Не удачная попытка удаления записей из таблицы organization";
    }

    @Override
    public String deleteById(String id) {
        LOGGER.info("Попытка удалить запись из таблицы organization");
        int count = organizationRepository.deleteById(id);
        if (count > 0) {
            LOGGER.info("Запись из таблицы organization успешно удалена");
            return "Запись из таблицы organization успешно удалена";
        }
        LOGGER.error("Не удачная попытка удаления записи из таблицы organization");
        return "Не удачная попытка удаления записи из таблицы organization";
    }
}
