package com.example.testproject1.service.staffservice.impl;

import com.example.testproject1.configuration.cache.CaffeineConfig;
import com.example.testproject1.model.dto.OrganizationListXmlDTO;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.jaxb.JaxbReader;
import com.example.testproject1.service.staffservice.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс для хранения и получения {@link Organization}
 *
 * @author smigranov
 */
@Order(value = 1)
@Service
public class OrganizationStorageService implements StorageService<Organization> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationStorageService.class);
    /**
     * Имя файла для jaxb анмаршалинга
     */
    private final String FILE_NAME = "organizations.xml";
    /**
     * Бин для чтения информации из xml файла
     */
    @Autowired
    private JaxbReader jaxbReader;

    /**
     * {@inheritDoc}
     * Конфигурая кэширования в классе {@link CaffeineConfig}
     */
    @Cacheable(cacheNames = "organization")
    @Override
    public List<Organization> getList() {
        LOGGER.info("Begin find Organization ");
        OrganizationListXmlDTO organizationListXmlDTO = jaxbReader.jaxbXMLToObject(FILE_NAME);
        List<Organization> organizationList = organizationListXmlDTO.getOrganizationList();
        LOGGER.info("Find Organization result");
        return organizationList;
    }
}
