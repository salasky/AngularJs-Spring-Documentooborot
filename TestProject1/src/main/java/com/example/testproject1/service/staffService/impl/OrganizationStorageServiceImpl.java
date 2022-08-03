package com.example.testproject1.service.staffService.impl;

import com.example.testproject1.configuration.cache.CaffeineConfig;
import com.example.testproject1.model.dto.OrganizationListXmlDTO;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.jaxb.JaxbReader;
import com.example.testproject1.service.staffService.OrganizationStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс для хранения и получения {@link Organization}
 *
 * @author smigranov
 */
@Service
public class OrganizationStorageServiceImpl implements OrganizationStorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationStorageServiceImpl.class);
    /**
     * Имя файла для jaxb анмаршалинга
     */
    private final String FILENAME = "organization.xml";
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
    public List<Organization> getOrganizationList() {
        LOGGER.info("Begin find Organization ");
        OrganizationListXmlDTO organizationListXmlDTO = jaxbReader.jaxbXMLToObject(FILENAME);
        List<Organization> organizationList = organizationListXmlDTO.getOrganizationList();
        LOGGER.info("Find Organization result");
        return organizationList;
    }
}
